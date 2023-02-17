package com.amano.moeconn.controller;

import com.amano.moeconn.annotation.Log;
import com.amano.moeconn.domain.ResourceDO;
import com.amano.moeconn.dto.ResourceCreateDTO;
import com.amano.moeconn.dto.ResourceDeleteDTO;
import com.amano.moeconn.dto.ResourceUpdateDTO;
import com.amano.moeconn.dto.Result;
import com.amano.moeconn.emnu.SysLogModuleEnum;
import com.amano.moeconn.emnu.SysLogOperTypeEnum;
import com.amano.moeconn.interceptor.AccessLimiting;
import com.amano.moeconn.query.ResourcePageQuery;
import com.amano.moeconn.service.ResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Api(tags = "资源api")
@RequestMapping("/api/resource")
public class ResourceController {
    @Resource
    private ResourceService resourceService;

    @GetMapping
    @ApiOperation("分页查询资源列表")
    @AccessLimiting(limitOnUnitTime = 1, timeUnit = TimeUnit.SECONDS)
    @Log(value = "查询资源列表", module = SysLogModuleEnum.RESOURCE, type = SysLogOperTypeEnum.READ)
    public Result<?> listAllResource(@Validated ResourcePageQuery query) {
        return Result.OK(resourceService.listResource(query));
    }

    @PostMapping
    @ApiOperation("创建资源")
    @AccessLimiting(limitOnUnitTime = 1, timeUnit = TimeUnit.SECONDS)
    @Log(value = "创建资源", module = SysLogModuleEnum.RESOURCE, type = SysLogOperTypeEnum.CREATE)
    public Result<?> createResource(@Validated @RequestBody ResourceCreateDTO resourceCreateDTO) {
        boolean exists = resourceService.checkExists(new ResourceDO().setName(resourceCreateDTO.getName()));
        if (exists) {
            return Result.error("资源名称存在！");
        }
        resourceService.save(resourceCreateDTO.toDO());
        return Result.OK();
    }

    @PutMapping
    @ApiOperation("修改资源")
    @AccessLimiting(limitOnUnitTime = 1, timeUnit = TimeUnit.SECONDS)
    @Log(value = "修改资源", module = SysLogModuleEnum.RESOURCE, type = SysLogOperTypeEnum.UPDATE)
    public Result<?> updateResource(@Validated @RequestBody ResourceUpdateDTO resourceUpdateDTO) {
        boolean exists = resourceService.checkExists(new ResourceDO().setId(resourceUpdateDTO.getId()));
        if (!exists) {
            return Result.error("资源不存在！");
        }
        resourceService.saveOrUpdate(resourceUpdateDTO.toDO());
        return Result.OK();
    }

    @DeleteMapping
    @ApiOperation("删除资源")
    @AccessLimiting(limitOnUnitTime = 1, timeUnit = TimeUnit.SECONDS)
    @Log(value = "删除资源", module = SysLogModuleEnum.RESOURCE, type = SysLogOperTypeEnum.DELETE)
    public Result<?> deleteResource(@Validated @RequestBody ResourceDeleteDTO resourceDeleteDTO) {
        resourceService.removeBatchByIds(resourceDeleteDTO.getIds());
        return Result.OK();
    }
}
