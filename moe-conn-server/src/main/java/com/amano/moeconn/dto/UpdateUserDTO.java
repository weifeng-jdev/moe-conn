package com.amano.moeconn.dto;

import com.amano.moeconn.annotation.EnumValue;
import com.amano.moeconn.emnu.EnableEnum;
import com.amano.moeconn.emnu.GenderEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@ApiModel("用户修改参数")
public class UpdateUserDTO {
    @NotNull(message = "用户ID不能为空")
    @ApiModelProperty("数据库id")
    private Long id;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("性别")
    @EnumValue(message = "请选择合法的性别", enumClass = GenderEnum.class, enumMethod = "validate")
    private Integer gender;

    @ApiModelProperty("手机号")
    @Pattern(regexp = "1\\d{10}", message = "手机号格式不正确")
    private String mobile;

    @ApiModelProperty("邮箱")
    @Email(message = "邮箱格式不正确")
    private String email;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("简介")
    private String introduce;

    @ApiModelProperty("签名")
    private String sign;

    @ApiModelProperty("用户是否可用")
    @EnumValue(message = "请选择合法的可用状态", enumClass = EnableEnum.class, enumMethod = "validate")
    private Integer enabled;
}
