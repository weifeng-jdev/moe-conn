package com.amano.moeconn.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class BaseDomain {
    @ApiModelProperty("创建时间")
    @TableField()
    private LocalDateTime createTime;
    @ApiModelProperty("创建人")
    private Long createBy;
    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;
    @ApiModelProperty("修改人")
    private Long UpdateBy;

    // 逻辑删除字段
    @TableLogic
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty("逻辑删除标记")
    private Integer delFlag;
}
