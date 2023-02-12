package com.amano.moeconn.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@ApiModel("承载登录信息实体")
@Accessors(chain = true)
public class LoginInfoDTO {
    @ApiModelProperty("登录用户名")
    private String username;
    @ApiModelProperty("登录密码")
    private String password;
    @ApiModelProperty("登录验证码")
    private String captcha;
}
