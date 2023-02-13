package com.amano.moeconn.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class BaseDomain {
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("创建人")
    private Long createBy;
    @ApiModelProperty("修改时间")
    private Date updateTime;
    @ApiModelProperty("修改人")
    private Long UpdateBy;
    @ApiModelProperty("删除标记")
    private Integer delFlag;
}
