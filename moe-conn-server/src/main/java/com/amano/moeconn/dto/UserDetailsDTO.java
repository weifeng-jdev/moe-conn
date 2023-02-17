package com.amano.moeconn.dto;

import com.amano.moeconn.domain.RoleDO;
import com.amano.moeconn.emnu.FlagEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@ApiModel("UserDetail")
@Accessors(chain = true)
public class UserDetailsDTO implements UserDetails, Serializable {
    @ApiModelProperty("数据库id")
    private Long id;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
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
    private Integer accountNonExpired;
    @ApiModelProperty("账号是否锁定：1锁定，0未锁定")
    private Integer accountNonLocked;
    @ApiModelProperty("密码是否过期：1过期，0未过期")
    private Integer credentialsNonExpired;
    @ApiModelProperty("用户是否可用：1可用，0不可用")
    private Integer enabled;
    @ApiModelProperty("角色集合")
    private List<RoleDO> role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.stream().map(role -> new SimpleGrantedAuthority(role.getCode())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return Objects.equals(this.getAccountNonExpired(), FlagEnum.CLOSE.getFlag());
    }

    @Override
    public boolean isAccountNonLocked() {
        return Objects.equals(this.getAccountNonLocked(), FlagEnum.CLOSE.getFlag());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return Objects.equals(this.getCredentialsNonExpired(), FlagEnum.CLOSE.getFlag());
    }

    @Override
    public boolean isEnabled() {
        return Objects.equals(this.getEnabled(), FlagEnum.CLOSE.getFlag());
    }
}
