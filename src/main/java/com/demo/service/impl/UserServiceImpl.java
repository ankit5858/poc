package com.demo.service.impl;

import java.security.Principal;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.dto.UserDTO;
import com.demo.dto.UserRegistrationDto;
import com.demo.entity.Role;
import com.demo.entity.User;
import com.demo.mapper.UserMapper;
import com.demo.repo.UserRepository;
import com.demo.service.RoleService;
import com.demo.service.UserService;
import com.demo.util.Utils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public String registerUser(UserRegistrationDto userRDto, Principal principal) {

		var existingRoleList = roleService.getRolesList();
		Set<Role> roleToMap = new HashSet<>();

		if (!"admin".equals(Utils.getRequestedUser(principal).get("user"))) {

			var filterRole = existingRoleList.stream().filter(r -> r.getName().equals("ROLE_USER")).findFirst()
					.orElse(new Role(4L, "ROLE_USER"));
			roleToMap.add(filterRole);

		} else {
			var filterRole = existingRoleList.stream()
					.filter(r -> userRDto.getRoleList().stream().anyMatch(er -> er.getName().equals(r.getName())))
					.findAny();

			roleToMap.add(filterRole.get());

		}

		var user = userMapper.userDtoToUserModel(userRDto);
		user.setRoles(roleToMap);
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		userRepo.save(user);

		return "You have successfully registered!!";
	}

	@Override
	public boolean existingUser(String userName, String email) {
		return userRepo.existsByUserNameOrEmail(userName, email);
	}

	@Override
	public List<UserDTO> getUsers(Principal principal) {

		var data = Utils.getRequestedUser(principal);
		var role = data.get("role");
		var user = data.get("user");

		List<User> userList = Collections.emptyList();
		if ("ROLE_ADMIN".equals(role)) {
			userList = userRepo.findAll();
		} else {
			userList = userRepo.findByUserName(user);
		}

		return userMapper.userModelListToUserDtoList(userList);
	}

	@Override
	public User getUser(Principal principal) {
		var user = Utils.getRequestedUser(principal).get("user");
		return userRepo.findByUserName(user).get(0);
	}

}
