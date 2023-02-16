package com.amano.moeconn.vo;

import com.amano.moeconn.domain.ResourceDO;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty("创建人")
    private Long createBy;

    @ApiModelProperty("修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @ApiModelProperty("修改人")
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
