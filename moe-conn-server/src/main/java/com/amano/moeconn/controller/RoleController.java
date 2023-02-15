package com.amano.moeconn.controller;

import com.amano.moeconn.dto.PageData;
import com.amano.moeconn.dto.Result;
import com.amano.moeconn.dto.RoleCreateDTO;
import com.amano.moeconn.dto.UserDetailsDTO;
import com.amano.moeconn.query.RolePageQuery;
import com.amano.moeconn.service.RoleService;
import com.amano.moeconn.vo.RoleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "角色api")
@RequestMapping("/api/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @GetMapping
    @ApiOperation("获取角色列表")
    public Result<PageData<RoleVO>> listAllRole(@Validated RolePageQuery rolePageQuery) {
        return Result.OK(roleService.listAllRole(rolePageQuery));
    }

    @PostMapping
    @ApiOperation("获取角色列表")
    public Result<?> createRole(Authentication authentication, @Validated @RequestBody RoleCreateDTO role) {
        UserDetailsDTO loginUser = (UserDetailsDTO) authentication.getPrincipal();
        role.setCreateBy(loginUser.getId());
        roleService.createRole(role)
        return Result.OK();
    }

    @PutMapping
    @ApiOperation("获取角色列表")
    public Result<?> updateRole() {
        return Result.OK();
    }

    @DeleteMapping
    @ApiOperation("删除角色")
    public Result<?> deleteRole() {
        return Result.OK();
    }
}
