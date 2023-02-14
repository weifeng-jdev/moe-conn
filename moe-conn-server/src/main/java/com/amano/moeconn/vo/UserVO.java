package com.amano.moeconn.vo;

import com.amano.moeconn.emnu.AccountNonExpiredEnum;
import com.amano.moeconn.emnu.AccountNonLockedEnum;
import com.amano.moeconn.emnu.CredentialsNonExpiredEnum;
import com.amano.moeconn.emnu.EnableEnum;
import com.amano.moeconn.emnu.GenderEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户前端数据模型")
public class UserVO {
    @ApiModelProperty("数据库id")
    private Long id;

    @ApiModelProperty("用户名")
    private String username;

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

    @ApiModelProperty("账号是否过期：1过期，0未过期")
    private AccountNonExpiredEnum accountNonExpired;

    @ApiModelProperty("账号是否锁定：1锁定，0未锁定")
    private AccountNonLockedEnum accountNonLocked;

    @ApiModelProperty("密码是否过期：1过期，0未过期")
    private CredentialsNonExpiredEnum credentialsNonExpired;

    @ApiModelProperty("用户是否可用：1可用，0不可用")
    private EnableEnum enabled;
}
