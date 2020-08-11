package com.example.controller;

import com.example.common.Response;
import com.example.entity.Resource;
import com.example.service.IResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resource")
@Api(tags = "资源管理")
public class ResourceController {

    @Autowired
    private IResourceService resourceService;

    @PostMapping("/addResource")
    @ApiOperation(value = "添加资源")
    public Response addResource(@RequestBody Resource resource){
        return resourceService.addResource(resource);
    }

    @PostMapping("/updateResource")
    @ApiOperation(value = "修改资源")
    public Response updateResource(@RequestBody Resource resource){
        return resourceService.updateResource(resource);
    }

    @PostMapping("/delResource")
    @ApiOperation(value = "删除资源")
    public Response delResource(@RequestBody Resource resource){
        return resourceService.delResource(resource);
    }

    @GetMapping("/queryResourceInfo")
    @ApiOperation(value = "查询资源")
    public Response queryResourceInfo(Resource resource, Integer page, Integer limit){
        return resourceService.queryResourceInfo(resource,page,limit);
    }
}
