package com.amano.moeconn.emnu;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountNonLockedEnum implements IEnum<Integer> {
    LOCKED(1, "已锁定"),
    UNLOCKED(0, "未锁定");
    private final Integer accountNonLocked;
    private final String stats;

    @Override
    public Integer getValue() {
        return accountNonLocked;
    }
}
