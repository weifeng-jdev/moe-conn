package com.amano.moeconn.vo;

import com.amano.moeconn.config.component.UsernameSerializerConverter;
import com.amano.moeconn.domain.SysLogDO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

import static com.amano.moeconn.constant.CommonConstant.DATE_TIME_FORMAT;

@Data
@Accessors(chain = true)
@ApiModel("操作日志视图模型")
public class SysLogVO {
    @ApiModelProperty("数据库id")
    private Long id;

    @ApiModelProperty("操作模块")
    private String operModule;

    @ApiModelProperty("操作类型")
    private String operType;

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
    @JsonFormat(pattern = DATE_TIME_FORMAT)
    private LocalDateTime createTime;

    @ApiModelProperty("操作人id")
    @JsonSerialize(converter = UsernameSerializerConverter.class)
    private Long operUserId;

    @ApiModelProperty("操作耗时")
    private Long operTimeCast;

    public static SysLogVO ofDO(SysLogDO sysLogDO) {
        return new SysLogVO().setId(sysLogDO.getId())
                .setCreateTime(sysLogDO.getCreateTime())
                .setOperDesc(sysLogDO.getOperDesc())
                .setOperModule(sysLogDO.getOperModule().getOperModule())
                .setOperType(sysLogDO.getOperType().getOperType())
                .setOperResult(sysLogDO.getOperResult())
                .setOperParams(sysLogDO.getOperParams())
                .setOperTimeCast(sysLogDO.getOperTimeCast())
                .setOperUserId(sysLogDO.getOperUserId())
                .setRequestIp(sysLogDO.getRequestIp())
                .setOperUri(sysLogDO.getOperUri());
    }
}
