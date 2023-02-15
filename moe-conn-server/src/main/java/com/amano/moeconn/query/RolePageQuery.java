package com.amano.moeconn.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@ApiModel("角色分页查询参数")
public class RolePageQuery {
    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("角色code")
    private String code;

    @NotNull
    @Max(100)
    @ApiModelProperty("单页数据量")
    private Integer pageSize;

    @NotNull
    @Min(1)
    @ApiModelProperty("页码")
    private Integer pageNum;
}
