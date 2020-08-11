package com.example.service.impl;

import com.example.common.Response;
import com.example.common.Result;
import com.example.common.enums.StatusCode;
import com.example.entity.Resource;
import com.example.entity.Role;
import com.example.mapper.ResourceMapper;
import com.example.service.IResourceService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResourceServiceImpl implements IResourceService {

    @Autowired
    private ResourceMapper resourceMapper;
    @Override
    public Response addResource(Resource resource) {
        int flag=resourceMapper.exists(resource);
        if(flag>0){
            return new Response(StatusCode.EXISTS.getCode(), StatusCode.EXISTS.getMsg());
        }
        int result= resourceMapper.addResource(resource);
        return  Result.check(result);
    }

    @Override
    public Response updateResource(Resource resource) {
        int result= resourceMapper.updateResource(resource);
        return  Result.check(result);
    }

    @Override
    public Response delResource(Resource resource) {
        int result= resourceMapper.delResource(resource);
        return  Result.check(result);
    }

    @Override
    public Response queryResourceInfo(Resource resource, Integer page, Integer limit) {
        Map map=new HashMap<>(3);
        Page page1= PageHelper.startPage(page,limit);
        List<Resource> result= resourceMapper.queryResourceInfo(resource);
        map.put("total",page1.getTotal());
        map.put("data",result);

        return  new Response(0,result,"success");
    }
}
