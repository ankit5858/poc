package com.demo.util;

import java.security.Principal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.demo.entity.User;

public class Utils {
	
	public static Map<String, String> getRequestedUser(Principal principal) {
		Map<String, String> result = new ConcurrentHashMap<>();
		if (null != principal) {

			var userToken = (UsernamePasswordAuthenticationToken) principal;
			var userPricipal = (User) userToken.getPrincipal();

			var user = userPricipal.getUserName();
			var role = userPricipal.getRoles().stream().map(r -> r.getName()).findFirst().get();
			
			result.put("user", user);
			result.put("role",role);
			
		}

		return result;
	}

}
