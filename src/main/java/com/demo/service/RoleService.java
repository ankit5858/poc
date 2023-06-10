package com.demo.service;

import java.util.List;

import com.demo.dto.RoleDto;
import com.demo.entity.Role;



public interface RoleService {
	
	List<RoleDto> getRoles();
	
	List<Role> getRolesList();
}
