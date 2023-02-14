package com.amano.moeconn.controller;

import com.amano.moeconn.dto.RegisterMailCodeDTO;
import com.amano.moeconn.dto.RegisterUserInfoDTO;
import com.amano.moeconn.dto.Result;
import com.amano.moeconn.exception.BizException;
import com.amano.moeconn.interceptor.AccessLimiting;
import com.amano.moeconn.service.UserAuthenticationService;
import com.amano.moeconn.vo.RegisterSuccessVo;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.amano.moeconn.constant.CacheConstant.LOGIN_CAPTCHA_KEY;
import static com.amano.moeconn.constant.CommonConstant.IMAGE;

@RestController
@Slf4j
@Api(tags = "用户鉴权api")
@RequestMapping("/api/auth")
public class UserAuthenticationController {
    @Resource
    private Producer producer;
    @Resource
    private UserAuthenticationService userAuthenticationService;

    @GetMapping("/captcha")
    @ApiOperation("获取验证码")
    public void getCaptcha(HttpServletResponse response, HttpSession session) {
        response.setContentType(IMAGE);
        String text = producer.createText();
        session.setAttribute(LOGIN_CAPTCHA_KEY, text);
        BufferedImage image = producer.createImage(text);
        try {
            ImageIO.write(image, "jpeg", response.getOutputStream());
        } catch (IOException e) {
            log.error("获取验证异常", e);
            throw new BizException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "获取验证码异常");
        }
    }

    @GetMapping("/register/sendMailCode")
    @ApiOperation("获取邮箱注册验证码")
    @AccessLimiting(limitOnUnitTime = 1L, timeUnit = TimeUnit.MINUTES)
    public Result<?> sendMailCode(@Validated @RequestBody RegisterMailCodeDTO registerMailCode)
            throws MessagingException {
        userAuthenticationService.sendMailCode(registerMailCode.getMail());
        return Result.OK();
    }

    @PostMapping("/register")
    @ApiOperation("用户注册")
    @AccessLimiting(limitOnUnitTime = 1L, timeUnit = TimeUnit.SECONDS)
    public Result<RegisterSuccessVo> register(@Validated @RequestBody RegisterUserInfoDTO registerUserInfo) {
        return Result.OK(userAuthenticationService.register(registerUserInfo));
    }
}
