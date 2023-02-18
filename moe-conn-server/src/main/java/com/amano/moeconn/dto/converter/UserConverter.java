package com.amano.moeconn.dto.converter;

import com.amano.moeconn.domain.UserDO;
import com.amano.moeconn.dto.UpdateUserDTO;
import com.amano.moeconn.emnu.EnableEnum;
import com.amano.moeconn.emnu.GenderEnum;

/**
 * 用户类的各种类型实体之间的转换
 */
public class UserConverter {
    public static UserDO updateDto2DO(UpdateUserDTO updateUserDTO) {
        return new UserDO()
                .setId(updateUserDTO.getId())
                .setNickName(updateUserDTO.getNickName())
                .setGender(GenderEnum.getEnum(updateUserDTO.getGender()))
                .setIntroduce(updateUserDTO.getIntroduce())
                .setMobile(updateUserDTO.getMobile())
                .setSign(updateUserDTO.getSign())
                .setEnabled(EnableEnum.getEnum(updateUserDTO.getEnabled()));
    }

}
