package com.amano.moeconn.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("注册成功响应")
public class RegisterSuccessVo {
    @ApiModelProperty("用户名")
    private String username;
}
