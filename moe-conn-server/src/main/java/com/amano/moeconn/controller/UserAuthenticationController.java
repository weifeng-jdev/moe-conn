package com.amano.moeconn.controller;

import com.amano.moeconn.exception.BizException;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static com.amano.moeconn.constant.CacheConstant.LOGIN_CAPTCHA_KEY;
import static com.amano.moeconn.constant.CommonConstant.IMAGE;

@RestController
@Slf4j
@ApiOperation("用户鉴权api")
@RequestMapping("/api/auth")
public class UserAuthenticationController {
    @Resource
    Producer producer;

    @GetMapping("/captcha")
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
}
