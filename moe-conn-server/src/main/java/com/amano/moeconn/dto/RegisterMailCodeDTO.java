package com.amano.moeconn.dto;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class RegisterMailCodeDTO {
    @Email(message = "邮箱格式不正确，请确认后重试！")
    private String mail;
}
