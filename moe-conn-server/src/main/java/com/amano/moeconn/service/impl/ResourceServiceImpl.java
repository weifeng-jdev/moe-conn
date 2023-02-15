package com.amano.moeconn.service.impl;

import com.amano.moeconn.dao.ResourceDao;
import com.amano.moeconn.dao.UserDao;
import com.amano.moeconn.domain.ResourceDO;
import com.amano.moeconn.domain.UserDO;
import com.amano.moeconn.service.ResourceService;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class ResourceServiceImpl extends ServiceImpl<UserDao, UserDO> implements ResourceService {
    @Resource
    private ResourceDao resourceDao;

    @Override
    public List<ResourceDO> listAllResource() {
        return resourceDao.selectList(new QueryChainWrapper<>(ResourceDO.class));
    }
}
