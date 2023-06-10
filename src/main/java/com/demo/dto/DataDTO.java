package com.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataDTO {

	private ImageDTO data;
	private boolean success;
	private Integer status;

	@Getter
	@Setter
	public static class ImageDTO {
		private String id;
		private String deletehash;
		private String name;
		private String link;
	}
}
