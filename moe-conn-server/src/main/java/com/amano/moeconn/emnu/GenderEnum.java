package com.amano.moeconn.emnu;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

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
}
