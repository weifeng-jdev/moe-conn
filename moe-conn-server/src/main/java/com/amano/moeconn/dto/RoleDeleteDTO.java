package com.amano.moeconn.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("角色修改请求参数")
public class RoleDeleteDTO {
    @ApiModelProperty("角色id")
    private Long id;

    @ApiModelProperty(value = "修改人id", hidden = true)
    private Long updateBy;
}
