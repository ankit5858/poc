package com.demo.service.impl;

import java.io.IOException;
import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.demo.dto.DataDTO;
import com.demo.dto.DataDTO.ImageDTO;
import com.demo.dto.DeleteImgDTO;
import com.demo.dto.MessageDTO;
import com.demo.exception.ImgurException;
import com.demo.kafka.MessageProducer;
import com.demo.mapper.ImageMapper;
import com.demo.repo.ImageRepository;
import com.demo.service.ImgurService;
import com.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ImgurServiceImpl implements ImgurService {
	Logger logger = LoggerFactory.getLogger(ImgurServiceImpl.class);

	@Value("${imgur.clientId}")
	private String client;

	@Value("${imgur.url}")
	private String imgurUrl;

	@Autowired
	private UserService userService;

	@Autowired
	private ImageMapper mapper;

	@Autowired
	private ImageRepository imageRepo;

	@Autowired
	private MessageProducer messgProducer;

	@Autowired
	private ObjectMapper obj;

	@Override
	public void uploadImage(MultipartFile file, Principal principle) throws IOException {

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Client-ID " + client);

		MultiValueMap<String, Object> requestMap = new LinkedMultiValueMap<>();
		requestMap.add("image", file.getBytes());

		HttpEntity<Object> entity = new HttpEntity<Object>(requestMap, headers);

		ResponseEntity<DataDTO> response = restTemplate.exchange(imgurUrl, HttpMethod.POST, entity, DataDTO.class);

		var result = response.getBody();

		if (result.isSuccess()) {
			var user = userService.getUser(principle);
			var image = mapper.dataDTOToImgData(result.getData());
			image.setUser(user);

			imageRepo.save(image);

			MessageDTO mDTO = new MessageDTO(user.getUserName(), file.getName());
			messgProducer.send("user-image", obj.writeValueAsString(mDTO));

		} else {
			logger.error("Getting issue while uploading image");
			throw new ImgurException("Getting issue while uploading image");
		}

	}

	@Override
	public ImageDTO getUsersImage(String imgurId, Principal principle) {
		var user = userService.getUser(principle);

		var existingImage = user.getImgData().stream().filter(img -> imgurId.equals(img.getImgurid())).findFirst();

		if (existingImage.isPresent()) {

			return mapper.imageDataToDTO(existingImage.get());

		}

		return new ImageDTO();
	}

	@Override
	public void deleteImage(String deleteHash, Principal principle) {

		var user = userService.getUser(principle);

		var existingImage = user.getImgData().stream().filter(img -> deleteHash.equals(img.getDeletehash())).findFirst()
				.map(img -> img.getId());

		if (existingImage.isPresent()) {

			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "Client-ID " + client);

			HttpEntity<Object> entity = new HttpEntity<Object>(headers);
			ResponseEntity<DeleteImgDTO> response = restTemplate.exchange(imgurUrl + "/" + deleteHash,
					HttpMethod.DELETE, entity, DeleteImgDTO.class);
			var result = response.getBody();

			if (result.isSuccess()) {
				imageRepo.deleteById(existingImage.get());
			} else {
				throw new ImgurException("Getting issue while delete the image");
			}

		}

	}

}
