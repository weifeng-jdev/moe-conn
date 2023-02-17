package com.amano.moeconn.service.impl;

import cn.hutool.core.util.StrUtil;
import com.amano.moeconn.dao.SysLogDao;
import com.amano.moeconn.domain.SysLogDO;
import com.amano.moeconn.dto.PageData;
import com.amano.moeconn.query.SysLogPageQuery;
import com.amano.moeconn.service.SysLogService;
import com.amano.moeconn.vo.SysLogVO;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogDao, SysLogDO> implements SysLogService {
    @Resource
    private SysLogDao sysLogDao;

    @Override
    public PageData<SysLogVO> listAllSysLog(SysLogPageQuery query) {
        Page<SysLogDO> page = new Page<>(query.getPageNum(), query.getPageSize());
        Page<SysLogDO> sysLogDOPage = sysLogDao.selectPage(page, new LambdaQueryChainWrapper<>(SysLogDO.class)
                .eq(StrUtil.isNotBlank(query.getOperModule()), SysLogDO::getOperModule, query.getOperModule())
                .eq(StrUtil.isNotBlank(query.getOperType()), SysLogDO::getOperType, query.getOperType())
                .between(Objects.nonNull(query.getCreateTimeBegin()),
                        SysLogDO::getCreateTime, query.getCreateTimeBegin(), query.getCreateTimeEnd())
                .getWrapper());
        List<SysLogDO> records = sysLogDOPage.getRecords();
        List<SysLogVO> sysLogVOS = records.stream().map(SysLogVO::ofDO).collect(Collectors.toList());
        return new PageData<SysLogVO>(sysLogVOS, sysLogDOPage.getTotal());

    }
}
