package com.amano.moeconn.emnu;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SysLogOperTypeEnum implements IEnum<String> {
    CREATE("C", "创建"),
    READ("R", "读取"),
    UPDATE("U", "更新"),
    DELETE("D", "删除"),
    REGISTER("REG", "注册");

    private String operType;
    private String desc;

    @Override
    public String getValue() {
        return operType;
    }
}
