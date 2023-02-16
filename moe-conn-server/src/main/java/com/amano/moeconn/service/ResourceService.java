package com.amano.moeconn.service;

import com.amano.moeconn.domain.ResourceDO;
import com.amano.moeconn.dto.PageData;
import com.amano.moeconn.query.ResourcePageQuery;
import com.amano.moeconn.vo.ResourceVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ResourceService extends IService<ResourceDO> {
    List<ResourceDO> listAllResource();

    PageData<ResourceVO> listResource(ResourcePageQuery query);

    boolean checkExists(ResourceDO resourceDO);
}
