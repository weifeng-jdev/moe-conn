package com.amano.moeconn.emnu;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnableEnum implements IEnum<Integer> {
    ENABLED(1, "可用"),
    DISABLED(0, "不可用");
    private final Integer enabled;
    private final String stats;

    @Override
    public Integer getValue() {
        return enabled;
    }
}
