package com.amano.moeconn.dto;

import com.amano.moeconn.domain.RoleDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("角色资源对应关系")
public class ResourceRoleDTO implements Serializable {
    @ApiModelProperty("资源id")
    private Long resourceId;
    @ApiModelProperty("资源名称")
    private String name;
    @ApiModelProperty("资源url")
    private String url;
    @ApiModelProperty("是否支持匿名访问：1支持，0不支持")
    private Integer anonymous;
    @ApiModelProperty("具有该资源的所有角色合集")
    private List<RoleDO> roleList;
}
