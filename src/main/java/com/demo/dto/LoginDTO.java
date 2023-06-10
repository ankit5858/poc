package com.demo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
	
	@NotNull(message = "UserName is required")
	@NotEmpty(message = "UserName is required")
    private String username;
	
	@NotNull(message = "Password is required")
	@NotEmpty(message = "Password is required")
    private String password;
}
