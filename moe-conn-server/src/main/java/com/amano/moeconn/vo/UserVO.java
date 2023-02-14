package com.amano.moeconn.vo;

import com.amano.moeconn.domain.UserDO;
import com.amano.moeconn.emnu.GenderEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@ApiModel("用户前端数据模型")
@Accessors(chain = true)
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

    @ApiModelProperty("账号是否过期")
    private String accountNonExpired;

    @ApiModelProperty("账号是否锁定")
    private String accountNonLocked;

    @ApiModelProperty("密码是否过期")
    private String credentialsNonExpired;

    @ApiModelProperty("用户是否可用")
    private String enabled;

    /**
     * 将数据库do实体类转换成vo实体类
     *
     * @param userDO do实体类
     * @return vo实体类
     */
    public static UserVO ofDo(UserDO userDO) {
        return new UserVO()
                .setId(userDO.getId())
                .setNickName(userDO.getNickName())
                .setEmail(userDO.getEmail())
                .setGender(userDO.getGender())
                .setMobile(userDO.getMobile())
                .setIntroduce(userDO.getIntroduce())
                .setSign(userDO.getSign())
                .setEnabled(userDO.getEnabled().getStats())
                .setAccountNonExpired(userDO.getAccountNonExpired().getStats())
                .setAccountNonLocked(userDO.getAccountNonLocked().getStats())
                .setCredentialsNonExpired(userDO.getCredentialsNonExpired().getStats());
    }
}
