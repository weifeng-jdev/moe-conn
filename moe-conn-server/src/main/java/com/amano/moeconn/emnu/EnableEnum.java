package com.amano.moeconn.emnu;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum EnableEnum implements IEnum<Integer> {
    ENABLED(1, "不可用"),
    DISABLED(0, "可用");
    private final Integer enabled;
    private final String stats;

    @Override
    public Integer getValue() {
        return enabled;
    }

    public static EnableEnum getEnum(Integer value) {
        EnableEnum[] values = EnableEnum.values();
        for (EnableEnum enableEnum : values) {
            if (Objects.equals(enableEnum.getValue(), value)) {
                return enableEnum;
            }
        }
        return null;
    }

    public static boolean validate(Integer value) {
        for (EnableEnum enableEnum : EnableEnum.values()) {
            if (Objects.equals(value, enableEnum.getValue())) {
                return true;
            }
        }
        return false;
    }
}
