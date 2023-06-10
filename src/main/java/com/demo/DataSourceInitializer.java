package com.demo;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.demo.entity.Role;
import com.demo.entity.User;
import com.demo.repo.RoleRepository;
import com.demo.repo.UserRepository;

/**
 * Sample class to create roles and admin user data.
 * 
 * @author ankagarw
 *
 */
@Component
public class DataSourceInitializer implements ApplicationListener<ApplicationReadyEvent> {

	private final RoleRepository roleRepository;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public DataSourceInitializer(RoleRepository roleRepository, UserRepository userRepository,
			PasswordEncoder passwordEncoder) {
		this.roleRepository = roleRepository;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		insertRoles();
		insertUser(User.builder().userName("admin").firstName("system").lastName("admin")
				.password(passwordEncoder.encode("admin")).email("abc@test.com").emailVerification(true).enable(true)
				.accountNonExpired(false).credentialsNonExpired(false).accountNonLocked(false).serviceAccess(true)
				.build(), "ROLE_ADMIN");
	}

	private void insertRoles() {
		var roleAdmin = roleRepository.findByName("ROLE_ADMIN");
		if (roleAdmin.isEmpty()) {
			roleRepository.save(new Role(1000L, "ROLE_ADMIN"));
		}
		var roleUser = roleRepository.findByName("ROLE_USER");
		if (roleUser.isEmpty()) {
			roleRepository.save(new Role(1001L, "ROLE_USER"));
		}
	}

	private void insertUser(User user, String... role) {
		if (!userRepository.existsByUserName(user.getUserName())) {
			Set<Role> roles = new HashSet<>();
			for (var r : role) {
				var findRole = roleRepository.findByName(r);
				findRole.ifPresent(roles::add);
			}
			user.setRoles(roles);
			userRepository.save(user);
		}
	}
}
