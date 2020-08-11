package com.example.controller;

import com.example.common.Response;
import com.example.entity.Role;
import com.example.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
@Api(tags = "角色管理")
public class RoleController {

    @Autowired
    private IRoleService roleService;

   @PostMapping("/addRole")
   @ApiOperation(value = "添加角色")
    public Response addRole(@RequestBody Role role){
        return roleService.addRole(role);
    }

    @PostMapping("/updateRole")
    @ApiOperation(value = "修改角色")
    public Response updateRole(@RequestBody Role role){
        return roleService.updateRole(role);
    }

    @PostMapping("/deleteRole")
    @ApiOperation(value = "删除角色")
    public Response deleteRole(@RequestBody Role role){
        return roleService.deleteRole(role);
    }


    @GetMapping("/queryRoleInfo")
    @ApiOperation(value = "查看角色信息")
    public Response queryRoleInfo(Role role,Integer page,Integer limit){
        return roleService.queryRoleInfo(role,page,limit);
    }
}
