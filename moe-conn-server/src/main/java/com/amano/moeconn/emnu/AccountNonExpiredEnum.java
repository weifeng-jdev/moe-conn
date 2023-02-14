package com.amano.moeconn.emnu;

import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountNonExpiredEnum implements IEnum<Integer> {
    EXPIRED(1, "已过期"),
    UNEXPIRED(0, "未过期");
    private final Integer accountNonExpired;
    private final String stats;

    @Override
    public Integer getValue() {
        return accountNonExpired;
    }
}
