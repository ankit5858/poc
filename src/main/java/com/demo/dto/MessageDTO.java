package com.demo.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO implements Serializable {

	private static final long serialVersionUID = -4458805209978190331L;
	
	private String userName;
	private String imageName;

}
