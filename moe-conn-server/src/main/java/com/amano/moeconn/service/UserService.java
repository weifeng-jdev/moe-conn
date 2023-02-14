package com.amano.moeconn.service;

import com.amano.moeconn.dto.PageData;
import com.amano.moeconn.dto.UserDetailsDTO;
import com.amano.moeconn.query.UserPageQuery;
import com.amano.moeconn.vo.UserSelfInfoVO;
import com.amano.moeconn.vo.UserVO;

public interface UserService {
    UserSelfInfoVO getSelfInfo(UserDetailsDTO user);

    PageData<UserVO> listUserPage(UserPageQuery query);
}
