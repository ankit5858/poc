package com.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.demo.dto.DataDTO.ImageDTO;
import com.demo.entity.ImgData;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ImageMapper {

	@Mapping(target="imgurid", source="image.id")
	@Mapping(ignore = true, target = "id")
	public ImgData dataDTOToImgData(ImageDTO image);
	
	@Mapping(target="id", source="image.imgurid")
	public ImageDTO imageDataToDTO(ImgData image);

}
