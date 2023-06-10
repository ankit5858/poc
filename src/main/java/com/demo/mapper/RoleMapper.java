package com.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.demo.dto.RoleDto;
import com.demo.entity.Role;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper {

	public List<RoleDto> rolesToRoleDtoList(List<Role> roles);

}
