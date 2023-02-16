package com.amano.moeconn.dto;

import com.amano.moeconn.domain.ResourceDO;
import com.amano.moeconn.emnu.AnonymousEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel("资源删除请求参数")
public class ResourceUpdateDTO {
    @ApiModelProperty("id")
    @NotNull(message = "id不能为空")
    private Long id;

    @ApiModelProperty("权限名称")
    @NotBlank(message = "权限名称不能为空")
    private String name;

    @ApiModelProperty("权限url")
    @NotBlank(message = "权限url不能为空")
    private String url;

    @ApiModelProperty("是否支持匿名访问：1支持，0不支持")
    @NotNull(message = "匿名访问选项不能为空")
    @Max(value = 1, message = "非法的匿名状态")
    @Min(value = 0, message = "非法的匿名状态")
    private Integer anonymous;

    public ResourceDO toDO() {
        return new ResourceDO()
                .setId(this.id)
                .setName(this.name)
                .setUrl(this.url)
                .setAnonymous(AnonymousEnum.getEnum(this.getAnonymous()));
    }
}
