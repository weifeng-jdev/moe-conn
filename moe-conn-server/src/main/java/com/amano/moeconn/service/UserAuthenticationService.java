package com.amano.moeconn.service;


import com.amano.moeconn.dto.RegisterUserInfoDTO;
import com.amano.moeconn.vo.RegisterSuccessVo;

import javax.mail.MessagingException;

public interface UserAuthenticationService {
    void sendMailCode(String mail) throws MessagingException;

    RegisterSuccessVo register(RegisterUserInfoDTO registerUserInfo);
}
