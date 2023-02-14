package com.amano.moeconn.service;

import com.amano.moeconn.dto.UserDetailsDTO;
import com.amano.moeconn.vo.UserSelfInfoVO;

public interface UserService {
    UserSelfInfoVO getSelfInfo(UserDetailsDTO user);
}
