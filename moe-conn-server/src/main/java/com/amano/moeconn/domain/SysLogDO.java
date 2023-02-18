package com.amano.moeconn.domain;

import com.amano.moeconn.emnu.SysLogModuleEnum;
import com.amano.moeconn.emnu.SysLogOperTypeEnum;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@ApiModel("操作日志")
@TableName("t_sys_log")
public class SysLogDO {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("数据库id")
    private Long id;

    @ApiModelProperty("操作模块")
    private SysLogModuleEnum operModule;

    @ApiModelProperty("操作类型")
    private SysLogOperTypeEnum operType;

    @ApiModelProperty("操作描述")
    private String operDesc;

    @ApiModelProperty("请求ip")
    private String requestIp;

    @ApiModelProperty("请求uri")
    private String operUri;

    @ApiModelProperty("操作参数")
    private String operParams;

    @ApiModelProperty("操作结果")
    private String operResult;

    @ApiModelProperty("操作时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("操作人id")
    private Long operUserId;

    @ApiModelProperty("操作耗时")
    private Long operTimeCast;
}
