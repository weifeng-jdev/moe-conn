package com.amano.moeconn.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
@ApiModel("获取邮箱验证码请求参数")
public class RegisterMailCodeDTO {
    @ApiModelProperty("邮箱")
    @Email(message = "邮箱格式不正确，请确认后重试！")
    private String mail;
}
