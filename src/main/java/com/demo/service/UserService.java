package com.demo.service;

import java.security.Principal;
import java.util.List;

import com.demo.dto.UserDTO;
import com.demo.dto.UserRegistrationDto;
import com.demo.entity.User;

public interface UserService {

	String registerUser(UserRegistrationDto userRegistrationDto, Principal principal);

	boolean existingUser(String userName, String email);

	List<UserDTO> getUsers(Principal principal);
	
	User getUser(Principal principal);

}
