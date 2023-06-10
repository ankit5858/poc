package com.demo.service;

import java.io.IOException;
import java.security.Principal;

import org.springframework.web.multipart.MultipartFile;

import com.demo.dto.DataDTO.ImageDTO;

public interface ImgurService {
	
	void uploadImage(MultipartFile file, Principal principle) throws IOException;
	
	void deleteImage(String hashPath, Principal principle);

	ImageDTO getUsersImage(String imgurId, Principal principle);

}
