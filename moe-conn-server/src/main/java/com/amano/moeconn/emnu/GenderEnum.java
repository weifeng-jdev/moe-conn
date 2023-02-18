package com.amano.moeconn.emnu;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum GenderEnum implements IEnum<Integer> {
    MALe(1, "男"),
    MADAM(0, "女"),
    UN_KNOW(2, "未知");
    private final Integer gender;
    private final String genderCh;

    @Override
    public Integer getValue() {
        return this.gender;
    }

    public static GenderEnum getEnum(Integer value) {
        GenderEnum[] values = GenderEnum.values();
        for (GenderEnum genderEnum : values) {
            if (Objects.equals(genderEnum.getValue(), value)) {
                return genderEnum;
            }
        }
        return null;
    }

    public static boolean validate(Integer value) {
        for (GenderEnum genderEnum : GenderEnum.values()) {
            if (Objects.equals(value, genderEnum.getValue())) {
                return true;
            }
        }
        return false;
    }
}
