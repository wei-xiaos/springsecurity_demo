package com.example.service;

import com.example.common.Response;
import com.example.entity.Role;


public interface IRoleService {
    Response addRole(Role role);

    Response updateRole(Role role);

    Response deleteRole(Role role);

    Response queryRoleInfo(Role role,Integer page,Integer limit);
}
