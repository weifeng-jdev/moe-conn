package com.amano.moeconn.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@ApiModel("资源删除请求参数")
public class ResourceDeleteDTO {
    @NotEmpty(message = "资源id不能为空")
    @ApiModelProperty("资源id")
    private List<Long> ids;
}
