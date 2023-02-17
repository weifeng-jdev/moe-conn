package com.amano.moeconn.controller;

import com.amano.moeconn.dto.PageData;
import com.amano.moeconn.dto.Result;
import com.amano.moeconn.query.SysLogPageQuery;
import com.amano.moeconn.service.SysLogService;
import com.amano.moeconn.vo.SysLogVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "系统操作日志接口")
@RestController
@RequestMapping("/sysLog")
@Slf4j
public class SysLogController {
    @Resource
    private SysLogService sysLogService;

    @GetMapping
    @ApiOperation("操作日志分页查询")
    public Result<PageData<SysLogVO>> listAllSysLog(@Validated SysLogPageQuery sysLogPageQuery) {
        return Result.OK(sysLogService.listAllSysLog(sysLogPageQuery));
    }
}
