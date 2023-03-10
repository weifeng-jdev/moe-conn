package com.amano.moeconn.service.impl;

import com.amano.moeconn.dao.MenuDao;
import com.amano.moeconn.domain.MenuDO;
import com.amano.moeconn.service.MenuService;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuDao privilegeDao;

    public List<MenuDO> listAllPrivilege(){
        return privilegeDao.selectList(new QueryChainWrapper<MenuDO>(MenuDO.class));
    }
}
