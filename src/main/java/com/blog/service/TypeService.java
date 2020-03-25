package com.blog.service;

import com.blog.domain.Type;

import java.util.List;

public interface TypeService {

    //查询所有分类
    List<Type> findAll();
}
