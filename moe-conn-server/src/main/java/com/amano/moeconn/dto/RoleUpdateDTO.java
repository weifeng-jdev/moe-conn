package com.amano.moeconn.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("角色修改请求参数")
public class RoleUpdateDTO {
    @ApiModelProperty("角色id")
    private Long id;

    @ApiModelProperty("资源id")
    private List<Long> resources;
}
