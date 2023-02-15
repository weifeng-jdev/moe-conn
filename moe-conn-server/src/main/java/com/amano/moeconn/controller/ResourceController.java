package com.amano.moeconn.controller;

import com.amano.moeconn.dto.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "资源api")
@RequestMapping("/api/resource")
public class ResourceController {
    @GetMapping
    @ApiOperation("分页查询资源列表")
    public Result<?> listAllResource() {
        return Result.OK();
    }

    @PostMapping
    @ApiOperation("创建资源")
    public Result<?> createResource() {
        return Result.OK();
    }

    @PutMapping
    @ApiOperation("修改资源")
    public Result<?> updateResource() {
        return Result.OK();
    }

    @DeleteMapping
    @ApiOperation("删除资源")
    public Result<?> deleteResource() {
        return Result.OK();
    }
}
