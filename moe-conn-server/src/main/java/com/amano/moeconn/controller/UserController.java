package com.amano.moeconn.controller;

import com.amano.moeconn.dto.Result;
import com.amano.moeconn.dto.UserDetailsDTO;
import com.amano.moeconn.service.UserService;
import com.amano.moeconn.vo.UserSelfInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "用户api")
@RequestMapping("/api/user")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/selfInfo")
    @ApiOperation("用户中心-个人信息")
    public Result<UserSelfInfoVO> getUserSelfInfo(@AuthenticationPrincipal Authentication authentication) {
        UserDetailsDTO user = (UserDetailsDTO) authentication.getPrincipal();
        return Result.OK(userService.getSelfInfo(user));
    }
}
