package com.amano.moeconn.service;

import com.amano.moeconn.domain.UserDO;
import com.amano.moeconn.dto.PageData;
import com.amano.moeconn.dto.UserDetailsDTO;
import com.amano.moeconn.query.UserPageQuery;
import com.amano.moeconn.vo.UserSelfInfoVO;
import com.amano.moeconn.vo.UserVO;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<UserDO> {
    UserSelfInfoVO getSelfInfo(UserDetailsDTO user);

    PageData<UserVO> listUserPage(UserPageQuery query);
}
