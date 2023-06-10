package com.demo.dto;

import java.util.List;

import com.demo.dto.DataDTO.ImageDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	private Long id;
	private String name;
	private String email;
	private String role;
	private Boolean enable;
	private List<ImageDTO> imges;
	
}
