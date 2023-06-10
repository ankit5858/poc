package com.demo.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.demo.dto.RoleDto;
import com.demo.entity.Role;
import com.demo.mapper.RoleMapper;
import com.demo.repo.RoleRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {RoleServiceImpl.class})
@ExtendWith(SpringExtension.class)
class RoleServiceImplTest {
  @MockBean
  private RoleMapper roleMapper;

  @MockBean
  private RoleRepository roleRepository;

  @Autowired
  private RoleServiceImpl roleServiceImpl;

  /**
   * Method under test: {@link RoleServiceImpl#getRoles()}
   */
  @Test
  void testGetRoles() {
    ArrayList<RoleDto> roleDtoList = new ArrayList<>();
    when(roleMapper.rolesToRoleDtoList(Mockito.<List<Role>>any())).thenReturn(roleDtoList);
    when(roleRepository.findAll()).thenReturn(new ArrayList<>());
    List<RoleDto> actualRoles = roleServiceImpl.getRoles();
    assertSame(roleDtoList, actualRoles);
    assertTrue(actualRoles.isEmpty());
    verify(roleMapper).rolesToRoleDtoList(Mockito.<List<Role>>any());
    verify(roleRepository).findAll();
  }

  /**
   * Method under test: {@link RoleServiceImpl#getRolesList()}
   */
  @Test
  void testGetRolesList() {
    ArrayList<Role> roleList = new ArrayList<>();
    when(roleRepository.findAll()).thenReturn(roleList);
    List<Role> actualRolesList = roleServiceImpl.getRolesList();
    assertSame(roleList, actualRolesList);
    assertTrue(actualRolesList.isEmpty());
    verify(roleRepository).findAll();
  }
}

