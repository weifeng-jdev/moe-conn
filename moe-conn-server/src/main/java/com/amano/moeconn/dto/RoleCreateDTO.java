package com.amano.moeconn.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@ApiModel("角色创建请求参数")
public class RoleCreateDTO {
    @NotBlank
    @ApiModelProperty("角色名称")
    private String name;

    @NotBlank
    @Pattern(regexp = "ROLE_\\w+")
    @ApiModelProperty("角色code")
    private String code;

    @ApiModelProperty("角色描述")
    private String roleDescribe;

    @ApiModelProperty("资源id")
    private List<Long> resources;

    @ApiModelProperty(value = "创建人", hidden = true)
    private Long createBy;
}
