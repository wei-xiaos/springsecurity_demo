package com.example.service;

import com.example.common.Response;
import com.example.entity.Resource;

public interface IResourceService {
    Response addResource(Resource resource);

    Response updateResource(Resource resource);

    Response delResource(Resource resource);

    Response queryResourceInfo(Resource resource, Integer page, Integer limit);
}
