package com.amano.moeconn.dto;

import com.amano.moeconn.annotation.EnumValue;
import com.amano.moeconn.emnu.EnableEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel("启用/禁用用户账号参数")
public class ChangeUserEnableStatusDTO {
    @NotEmpty(message = "请选择用户")
    @ApiModelProperty("用户id集合")
    private List<Long> ids;

    @NotNull(message = "可用状态不能为空")
    @EnumValue(message = "不合法的可用状态", enumClass = EnableEnum.class, enumMethod = "validate")
    @ApiModelProperty("目标可用状态")
    private Integer isEnabled;
}
