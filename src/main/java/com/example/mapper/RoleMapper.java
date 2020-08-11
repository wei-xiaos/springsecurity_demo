package com.example.mapper;

import com.example.entity.Role;

import java.util.List;

public interface RoleMapper {
    int addRole(Role role);

    int updateRole(Role role);

    int deleteRole(Role role);

    List<Role> queryRoleInfo(Role role);

    int exists(Role role);
}
