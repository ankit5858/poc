package com.demo.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.demo.dto.UserDTO;
import com.demo.dto.UserRegistrationDto;
import com.demo.entity.Role;
import com.demo.entity.User;
import com.demo.mapper.UserMapper;
import com.demo.repo.UserRepository;
import com.demo.service.RoleService;
import java.security.Principal;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
  @MockBean
  private PasswordEncoder passwordEncoder;

  @MockBean
  private RoleService roleService;

  @MockBean
  private UserMapper userMapper;

  @MockBean
  private UserRepository userRepository;

  @Autowired
  private UserServiceImpl userServiceImpl;

  /**
   * Method under test: {@link UserServiceImpl#registerUser(UserRegistrationDto, Principal)}
   */
  @Test
  void testRegisterUser() {
    when(roleService.getRolesList()).thenReturn(new ArrayList<>());

    User user = new User();
    user.setAccountNonExpired(true);
    user.setAccountNonLocked(true);
    user.setCreatedAt(
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    user.setCredentialsNonExpired(true);
    user.setEmail("jane.doe@example.org");
    user.setEmailVerification(true);
    user.setEnable(true);
    user.setFirstName("Jane");
    user.setId(1L);
    user.setImgData(new ArrayList<>());
    user.setLastLogin(
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    user.setLastName("Doe");
    user.setPassword("iloveyou");
    user.setRoles(new HashSet<>());
    user.setServiceAccess(true);
    user.setUpdateAt(
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    user.setUserName("janedoe");
    when(userMapper.userDtoToUserModel(Mockito.<UserRegistrationDto>any())).thenReturn(user);

    User user2 = new User();
    user2.setAccountNonExpired(true);
    user2.setAccountNonLocked(true);
    user2.setCreatedAt(
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    user2.setCredentialsNonExpired(true);
    user2.setEmail("jane.doe@example.org");
    user2.setEmailVerification(true);
    user2.setEnable(true);
    user2.setFirstName("Jane");
    user2.setId(1L);
    user2.setImgData(new ArrayList<>());
    user2.setLastLogin(
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    user2.setLastName("Doe");
    user2.setPassword("iloveyou");
    user2.setRoles(new HashSet<>());
    user2.setServiceAccess(true);
    user2.setUpdateAt(
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    user2.setUserName("janedoe");
    when(userRepository.save(Mockito.<User>any())).thenReturn(user2);
    when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");
    assertEquals("You have successfully registered!!",
        userServiceImpl.registerUser(new UserRegistrationDto(), null));
    verify(roleService).getRolesList();
    verify(userMapper).userDtoToUserModel(Mockito.<UserRegistrationDto>any());
    verify(userRepository).save(Mockito.<User>any());
    verify(passwordEncoder).encode(Mockito.<CharSequence>any());
  }

  /**
   * Method under test: {@link UserServiceImpl#registerUser(UserRegistrationDto, Principal)}
   */
  @Test
  void testRegisterUser2() {
    Role role = new Role();
    role.setId(2L);
    role.setName("user");

    ArrayList<Role> roleList = new ArrayList<>();
    roleList.add(role);
    when(roleService.getRolesList()).thenReturn(roleList);

    User user = new User();
    user.setAccountNonExpired(true);
    user.setAccountNonLocked(true);
    user.setCreatedAt(
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    user.setCredentialsNonExpired(true);
    user.setEmail("jane.doe@example.org");
    user.setEmailVerification(true);
    user.setEnable(true);
    user.setFirstName("Jane");
    user.setId(1L);
    user.setImgData(new ArrayList<>());
    user.setLastLogin(
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    user.setLastName("Doe");
    user.setPassword("iloveyou");
    user.setRoles(new HashSet<>());
    user.setServiceAccess(true);
    user.setUpdateAt(
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    user.setUserName("janedoe");
    when(userMapper.userDtoToUserModel(Mockito.<UserRegistrationDto>any())).thenReturn(user);

    User user2 = new User();
    user2.setAccountNonExpired(true);
    user2.setAccountNonLocked(true);
    user2.setCreatedAt(
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    user2.setCredentialsNonExpired(true);
    user2.setEmail("jane.doe@example.org");
    user2.setEmailVerification(true);
    user2.setEnable(true);
    user2.setFirstName("Jane");
    user2.setId(1L);
    user2.setImgData(new ArrayList<>());
    user2.setLastLogin(
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    user2.setLastName("Doe");
    user2.setPassword("iloveyou");
    user2.setRoles(new HashSet<>());
    user2.setServiceAccess(true);
    user2.setUpdateAt(
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    user2.setUserName("janedoe");
    when(userRepository.save(Mockito.<User>any())).thenReturn(user2);
    when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");
    assertEquals("You have successfully registered!!",
        userServiceImpl.registerUser(new UserRegistrationDto(), null));
    verify(roleService).getRolesList();
    verify(userMapper).userDtoToUserModel(Mockito.<UserRegistrationDto>any());
    verify(userRepository).save(Mockito.<User>any());
    verify(passwordEncoder).encode(Mockito.<CharSequence>any());
  }

  /**
   * Method under test: {@link UserServiceImpl#existingUser(String, String)}
   */
  @Test
  void testExistingUser() {
    when(userRepository.existsByUserNameOrEmail(Mockito.<String>any(),
        Mockito.<String>any())).thenReturn(true);
    assertTrue(userServiceImpl.existingUser("janedoe", "jane.doe@example.org"));
    verify(userRepository).existsByUserNameOrEmail(Mockito.<String>any(), Mockito.<String>any());
  }

  /**
   * Method under test: {@link UserServiceImpl#existingUser(String, String)}
   */
  @Test
  void testExistingUser2() {
    when(userRepository.existsByUserNameOrEmail(Mockito.<String>any(),
        Mockito.<String>any())).thenReturn(false);
    assertFalse(userServiceImpl.existingUser("janedoe", "jane.doe@example.org"));
    verify(userRepository).existsByUserNameOrEmail(Mockito.<String>any(), Mockito.<String>any());
  }

  /**
   * Method under test: {@link UserServiceImpl#getUsers(Principal)}
   */
  @Test
  void testGetUsers() {
    ArrayList<UserDTO> userDTOList = new ArrayList<>();
    when(userMapper.userModelListToUserDtoList(Mockito.<List<User>>any())).thenReturn(userDTOList);
    when(userRepository.findByUserName(Mockito.<String>any())).thenReturn(new ArrayList<>());
    List<UserDTO> actualUsers = userServiceImpl.getUsers(null);
    assertSame(userDTOList, actualUsers);
    assertTrue(actualUsers.isEmpty());
    verify(userMapper).userModelListToUserDtoList(Mockito.<List<User>>any());
    verify(userRepository).findByUserName(Mockito.<String>any());
  }


  /**
   * Method under test: {@link UserServiceImpl#getUser(Principal)}
   */
  @Test
  void testGetUser2() {
    User user = new User();
    user.setAccountNonExpired(true);
    user.setAccountNonLocked(true);
    user.setCreatedAt(
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    user.setCredentialsNonExpired(true);
    user.setEmail("jane.doe@example.org");
    user.setEmailVerification(true);
    user.setEnable(true);
    user.setFirstName("Jane");
    user.setId(1L);
    user.setImgData(new ArrayList<>());
    user.setLastLogin(
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    user.setLastName("Doe");
    user.setPassword("iloveyou");
    user.setRoles(new HashSet<>());
    user.setServiceAccess(true);
    user.setUpdateAt(
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    user.setUserName("janedoe");

    ArrayList<User> userList = new ArrayList<>();
    userList.add(user);
    when(userRepository.findByUserName(Mockito.<String>any())).thenReturn(userList);
    assertSame(user, userServiceImpl.getUser(null));
    verify(userRepository).findByUserName(Mockito.<String>any());
  }
}

