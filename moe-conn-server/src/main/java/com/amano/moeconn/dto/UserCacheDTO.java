package com.amano.moeconn.dto;

import com.amano.moeconn.domain.UserDO;
import com.amano.moeconn.emnu.GenderEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Optional;

@Data
@ApiModel("UserDetail")
@Accessors(chain = true)
public class UserCacheDTO {
    @ApiModelProperty("数据库id")
    private Long id;
    @ApiModelProperty("用户名")
    private String username;
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
    private Integer accountNonExpired;
    @ApiModelProperty("账号是否锁定：1锁定，0未锁定")
    private Integer accountNonLocked;
    @ApiModelProperty("密码是否过期：1过期，0未过期")
    private Integer credentialsNonExpired;
    @ApiModelProperty("用户是否可用：1可用，0不可用")
    private Integer enabled;

    public static UserCacheDTO ofDo(UserDO userDO) {
        return new UserCacheDTO()
                .setId(userDO.getId())
                .setNickName(userDO.getNickName())
                .setEmail(userDO.getEmail())
                .setGender(Optional.ofNullable(userDO.getGender()).orElseGet(() -> GenderEnum.UN_KNOW).getGender())
                .setMobile(userDO.getMobile())
                .setIntroduce(userDO.getIntroduce())
                .setSign(userDO.getSign())
                .setAccountNonExpired(userDO.getAccountNonExpired().getValue())
                .setAccountNonLocked(userDO.getAccountNonLocked().getValue())
                .setCredentialsNonExpired(userDO.getCredentialsNonExpired().getValue())
                .setEnabled(userDO.getEnabled().getValue());
    }
}
