package com.amano.moeconn.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@TableName("t_menu")
@Accessors(chain = true)
@ApiModel("菜单权限")
public class MenuDO extends BaseDomain implements Serializable {
    @ApiModelProperty("数据库id")
    private Long id;
    @ApiModelProperty("菜单名称")
    private String name;
    @ApiModelProperty("菜单路由")
    private String router;
    @ApiModelProperty("菜单排序")
    private Long orderNum;
}
