package com.amano.moeconn.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel("启用/禁用用户账号参数")
public class ChangeUserEnableStatusDTO {
    @NotNull(message = "请选择用户")
    @NotEmpty(message = "请选择用户")
    @ApiModelProperty("用户id集合")
    private List<Long> ids;

    @NotNull(message = "可用状态不能为空")
    @Max(value = 1, message = "非法的匿名状态")
    @Min(value = 0, message = "非法的匿名状态")
    @ApiModelProperty("目标可用状态")
    private Integer isEnabled;
}
