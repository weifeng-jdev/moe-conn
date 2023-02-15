package com.amano.moeconn.service.impl;

import cn.hutool.core.util.StrUtil;
import com.amano.moeconn.dao.UserDao;
import com.amano.moeconn.domain.UserDO;
import com.amano.moeconn.dto.PageData;
import com.amano.moeconn.dto.UserDetailsDTO;
import com.amano.moeconn.exception.BizException;
import com.amano.moeconn.query.UserPageQuery;
import com.amano.moeconn.service.UserService;
import com.amano.moeconn.vo.UserSelfInfoVO;
import com.amano.moeconn.vo.UserVO;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserDao, UserDO> implements UserService {
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

    @Override
    public PageData<UserVO> listUserPage(UserPageQuery query) {
        Page<UserDO> page = new Page<>(query.getPageNum(), query.getPageSize());
        Page<UserDO> userDOPage = userDao.selectPage(page, new LambdaQueryChainWrapper<>(UserDO.class)
                .like(StrUtil.isNotBlank(query.getNickName()), UserDO::getNickName, query.getNickName())
                .like(StrUtil.isNotBlank(query.getEmail()), UserDO::getEmail, query.getEmail())
                .eq(!Objects.isNull(query.getGender()), UserDO::getGender, query.getGender())
                .like(StrUtil.isNotBlank(query.getMobile()), UserDO::getMobile, query.getMobile())
                .eq(UserDO::getEnabled, query.getEnabled())
                .orderByDesc(UserDO::getCreateTime)
                .getWrapper());
        List<UserVO> userVOS = userDOPage.getRecords().stream().map(UserVO::ofDo).collect(Collectors.toList());
        return new PageData<UserVO>().setDataList(userVOS).setTotal(userDOPage.getTotal());
    }
}
