package com.example.mapper;

import com.example.entity.Resource;

import java.util.List;

public interface ResourceMapper {
    int addResource(Resource resource);

    int updateResource(Resource resource);

    int delResource(Resource resource);

    List<Resource> queryResourceInfo(Resource resource);

    int exists(Resource resource);
}
