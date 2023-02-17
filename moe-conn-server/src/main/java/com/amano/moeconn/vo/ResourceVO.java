package com.amano.moeconn.vo;

import com.amano.moeconn.config.component.UsernameSerializerConverter;
import com.amano.moeconn.domain.ResourceDO;
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
@ApiModel("资源")
public class ResourceVO {
    @ApiModelProperty("数据库id")
    private Long id;

    @ApiModelProperty("权限名称")
    private String name;

    @ApiModelProperty("权限url")
    private String url;

    @ApiModelProperty("是否支持匿名访问：1支持，0不支持")
    private String anonymous;

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
    private Long updateBy;

    public static ResourceVO ofDO(ResourceDO resourceDO) {
        return new ResourceVO().setId(resourceDO.getId())
                .setName(resourceDO.getName())
                .setUrl(resourceDO.getUrl())
                .setAnonymous(resourceDO.getAnonymous().getStatus())
                .setCreateBy(resourceDO.getCreateBy())
                .setCreateTime(resourceDO.getCreateTime())
                .setUpdateBy(resourceDO.getUpdateBy())
                .setUpdateTime(resourceDO.getUpdateTime());
    }
}
