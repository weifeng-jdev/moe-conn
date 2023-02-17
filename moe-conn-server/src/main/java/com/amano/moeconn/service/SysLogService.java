package com.amano.moeconn.service;

import com.amano.moeconn.domain.SysLogDO;
import com.amano.moeconn.dto.PageData;
import com.amano.moeconn.query.SysLogPageQuery;
import com.amano.moeconn.vo.SysLogVO;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SysLogService extends IService<SysLogDO> {
    PageData<SysLogVO> listAllSysLog(SysLogPageQuery sysLogPageQuery);
}
