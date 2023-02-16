package com.amano.moeconn.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@ApiModel("角色修改请求参数")
public class RoleDeleteDTO {
    @ApiModelProperty("角色id")
    @NotEmpty(message = "角色id不能为空")
    private List<Long> id;
}