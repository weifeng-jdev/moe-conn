package com.amano.moeconn.emnu;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum AnonymousEnum implements IEnum<Integer> {
    ANONYMOUS(1, "允许"),
    NON_ANONYMOUS(0, "不允许");

    private Integer anonymous;
    private String status;

    @Override
    public Integer getValue() {
        return anonymous;
    }

    public static AnonymousEnum getEnum(Integer value) {
        AnonymousEnum[] values = AnonymousEnum.values();
        for (AnonymousEnum anonymousEnum : values) {
            if (Objects.equals(anonymousEnum.getValue(), value)) {
                return anonymousEnum;
            }
        }
        return null;
    }

    public static boolean validate(Integer value) {
        for (AnonymousEnum userStatusEnum : AnonymousEnum.values()) {
            if (Objects.equals(value, userStatusEnum.getValue())) {
                return true;
            }
        }
        return false;
    }
}
