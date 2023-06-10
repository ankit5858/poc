package com.demo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByUserName(String userName);

	boolean existsByUserNameOrEmail(String username, String email);

	Optional<User> findByUserNameOrEmail(String username, String email);

	List<User> findByUserName(String userName);

}
