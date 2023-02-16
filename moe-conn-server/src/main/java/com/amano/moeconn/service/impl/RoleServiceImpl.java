package com.amano.moeconn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.amano.moeconn.dao.RoleDao;
import com.amano.moeconn.domain.RoleDO;
import com.amano.moeconn.domain.RoleResourceDO;
import com.amano.moeconn.dto.PageData;
import com.amano.moeconn.dto.ResourceRoleDTO;
import com.amano.moeconn.dto.RoleCreateDTO;
import com.amano.moeconn.dto.RoleUpdateDTO;
import com.amano.moeconn.exception.BizException;
import com.amano.moeconn.query.RolePageQuery;
import com.amano.moeconn.service.RoleService;
import com.amano.moeconn.vo.RoleVO;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RoleServiceImpl extends ServiceImpl<RoleDao, RoleDO> implements RoleService {
    @Resource
    private RoleDao roleDao;
    @Resource
    private RoleResourceServiceImpl resourceService;

    public List<ResourceRoleDTO> listAllResourceRole() {
        return roleDao.listAllResourceRole();
    }

    public List<RoleDO> listRoleByUserId(Long userId) {
        return roleDao.listRoleByUserId(userId);
    }

    @Override
    public PageData<RoleVO> listAllRole(RolePageQuery rolePageQuery) {
        Page<RoleDO> page = new Page<>(rolePageQuery.getPageNum(), rolePageQuery.getPageSize());
        Page<RoleDO> roleDOPage = roleDao.selectPage(page, new LambdaQueryChainWrapper<RoleDO>(RoleDO.class)
                .eq(Objects.nonNull(rolePageQuery.getName()), RoleDO::getCode, rolePageQuery.getCode())
                .like(StrUtil.isNotBlank(rolePageQuery.getName()), RoleDO::getName, rolePageQuery.getName())
                .orderByDesc(RoleDO::getCreateTime)
                .getWrapper());
        List<RoleVO> roleVOS = BeanUtil.copyToList(roleDOPage.getRecords(), RoleVO.class);
        return new PageData<RoleVO>().setDataList(roleVOS).setTotal(roleDOPage.getTotal());
    }

    @Override
    @Transactional
    public void createRole(RoleCreateDTO role) {
        // 检查roleCode或者roleName是否已经存在
        checkRoleExists(role);
        // 插入角色和关联关系
        RoleDO roleDO = new RoleDO();
        roleDO.setRoleDescribe(role.getRoleDescribe())
                .setCode(role.getCode())
                .setName(role.getName());
        roleDao.insert(roleDO);
        if (CollUtil.isEmpty(role.getResources())) {
            return;
        }
        List<RoleResourceDO> roleResourceDOS = role.getResources().stream()
                .map(resourceId -> {
                    return new RoleResourceDO()
                            .setRole_id(roleDO.getId())
                            .setResource_id(resourceId);
                }).collect(Collectors.toList());
        resourceService.saveBatch(roleResourceDOS);
    }

    private void checkRoleExists(RoleCreateDTO role) {
        RoleDO exists = roleDao.selectOne(new LambdaQueryChainWrapper<>(RoleDO.class)
                .eq(RoleDO::getCode, role.getCode())
                .or()
                .eq(RoleDO::getName, role.getName())
                .getWrapper());
        if (Objects.nonNull(exists) && Objects.equals(exists.getCode(), role.getCode())) {
            throw new BizException(500, "角色Code已存在！");
        } else if (Objects.nonNull(exists) && Objects.equals(exists.getName(), role.getName())) {
            throw new BizException(500, "角色名称已存在！");
        }
    }

    @Override
    @Transactional
    public void updateRoleById(RoleUpdateDTO role) {
        RoleDO existsRole = this.getById(role.getId());
        if (Objects.isNull(existsRole)) {
            throw new BizException(500, "角色不存在！");
        }
        // 删除已有关联，插入新的关联
        resourceService.remove(new LambdaQueryChainWrapper<>(RoleResourceDO.class)
                .eq(RoleResourceDO::getRole_id, role.getId())
                .getWrapper());
        List<Long> resources = role.getResources();
        if (CollUtil.isEmpty(resources)) {
            return;
        }
        List<RoleResourceDO> newRoleResource = resources.stream()
                .map(resourceId -> new RoleResourceDO().setRole_id(role.getId()).setResource_id(resourceId))
                .collect(Collectors.toList());
        resourceService.saveBatch(newRoleResource);
    }

    @Override
    @Transactional
    public void deleteRoleById(RoleDO role) {
        resourceService.removeById(role.getId());
        // 删除已有关联
        resourceService.remove(new LambdaQueryChainWrapper<>(RoleResourceDO.class)
                .eq(RoleResourceDO::getRole_id, role.getId())
                .getWrapper());
    }
}
