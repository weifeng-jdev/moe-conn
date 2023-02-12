package com.amano.moeconn.service;

import com.amano.moeconn.domain.RoleDO;
import com.amano.moeconn.dto.ResourceRoleDTO;

import java.util.List;

public interface RoleService {
    List<ResourceRoleDTO> listAllResourceRole();
    List<RoleDO> listRoleByUserId(Long userId);
}
