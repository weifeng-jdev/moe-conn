package com.amano.moeconn.service.impl;

import com.amano.moeconn.dao.RoleDao;
import com.amano.moeconn.domain.RoleDO;
import com.amano.moeconn.dto.ResourceRoleDTO;
import com.amano.moeconn.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDao roleDao;

    public List<ResourceRoleDTO> listAllResourceRole() {
        return roleDao.listAllResourceRole();
    }

    public List<RoleDO> listRoleByUserId(Long userId) {
        return roleDao.listRoleByUserId(userId);
    }
}
