package com.example.service.impl;

import com.example.common.Response;
import com.example.common.Result;
import com.example.common.enums.StatusCode;
import com.example.entity.Role;
import com.example.mapper.RoleMapper;
import com.example.service.IRoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Response addRole(Role role) {
        int flag=roleMapper.exists(role);
        if(flag>0){
            return new Response(StatusCode.EXISTS.getCode(), StatusCode.EXISTS.getMsg());
        }
       int result= roleMapper.addRole(role);
        return  Result.check(result);
    }

    @Override
    public Response updateRole(Role role) {
        int result= roleMapper.updateRole(role);
        return  Result.check(result);
    }

    @Override
    public Response deleteRole(Role role) {
        int result= roleMapper.deleteRole(role);
        return  Result.check(result);
    }

    @Override
    public Response queryRoleInfo(Role role,Integer page,Integer limit) {
        Map map=new HashMap<>(3);
        Page page1=PageHelper.startPage(page,limit);
        List<Role> result= roleMapper.queryRoleInfo(role);
        map.put("total",page1.getTotal());
        map.put("data",result);

        return  new Response(0,result,"success");
    }
}
