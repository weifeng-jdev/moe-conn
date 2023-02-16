package com.amano.moeconn.controller;

import com.amano.moeconn.domain.ResourceDO;
import com.amano.moeconn.dto.ResourceCreateDTO;
import com.amano.moeconn.dto.ResourceDeleteDTO;
import com.amano.moeconn.dto.ResourceUpdateDTO;
import com.amano.moeconn.dto.Result;
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

@RestController
@Api(tags = "资源api")
@RequestMapping("/api/resource")
public class ResourceController {
    @Resource
    private ResourceService resourceService;

    @GetMapping
    @ApiOperation("分页查询资源列表")
    public Result<?> listAllResource(@Validated ResourcePageQuery query) {
        return Result.OK(resourceService.listResource(query));
    }

    @PostMapping
    @ApiOperation("创建资源")
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
    public Result<?> deleteResource(@Validated @RequestBody ResourceDeleteDTO resourceDeleteDTO) {
        resourceService.removeBatchByIds(resourceDeleteDTO.getIds());
        return Result.OK();
    }
}
