package com.demo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Set;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDto {
	
	@NotNull(message = "FirstName is required")
	@NotEmpty(message = "FirstName is required")
	private String firstName;
	
	@NotNull(message = "LastName is required")
	@NotEmpty(message = "LastName is required")
	private String lastName;
	
	@NotNull(message = "Email is required")
	@NotEmpty(message = "Email is required")
	private String email;
	
	@NotNull(message = "UserName is required")
	@NotEmpty(message = "UserName is required")
	private String userName;
	
	@NotNull(message = "Password is required")
	@NotEmpty(message = "Password is required")
	private String password;
	private Set<RoleDto> roleList;

}


