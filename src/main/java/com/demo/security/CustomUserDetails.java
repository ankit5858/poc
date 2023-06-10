package com.demo.security;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.repo.UserRepository;

@Primary
@Service("custom")
public class CustomUserDetails implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		var user = userRepo.findByUserNameOrEmail(username, username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("Invalid username or password")));

		return User.withUsername(user.getUserName()).password(user.getPassword())
				.accountExpired(user.getAccountNonExpired()).accountLocked(user.getAccountNonLocked())
				.credentialsExpired(user.getCredentialsNonExpired())
				.disabled(!(user.getEnable() || user.getEmailVerification()))
				.authorities(user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName()))
						.collect(Collectors.toList()))

				.build();

	}

}
