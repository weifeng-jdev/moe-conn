package com.amano.moeconn.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@TableName("t_role")
@Accessors(chain = true)
@ApiModel("角色")
public class RoleDO extends BaseDomain implements Serializable {
    @ApiModelProperty("数据库id")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("角色code")
    private String code;

    @ApiModelProperty("角色描述")
    private String roleDescribe;
}
