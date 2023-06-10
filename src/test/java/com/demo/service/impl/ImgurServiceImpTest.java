package com.demo.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.demo.entity.ImgData;
import com.demo.entity.User;
import com.demo.exception.ImgurException;
import com.demo.kafka.MessageProducer;
import com.demo.mapper.ImageMapper;
import com.demo.repo.ImageRepository;
import com.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.security.auth.UserPrincipal;
import java.security.Principal;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ImgurServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ImgurServiceImpTest {
  @MockBean
  private ImageMapper imageMapper;

  @MockBean
  private ImageRepository imageRepository;

  @Autowired
  private ImgurServiceImpl imgurServiceImp;

  @MockBean
  private MessageProducer messageProducer;

  @MockBean
  private ObjectMapper objectMapper;

  @MockBean
  private UserService userService;

  /**
   * Method under test: {@link ImgurServiceImpl#getUsersImage(String, Principal)}
   */
  @Test
  void testGetUsersImage() {
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
    when(userService.getUser(Mockito.<Principal>any())).thenReturn(user);
    imgurServiceImp.getUsersImage("42", new UserPrincipal("principle"));
    verify(userService).getUser(Mockito.<Principal>any());
  }

  /**
   * Method under test: {@link ImgurServiceImpl#getUsersImage(String, Principal)}
   */
  @Test
  void testGetUsersImage2() {
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

    ImgData imgData = new ImgData();
    imgData.setDeletehash("Deletehash");
    imgData.setId(1L);
    imgData.setImgurid("Imgurid");
    imgData.setLink("Link");
    imgData.setName("Name");
    imgData.setUser(user);

    ArrayList<ImgData> imgData2 = new ArrayList<>();
    imgData2.add(imgData);

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
    user2.setImgData(imgData2);
    user2.setLastLogin(
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    user2.setLastName("Doe");
    user2.setPassword("iloveyou");
    user2.setRoles(new HashSet<>());
    user2.setServiceAccess(true);
    user2.setUpdateAt(
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    user2.setUserName("janedoe");
    when(userService.getUser(Mockito.<Principal>any())).thenReturn(user2);
    imgurServiceImp.getUsersImage("42", new UserPrincipal("principle"));
    verify(userService).getUser(Mockito.<Principal>any());
  }

  /**
   * Method under test: {@link ImgurServiceImpl#getUsersImage(String, Principal)}
   */
  @Test
  void testGetUsersImage3() {
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

    ImgData imgData = new ImgData();
    imgData.setDeletehash("Deletehash");
    imgData.setId(1L);
    imgData.setImgurid("Imgurid");
    imgData.setLink("Link");
    imgData.setName("Name");
    imgData.setUser(user);

    User user2 = new User();
    user2.setAccountNonExpired(false);
    user2.setAccountNonLocked(false);
    user2.setCreatedAt(
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    user2.setCredentialsNonExpired(false);
    user2.setEmail("john.smith@example.org");
    user2.setEmailVerification(false);
    user2.setEnable(false);
    user2.setFirstName("John");
    user2.setId(2L);
    user2.setImgData(new ArrayList<>());
    user2.setLastLogin(
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    user2.setLastName("Smith");
    user2.setPassword("42");
    user2.setRoles(new HashSet<>());
    user2.setServiceAccess(false);
    user2.setUpdateAt(
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    user2.setUserName("42");

    ImgData imgData2 = new ImgData();
    imgData2.setDeletehash("Imgurid");
    imgData2.setId(2L);
    imgData2.setImgurid("Imgurid");
    imgData2.setLink("Imgurid");
    imgData2.setName("Imgurid");
    imgData2.setUser(user2);

    ArrayList<ImgData> imgData3 = new ArrayList<>();
    imgData3.add(imgData2);
    imgData3.add(imgData);

    User user3 = new User();
    user3.setAccountNonExpired(true);
    user3.setAccountNonLocked(true);
    user3.setCreatedAt(
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    user3.setCredentialsNonExpired(true);
    user3.setEmail("jane.doe@example.org");
    user3.setEmailVerification(true);
    user3.setEnable(true);
    user3.setFirstName("Jane");
    user3.setId(1L);
    user3.setImgData(imgData3);
    user3.setLastLogin(
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    user3.setLastName("Doe");
    user3.setPassword("iloveyou");
    user3.setRoles(new HashSet<>());
    user3.setServiceAccess(true);
    user3.setUpdateAt(
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    user3.setUserName("janedoe");
    when(userService.getUser(Mockito.<Principal>any())).thenReturn(user3);
    imgurServiceImp.getUsersImage("42", new UserPrincipal("principle"));
    verify(userService).getUser(Mockito.<Principal>any());
  }

  /**
   * Method under test: {@link ImgurServiceImpl#getUsersImage(String, Principal)}
   */
  @Test
  void testGetUsersImage4() {
    when(userService.getUser(Mockito.<Principal>any())).thenThrow(
        new ImgurException("An error occurred"));
    assertThrows(ImgurException.class,
        () -> imgurServiceImp.getUsersImage("42", new UserPrincipal("principle")));
    verify(userService).getUser(Mockito.<Principal>any());
  }

  /**
   * Method under test: {@link ImgurServiceImpl#deleteImage(String, Principal)}
   */
  @Test
  void testDeleteImage() {
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
    when(userService.getUser(Mockito.<Principal>any())).thenReturn(user);
    imgurServiceImp.deleteImage("Delete Hash", new UserPrincipal("principle"));
    verify(userService).getUser(Mockito.<Principal>any());
  }

  /**
   * Method under test: {@link ImgurServiceImpl#deleteImage(String, Principal)}
   */
  @Test
  void testDeleteImage2() {
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

    ImgData imgData = new ImgData();
    imgData.setDeletehash("Deletehash");
    imgData.setId(1L);
    imgData.setImgurid("Imgurid");
    imgData.setLink("Link");
    imgData.setName("Name");
    imgData.setUser(user);

    ArrayList<ImgData> imgData2 = new ArrayList<>();
    imgData2.add(imgData);

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
    user2.setImgData(imgData2);
    user2.setLastLogin(
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    user2.setLastName("Doe");
    user2.setPassword("iloveyou");
    user2.setRoles(new HashSet<>());
    user2.setServiceAccess(true);
    user2.setUpdateAt(
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    user2.setUserName("janedoe");
    when(userService.getUser(Mockito.<Principal>any())).thenReturn(user2);
    imgurServiceImp.deleteImage("Delete Hash", new UserPrincipal("principle"));
    verify(userService).getUser(Mockito.<Principal>any());
  }

  /**
   * Method under test: {@link ImgurServiceImpl#deleteImage(String, Principal)}
   */
  @Test
  void testDeleteImage3() {
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

    ImgData imgData = new ImgData();
    imgData.setDeletehash("Deletehash");
    imgData.setId(1L);
    imgData.setImgurid("Imgurid");
    imgData.setLink("Link");
    imgData.setName("Name");
    imgData.setUser(user);

    User user2 = new User();
    user2.setAccountNonExpired(false);
    user2.setAccountNonLocked(false);
    user2.setCreatedAt(
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    user2.setCredentialsNonExpired(false);
    user2.setEmail("john.smith@example.org");
    user2.setEmailVerification(false);
    user2.setEnable(false);
    user2.setFirstName("John");
    user2.setId(2L);
    user2.setImgData(new ArrayList<>());
    user2.setLastLogin(
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    user2.setLastName("Smith");
    user2.setPassword("Delete Hash");
    user2.setRoles(new HashSet<>());
    user2.setServiceAccess(false);
    user2.setUpdateAt(
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    user2.setUserName("Delete Hash");

    ImgData imgData2 = new ImgData();
    imgData2.setDeletehash("Deletehash");
    imgData2.setId(2L);
    imgData2.setImgurid("Deletehash");
    imgData2.setLink("Deletehash");
    imgData2.setName("Deletehash");
    imgData2.setUser(user2);

    ArrayList<ImgData> imgData3 = new ArrayList<>();
    imgData3.add(imgData2);
    imgData3.add(imgData);

    User user3 = new User();
    user3.setAccountNonExpired(true);
    user3.setAccountNonLocked(true);
    user3.setCreatedAt(
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    user3.setCredentialsNonExpired(true);
    user3.setEmail("jane.doe@example.org");
    user3.setEmailVerification(true);
    user3.setEnable(true);
    user3.setFirstName("Jane");
    user3.setId(1L);
    user3.setImgData(imgData3);
    user3.setLastLogin(
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    user3.setLastName("Doe");
    user3.setPassword("iloveyou");
    user3.setRoles(new HashSet<>());
    user3.setServiceAccess(true);
    user3.setUpdateAt(
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    user3.setUserName("janedoe");
    when(userService.getUser(Mockito.<Principal>any())).thenReturn(user3);
    imgurServiceImp.deleteImage("Delete Hash", new UserPrincipal("principle"));
    verify(userService).getUser(Mockito.<Principal>any());
  }

  /**
   * Method under test: {@link ImgurServiceImpl#deleteImage(String, Principal)}
   */
  @Test
  void testDeleteImage4() {
    when(userService.getUser(Mockito.<Principal>any())).thenThrow(
        new ImgurException("An error occurred"));
    assertThrows(ImgurException.class,
        () -> imgurServiceImp.deleteImage("Delete Hash", new UserPrincipal("principle")));
    verify(userService).getUser(Mockito.<Principal>any());
  }
}

