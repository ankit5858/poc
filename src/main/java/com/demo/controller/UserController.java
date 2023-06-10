package com.demo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.dto.DataDTO.ImageDTO;
import com.demo.dto.UserDTO;
import com.demo.service.ImgurService;
import com.demo.service.UserService;

/**
 * User Controller to handle users requests.
 * 
 * @author ankagarw
 *
 */
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ImgurService imgurService;

	/**
	 * Method to get user details.
	 * 
	 * @param principle: current login user.
	 * @return: user details.
	 */
	@GetMapping("/users")
	public ResponseEntity<List<UserDTO>> getUsers(Principal principle) {

		return ResponseEntity.ok(userService.getUsers(principle));
	}
	
	/**
	 * Method to get user image.
	 * 
	 * @param principle: current login user.
	 * @return: user details.
	 */
	@GetMapping("/users/img/{imgurId}")
	public ResponseEntity<ImageDTO> getUsersImage(@PathVariable("imgurId") String imgurId,Principal principle) {

		return ResponseEntity.ok(imgurService.getUsersImage(imgurId, principle));
	}

	/**
	 * Method to upload image for requested user.
	 * 
	 * @param file:      image
	 * @param principle: current login user.
	 * @return: response.
	 */
	@PostMapping("/users/img/upload")
	public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, Principal principle) {

		try {
			imgurService.uploadImage(file, principle);

			var message = "Uploaded the image successfully: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			var message = "Could not upload the image: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}

	/**
	 * Method to delete the image request by user.
	 * 
	 * @param deleteHash: delete code.
	 * @param principle:  current login user.
	 * @return: response.
	 */
	@DeleteMapping("/users/img/delete/{deleteHash}")
	public ResponseEntity<String> deleteImage(@PathVariable("deleteHash") String deleteHash, Principal principle) {

		try {
			imgurService.deleteImage(deleteHash, principle);

			var message = "image successfully deleted.";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			var message = "Could not delete the image Error: " + e.getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}

}
