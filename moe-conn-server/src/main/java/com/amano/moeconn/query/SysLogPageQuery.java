package com.amano.moeconn.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@ApiModel("系统日志分页查询参数")
public class SysLogPageQuery {
    @ApiModelProperty("操作模块")
    private String operModule;

    @ApiModelProperty("操作类型")
    private String operType;

    @ApiModelProperty("操作时间起")
    private LocalDateTime createTimeBegin;

    @ApiModelProperty("操作时间止")
    private LocalDateTime createTimeEnd;

    @NotNull
    @Max(100)
    @ApiModelProperty("单页数据量")
    private Integer pageSize;

    @NotNull
    @Min(1)
    @ApiModelProperty("页码")
    private Integer pageNum;
}
