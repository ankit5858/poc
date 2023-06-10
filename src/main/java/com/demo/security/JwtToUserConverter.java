package com.demo.security;

import java.util.Collections;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import com.demo.entity.Role;
import com.demo.entity.User;

@Component
public class JwtToUserConverter implements Converter<Jwt, UsernamePasswordAuthenticationToken> {

	@Override
	public UsernamePasswordAuthenticationToken convert(Jwt jwt) {
		var user = new User();
		user.setRoles(Collections.singleton(new Role(0L, jwt.getClaimAsString("base"))));
		user.setUserName(jwt.getSubject());
		return new UsernamePasswordAuthenticationToken(user, jwt, Collections.emptyList());
	}
	
}
