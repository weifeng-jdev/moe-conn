package com.amano.moeconn.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@ApiModel("分页数据集合")
@Accessors(chain = true)
public class PageData<T> {
    @ApiModelProperty("分页数据")
    private List<T> dataList;
    @ApiModelProperty("数据总量")
    private Long total;
}
