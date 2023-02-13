package com.amano.moeconn.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@ApiModel("注册用户请求参数")
public class RegisterUserInfoDTO {
    @ApiModelProperty("注册渠道，1.邮箱，2。手机")
    private Integer registerChannel;

    @Pattern(regexp = "1[3|4|5|7|8][0-9]\\d{8}", message = "手机号格式错误")
    @ApiModelProperty("注册手机号")
    private String phone;

    @Email(message = "邮箱格式错误")
    @ApiModelProperty("注册邮箱")
    private String mail;

    @ApiModelProperty("注册密码")
    private String password;

    @NotBlank
    @ApiModelProperty("注册验证码")
    private String verifyCode;
}
