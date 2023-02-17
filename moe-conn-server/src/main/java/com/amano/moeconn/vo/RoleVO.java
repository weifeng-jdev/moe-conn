package com.amano.moeconn.vo;

import com.amano.moeconn.config.component.UsernameSerializerConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

import static com.amano.moeconn.constant.CommonConstant.DATE_TIME_FORMAT;

@Data
@ApiModel("角色视图模型")
@Accessors(chain = true)
public class RoleVO {
    @ApiModelProperty("数据库id")
    private Long id;

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("角色code")
    private String code;

    @ApiModelProperty("角色描述")
    private String describe;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = DATE_TIME_FORMAT)
    private LocalDateTime createTime;

    @ApiModelProperty("创建人")
    @JsonSerialize(converter = UsernameSerializerConverter.class)
    private Long createBy;

    @ApiModelProperty("修改时间")
    @JsonFormat(pattern = DATE_TIME_FORMAT)
    private LocalDateTime updateTime;

    @ApiModelProperty("修改人")
    @JsonSerialize(converter = UsernameSerializerConverter.class)
    private Long UpdateBy;
}
