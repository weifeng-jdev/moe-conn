package com.amano.moeconn.security.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.amano.moeconn.dto.LoginInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;

import static com.amano.moeconn.constant.CacheConstant.LOGIN_CAPTCHA_KEY;

@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private final boolean POST_ONLY = true;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        if (this.POST_ONLY && !request.getMethod().equals(HttpMethod.POST.name())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        // post请求体登录信息获取
        String username;
        String password;
        String captcha;
        try (BufferedReader streamReader
                     = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = streamReader.readLine()) != null) {
                responseStrBuilder.append(inputStr);
            }
            if (StrUtil.isEmpty(responseStrBuilder.toString())) {
                throw new AuthenticationServiceException("用户名或密码错误！");
            }
            JSONObject jsonObject = JSONUtil.parseObj(responseStrBuilder.toString());
            username = Optional.ofNullable(jsonObject.getStr("username"))
                    .orElseThrow(() -> new AuthenticationServiceException("用户名或密码错误！"));
            password = Optional.ofNullable(jsonObject.getStr("password"))
                    .orElseThrow(() -> new AuthenticationServiceException("用户名或密码错误！"));
            captcha = Optional.ofNullable(jsonObject.getStr("captcha"))
                    .orElseThrow(() -> new AuthenticationServiceException("请输入验证码！"));
            // 校验验证码
            if (!this.checkCaptcha(new LoginInfoDTO().setUsername(username).setCaptcha(captcha), request.getSession())) {
                throw new AuthenticationServiceException("验证码错误！");
            }
        } catch (IOException e) {
            log.error("获取请求体错误", e);
            throw new BadCredentialsException("系统异常,请检查用户名或密码！");
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        setDetails(request, token);
        return this.getAuthenticationManager().authenticate(token);
    }

    private boolean checkCaptcha(LoginInfoDTO loginInfo, HttpSession session) {
        // 获取redis中的验证码
        Object verifyCaptcha = session.getAttribute(LOGIN_CAPTCHA_KEY);
        if (Objects.isNull(verifyCaptcha)) {
            return false;
        }
        return Objects.equals(verifyCaptcha, loginInfo.getCaptcha());
    }
}
