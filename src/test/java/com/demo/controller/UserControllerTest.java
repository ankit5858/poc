package com.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.demo.dto.DataDTO;
import com.demo.service.ImgurService;
import com.demo.service.UserService;
import com.sun.security.auth.UserPrincipal;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
  @MockBean
  private ImgurService imgurService;

  @Autowired
  private UserController userController;

  @MockBean
  private UserService userService;

  /**
   * Method under test: {@link UserController#getUsers(Principal)}
   */
  @Test
  void testGetUsers() throws Exception {
    when(userService.getUsers(Mockito.<Principal>any())).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/users");
    MockMvcBuilders.standaloneSetup(userController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  /**
   * Method under test: {@link UserController#getUsers(Principal)}
   */
  @Test
  void testGetUsers2() throws Exception {
    when(userService.getUsers(Mockito.<Principal>any())).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/users");
    requestBuilder.characterEncoding("Encoding");
    MockMvcBuilders.standaloneSetup(userController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  /**
   * Method under test: {@link UserController#getUsersImage(String, Principal)}
   */
  @Test
  void testGetUsersImage() throws Exception {
    DataDTO.ImageDTO imageDTO = new DataDTO.ImageDTO();
    imageDTO.setDeletehash("Deletehash");
    imageDTO.setId("42");
    imageDTO.setLink("Link");
    imageDTO.setName("Name");
    when(imgurService.getUsersImage(Mockito.<String>any(), Mockito.<Principal>any())).thenReturn(
        imageDTO);
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/api/users/img/{imgurId}", "42");
    MockMvcBuilders.standaloneSetup(userController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content()
            .string(
                "{\"id\":\"42\",\"deletehash\":\"Deletehash\",\"name\":\"Name\",\"link\":\"Link\"}"));
  }

  /**
   * Method under test: {@link UserController#uploadImage(MultipartFile, Principal)}
   */
  @Test
  void testUploadImage() throws IOException {
    UserController userController = new UserController();
    MockMultipartFile file =
        new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));

    ResponseEntity<String> actualUploadImageResult =
        userController.uploadImage(file, new UserPrincipal("principle"));
    assertEquals(
        "Could not upload the image: . Error: Cannot invoke \"com.demo.service.ImgurService.uploadImage(org"
            +
            ".springframework.web.multipart.MultipartFile, java.security.Principal)\" because \"this.imgurService\""
            + " is null", actualUploadImageResult.getBody());
    assertEquals(HttpStatus.EXPECTATION_FAILED, actualUploadImageResult.getStatusCode());
    assertTrue(actualUploadImageResult.getHeaders().isEmpty());
  }

  /**
   * Method under test: {@link UserController#deleteImage(String, Principal)}
   */
  @Test
  void testDeleteImage() throws Exception {
    doNothing().when(imgurService).deleteImage(Mockito.<String>any(), Mockito.<Principal>any());
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.delete("/api/users/img/delete/{deleteHash}",
            "Delete Hash");
    MockMvcBuilders.standaloneSetup(userController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
        .andExpect(MockMvcResultMatchers.content().string("image successfully deleted."));
  }

  /**
   * Method under test: {@link UserController#deleteImage(String, Principal)}
   */
  @Test
  void testDeleteImage2() throws Exception {
    doNothing().when(imgurService).deleteImage(Mockito.<String>any(), Mockito.<Principal>any());
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.delete("/api/users/img/delete/{deleteHash}",
            "Delete Hash");
    requestBuilder.characterEncoding("Encoding");
    MockMvcBuilders.standaloneSetup(userController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
        .andExpect(MockMvcResultMatchers.content().string("image successfully deleted."));
  }
}

