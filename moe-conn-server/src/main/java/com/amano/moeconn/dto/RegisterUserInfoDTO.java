package com.amano.moeconn.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@ApiModel("注册用户请求参数")
public class RegisterUserInfoDTO {
    @Email(message = "邮箱格式错误")
    @ApiModelProperty("注册邮箱")
    private String mail;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty("注册密码")
    private String password;

    @NotBlank(message = "验证码不能为空")
    @ApiModelProperty("注册验证码")
    private String verifyCode;
}
