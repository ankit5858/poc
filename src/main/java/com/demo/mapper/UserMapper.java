package com.demo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.demo.dto.DataDTO.ImageDTO;
import com.demo.dto.UserDTO;
import com.demo.dto.UserRegistrationDto;
import com.demo.entity.ImgData;
import com.demo.entity.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

	@Mapping(target = "accountNonExpired", constant = "false")
	@Mapping(target = "accountNonLocked", constant = "false")
	@Mapping(target = "credentialsNonExpired", constant = "false")
	@Mapping(target = "emailVerification", constant = "true")
	@Mapping(target = "enable", constant = "true")
	@Mapping(target = "serviceAccess", constant = "true")
	public User userDtoToUserModel(UserRegistrationDto user);

	public List<UserDTO> userModelToUserDto(List<User> userList);

	default UserDTO userModelToUserDto(User source) {
		var target = new UserDTO();
		target.setName(source.getFirstName() + " " + source.getLastName());
		target.setRole(source.getRoles().stream().map(r -> r.getName()).findFirst().orElse(""));
		target.setId(source.getId());
		target.setEmail(source.getEmail());
		target.setEnable(source.getEnable());

		List<ImageDTO> imageList = new ArrayList<ImageDTO>();

		for (ImgData img : source.getImgData()) {
			var dto = new ImageDTO();
			dto.setId(img.getImgurid());
			dto.setLink(img.getLink());
			dto.setName(img.getName());
			dto.setDeletehash(img.getDeletehash());
			imageList.add(dto);
		}

		target.setImges(imageList);

		return target;
	}

	public List<UserDTO> userModelListToUserDtoList(List<User> userList);

}
