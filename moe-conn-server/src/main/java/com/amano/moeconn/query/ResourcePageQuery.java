package com.amano.moeconn.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@ApiModel("资源分页查询参数")
public class ResourcePageQuery {
    @ApiModelProperty("权限名称")
    private String name;

    @ApiModelProperty("权限url")
    private String url;

    @ApiModelProperty("是否支持匿名访问：1支持，0不支持")
    private Integer anonymous;

    @NotNull
    @Max(100)
    @ApiModelProperty("单页数据量")
    private Integer pageSize;

    @NotNull
    @Min(1)
    @ApiModelProperty("页码")
    private Integer pageNum;
}
