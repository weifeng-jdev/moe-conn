package com.amano.moeconn.query;

import com.amano.moeconn.emnu.AccountNonExpiredEnum;
import com.amano.moeconn.emnu.AccountNonLockedEnum;
import com.amano.moeconn.emnu.CredentialsNonExpiredEnum;
import com.amano.moeconn.emnu.EnableEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@ApiModel("用户分页查询参数")
public class UserPageQuery {
    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("性别")
    private Integer gender;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("账号是否过期：1过期，0未过期")
    private AccountNonExpiredEnum accountNonExpired = AccountNonExpiredEnum.UNEXPIRED;

    @ApiModelProperty("账号是否锁定：1锁定，0未锁定")
    private AccountNonLockedEnum accountNonLocked = AccountNonLockedEnum.UNLOCKED;

    @ApiModelProperty("密码是否过期：1过期，0未过期")
    private CredentialsNonExpiredEnum credentialsNonExpired = CredentialsNonExpiredEnum.UNEXPIRED;

    @ApiModelProperty("用户是否可用：1可用，0不可用")
    private EnableEnum enabled = EnableEnum.ENABLED;

    @NotNull
    @Max(100)
    @ApiModelProperty("单页数据量")
    private Integer pageSize;

    @NotNull
    @Min(1)
    @ApiModelProperty("页码")
    private Integer pageNum;
}
