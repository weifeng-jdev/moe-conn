package com.amano.moeconn.service.impl;

import com.amano.moeconn.dao.RoleResourceDao;
import com.amano.moeconn.domain.RoleResourceDO;
import com.amano.moeconn.service.RoleResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RoleResourceServiceImpl
        extends ServiceImpl<RoleResourceDao, RoleResourceDO> implements RoleResourceService {
}
