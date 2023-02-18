package com.amano.moeconn.controller;

import com.amano.moeconn.annotation.Log;
import com.amano.moeconn.domain.UserDO;
import com.amano.moeconn.dto.ChangeUserEnableStatusDTO;
import com.amano.moeconn.dto.PageData;
import com.amano.moeconn.dto.Result;
import com.amano.moeconn.dto.UpdateUserDTO;
import com.amano.moeconn.dto.UserDetailsDTO;
import com.amano.moeconn.dto.converter.UserConverter;
import com.amano.moeconn.emnu.EnableEnum;
import com.amano.moeconn.emnu.SysLogModuleEnum;
import com.amano.moeconn.emnu.SysLogOperTypeEnum;
import com.amano.moeconn.interceptor.AccessLimiting;
import com.amano.moeconn.query.UserPageQuery;
import com.amano.moeconn.service.UserService;
import com.amano.moeconn.vo.UserSelfInfoVO;
import com.amano.moeconn.vo.UserVO;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RestController
@Api(tags = "用户api")
@RequestMapping("/api/user")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/selfInfo")
    @ApiOperation("用户中心-个人信息")
    @AccessLimiting(limitOnUnitTime = 1, timeUnit = TimeUnit.SECONDS)
    @Log(value = "用户中心-个人信息", module = SysLogModuleEnum.USER, type = SysLogOperTypeEnum.READ)
    public Result<UserSelfInfoVO> getUserSelfInfo(@AuthenticationPrincipal Authentication authentication) {
        UserDetailsDTO user = (UserDetailsDTO) authentication.getPrincipal();
        return Result.OK(userService.getSelfInfo(user));
    }

    @GetMapping
    @ApiOperation("/用户列表查询")
    @AccessLimiting(limitOnUnitTime = 1, timeUnit = TimeUnit.SECONDS)
    @Log(value = "用户列表查询", module = SysLogModuleEnum.USER, type = SysLogOperTypeEnum.READ)
    public Result<PageData<UserVO>> listUserPage(@Validated UserPageQuery query) {
        return Result.OK(userService.listUserPage(query));
    }

    @GetMapping("/{id}")
    @ApiOperation("获取用户详情")
    @AccessLimiting(limitOnUnitTime = 1, timeUnit = TimeUnit.SECONDS)
    @Log(value = "获取用户详情", module = SysLogModuleEnum.USER, type = SysLogOperTypeEnum.READ)
    public Result<UserVO> getUserDetailById(@PathVariable("id") Long id) {
        UserDO userDO = userService.getById(id);
        if (Objects.isNull(userDO)) {
            return Result.error("用户不存在");
        }
        return Result.OK(UserVO.ofDo(userDO));
    }

    @PutMapping("/enableStatus")
    @ApiOperation("启用/禁用用户账号")
    @AccessLimiting(limitOnUnitTime = 1, timeUnit = TimeUnit.SECONDS)
    @Log(value = "启用/禁用用户账号", module = SysLogModuleEnum.USER, type = SysLogOperTypeEnum.UPDATE)
    public Result<?> changeEnableStatus(@Validated @RequestBody ChangeUserEnableStatusDTO changeUserEnableStatusDTO) {
        userService.update(new UserDO().setEnabled(EnableEnum.getEnum(changeUserEnableStatusDTO.getIsEnabled())),
                new LambdaQueryChainWrapper<>(UserDO.class)
                        .in(UserDO::getId, changeUserEnableStatusDTO.getIds())
                        .getWrapper());
        return Result.OK();
    }

    @PutMapping
    @ApiOperation("修改用户信息")
    @AccessLimiting(limitOnUnitTime = 1, timeUnit = TimeUnit.SECONDS)
    @Log(value = "修改用户信息", module = SysLogModuleEnum.USER, type = SysLogOperTypeEnum.UPDATE)
    public Result<?> updateUserById(@Validated @RequestBody UpdateUserDTO updateUserDTO) {
        UserDO userDO = userService.getById(updateUserDTO.getId());
        if (Objects.isNull(userDO)) {
            return Result.error("用户不存在");
        }
        userService.updateById(UserConverter.updateDto2DO(updateUserDTO));
        return Result.OK();
    }
}
