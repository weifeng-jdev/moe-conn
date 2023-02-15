package com.amano.moeconn.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@TableName("t_role_resource")
@Accessors(chain = true)
@ApiModel("角色资源关联")
public class RoleResourceDO {
    @TableId
    @ApiModelProperty("数据库id")
    private Long id;

    @ApiModelProperty("角色id")
    private Long role_id;

    @ApiModelProperty("资源id")
    private Long resource_id;
}
