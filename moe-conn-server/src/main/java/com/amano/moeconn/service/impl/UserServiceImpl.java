package com.amano.moeconn.service.impl;

import com.amano.moeconn.dao.UserDao;
import com.amano.moeconn.domain.UserDO;
import com.amano.moeconn.dto.UserDetailsDTO;
import com.amano.moeconn.exception.BizException;
import com.amano.moeconn.service.UserService;
import com.amano.moeconn.vo.UserSelfInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public UserSelfInfoVO getSelfInfo(UserDetailsDTO user) {
        UserDO userDO = Optional.ofNullable(userDao.selectById(user.getId()))
                .orElseThrow(() -> new BizException(HttpStatus.BAD_REQUEST.value(), "用户不存在"));
        // 构建vo
        UserSelfInfoVO userSelfInfoVO = new UserSelfInfoVO();
        userSelfInfoVO.setId(userDO.getId())
                .setNickName(userDO.getNickName())
                .setEmail(userDO.getEmail())
                .setGender(userDO.getGender())
                .setMobile(userDO.getMobile())
                .setSign(userDO.getSign())
                .setIntroduce(userDO.getIntroduce());
        return userSelfInfoVO;
    }
}
