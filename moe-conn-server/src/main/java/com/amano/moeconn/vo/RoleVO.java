package com.amano.moeconn.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty("创建人")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Long createBy;

    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("修改人")
    private Long UpdateBy;
}
