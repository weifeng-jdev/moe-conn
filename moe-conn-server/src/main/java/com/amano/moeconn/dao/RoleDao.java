package com.amano.moeconn.dao;

import com.amano.moeconn.domain.RoleDO;
import com.amano.moeconn.dto.ResourceRoleDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleDao extends BaseMapper<RoleDO> {
    List<ResourceRoleDTO> listAllResourceRole();

    List<RoleDO> listRoleByUserId(Long userId);
}
