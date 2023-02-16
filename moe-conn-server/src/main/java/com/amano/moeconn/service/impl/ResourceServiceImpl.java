package com.amano.moeconn.service.impl;

import cn.hutool.core.util.StrUtil;
import com.amano.moeconn.dao.ResourceDao;
import com.amano.moeconn.domain.ResourceDO;
import com.amano.moeconn.dto.PageData;
import com.amano.moeconn.query.ResourcePageQuery;
import com.amano.moeconn.service.ResourceService;
import com.amano.moeconn.vo.ResourceVO;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ResourceServiceImpl extends ServiceImpl<ResourceDao, ResourceDO> implements ResourceService {
    @Resource
    private ResourceDao resourceDao;

    @Override
    public List<ResourceDO> listAllResource() {
        return resourceDao.selectList(new QueryChainWrapper<>(ResourceDO.class));
    }

    @Override
    public PageData<ResourceVO> listResource(ResourcePageQuery query) {
        Page<ResourceDO> page = new Page<>();
        Page<ResourceDO> pageData = resourceDao.selectPage(page, new LambdaQueryChainWrapper<>(ResourceDO.class)
                .like(StrUtil.isNotBlank(query.getName()), ResourceDO::getName, query.getName())
                .like(StrUtil.isNotBlank(query.getUrl()), ResourceDO::getUrl, query.getUrl())
                .eq(Objects.nonNull(query.getAnonymous()), ResourceDO::getAnonymous, query.getAnonymous())
                .getWrapper());
        List<ResourceDO> records = pageData.getRecords();
        List<ResourceVO> resourceVOS = records.stream().map(ResourceVO::ofDO).collect(Collectors.toList());
        return new PageData<ResourceVO>().setDataList(resourceVOS).setTotal(pageData.getTotal());
    }

    @Override
    public boolean checkExists(ResourceDO resourceDO) {
        Long count = resourceDao.selectCount(new LambdaQueryChainWrapper<>(ResourceDO.class)
                .eq(Objects.nonNull(resourceDO.getId()), ResourceDO::getId, resourceDO.getId())
                .eq(StrUtil.isNotBlank(resourceDO.getName()), ResourceDO::getName, resourceDO.getName())
                .getWrapper());
        return count > 0;
    }
}
