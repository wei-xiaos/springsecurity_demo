package com.example.mapper;

import com.example.entity.Role;
import com.example.vo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserMapper {
    User findByName(@Param("username") String username);

    Set<Role> queryRoleByUserId(@Param("id") Long id);

    List<Map<String, Object>> findAll();

    List<Map<String, Object>> findByRoleName(String auth);

    int addUser(User user);

    int exists(User user);
}
