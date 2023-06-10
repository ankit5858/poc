package com.demo.controller;

import jakarta.validation.Valid;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.LoginDTO;
import com.demo.dto.TokenDTO;
import com.demo.dto.UserRegistrationDto;
import com.demo.security.TokenGenerator;
import com.demo.service.UserService;
import com.demo.util.Utils;



/**
 * Controller to handle the request for clients register and login.
 * 
 * @author ankagarw
 *
 */
@RestController
@RequestMapping("/api")
public class WebController {

	@Autowired
	private UserService userService;

	@Autowired
	TokenGenerator tokenGenerator;

	@Autowired
	DaoAuthenticationProvider daoAuthenticationProvider;

	@Autowired
	@Qualifier("jwtRefreshTokenAuthProvider")
	JwtAuthenticationProvider refreshTokenAuthProvider;

	/**
	 * Method to register a user.
	 * 
	 * @param registrationDto : user details.
	 * @param principal:      get the requested user, if provided.
	 * @return : response.
	 */
	@PostMapping("/register")
	public String registerUserAccount(@RequestBody @Valid UserRegistrationDto registrationDto, Principal principal) {

		boolean isExistingUser = userService.existingUser(registrationDto.getUserName(), registrationDto.getEmail());

		if (isExistingUser) {
			return "User already registered!!";
		}

		if ("admin".equals(Utils.getRequestedUser(principal).get("user"))
				&& CollectionUtils.isEmpty(registrationDto.getRoleList())) {

			return "Role is required";
		}

		return userService.registerUser(registrationDto, principal);
	}

	/**
	 * Method to provide the token for a valid user.
	 * 
	 * @param loginDTO : user credentials.
	 * @return: response token.
	 */
	@PostMapping("/login")
	public ResponseEntity<TokenDTO> login(@RequestBody @Valid LoginDTO loginDTO) {
		Authentication authentication = daoAuthenticationProvider.authenticate(
				UsernamePasswordAuthenticationToken.unauthenticated(loginDTO.getUsername(), loginDTO.getPassword()));

		return ResponseEntity.ok(tokenGenerator.createToken(authentication));
	}

}
