package com.amano.moeconn.dto;

import com.amano.moeconn.emnu.GenderEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("用户修改参数")
public class UpdateUserDTO {
    @NotNull(message = "用户ID不能为空")
    @ApiModelProperty("数据库id")
    private Long id;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("性别")
    private GenderEnum gender;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("简介")
    private String introduce;

    @ApiModelProperty("签名")
    private String sign;

    @ApiModelProperty("用户是否可用")
    private String enabled;
}
