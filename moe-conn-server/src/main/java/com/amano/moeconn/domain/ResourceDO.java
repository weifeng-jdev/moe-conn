package com.amano.moeconn.domain;

import com.amano.moeconn.emnu.AnonymousEnum;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@TableName("t_resource")
@Accessors(chain = true)
@ApiModel("资源")
public class ResourceDO extends BaseDomain {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("数据库id")
    private Long id;

    @ApiModelProperty("权限名称")
    private String name;

    @ApiModelProperty("权限url")
    private String url;

    @ApiModelProperty("请求方法")
    private String requestMethod;

    @ApiModelProperty("是否支持匿名访问：1支持，0不支持")
    private AnonymousEnum anonymous;
}
