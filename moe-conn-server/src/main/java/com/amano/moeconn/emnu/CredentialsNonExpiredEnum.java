package com.amano.moeconn.emnu;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CredentialsNonExpiredEnum implements IEnum<Integer> {
    EXPIRED(1, "已过期"),
    UNEXPIRED(0, "未过期");
    private final Integer credentialsNonExpired;
    private final String stats;

    @Override
    public Integer getValue() {
        return credentialsNonExpired;
    }
}
