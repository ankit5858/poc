package com.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dto.RoleDto;
import com.demo.entity.Role;
import com.demo.mapper.RoleMapper;
import com.demo.repo.RoleRepository;
import com.demo.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public List<RoleDto> getRoles() {

		var roleList = roleRepo.findAll();
		List<RoleDto> roleDtoList = new ArrayList<>();
		if (null != roleList) {

			roleDtoList = roleMapper.rolesToRoleDtoList(roleList);
		}

		return roleDtoList;
	}

	@Override
	public List<Role> getRolesList() {

		var roleList = roleRepo.findAll();

		return roleList;
	}

}
