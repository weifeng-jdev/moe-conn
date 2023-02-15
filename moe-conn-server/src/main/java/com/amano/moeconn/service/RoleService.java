package com.amano.moeconn.service;

import com.amano.moeconn.domain.RoleDO;
import com.amano.moeconn.dto.PageData;
import com.amano.moeconn.dto.ResourceRoleDTO;
import com.amano.moeconn.dto.RoleCreateDTO;
import com.amano.moeconn.query.RolePageQuery;
import com.amano.moeconn.vo.RoleVO;

import java.util.List;

public interface RoleService {
    List<ResourceRoleDTO> listAllResourceRole();

    List<RoleDO> listRoleByUserId(Long userId);

    PageData<RoleVO> listAllRole(RolePageQuery rolePageQuery);

    void createRole(RoleCreateDTO role);
}
