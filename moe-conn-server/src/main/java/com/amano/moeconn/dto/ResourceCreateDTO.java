package com.amano.moeconn.dto;

import com.amano.moeconn.config.annotation.EnumValue;
import com.amano.moeconn.domain.ResourceDO;
import com.amano.moeconn.emnu.AnonymousEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel("资源删除请求参数")
public class ResourceCreateDTO {
    @ApiModelProperty("资源名称")
    @NotBlank(message = "资源名称不能为空")
    private String name;

    @ApiModelProperty("权限url")
    @NotBlank(message = "资源url不能为空")
    private String url;

    @ApiModelProperty("是否支持匿名访问：1支持，0不支持")
    @NotNull(message = "匿名访问选项不能为空")
    @EnumValue(enumClass = AnonymousEnum.class, enumMethod = "validate", message = "非法的匿名选项")
    private Integer anonymous;

    @ApiModelProperty("请求方法")
    @NotBlank(message = "请求方法不能为空")
    private String requestMethod;

    public ResourceDO toDO() {
        return new ResourceDO()
                .setName(this.name)
                .setUrl(this.url)
                .setAnonymous(AnonymousEnum.getEnum(this.getAnonymous()))
                .setRequestMethod(this.getRequestMethod());
    }
}
