package com.amano.moeconn.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@ApiModel("用户中心-个人信息")
@Accessors(chain = true)
public class UserSelfInfoVO {
    @ApiModelProperty("数据库id")
    private Long id;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("性别")
    private Integer gender;
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
}
