package com.amano.moeconn.domain;

import com.amano.moeconn.emnu.FlagEnum;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@TableName("t_user")
@ApiModel("用户")
public class UserDO extends BaseDomain implements Serializable {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("数据库id")
    private Long id;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("数据库id")
    private String password;
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
    @ApiModelProperty("账号是否过期：1过期，0未过期")
    private Integer accountNonExpired = FlagEnum.CLOSE.getFlag();
    @ApiModelProperty("账号是否锁定：1锁定，0未锁定")
    private Integer accountNonLocked = FlagEnum.CLOSE.getFlag();
    @ApiModelProperty("密码是否过期：1过期，0未过期")
    private Integer credentialsNonExpired = FlagEnum.CLOSE.getFlag();
    @ApiModelProperty("用户是否可用：1可用，0不可用")
    private Integer enabled = FlagEnum.OPEN.getFlag();
}
