package com.blog.service;

import com.blog.domain.Type;

import java.util.List;

public interface TypeService {

    //查询所有分类
    List<Type> findAll();

    //查询总数
    int findCounts();

    //查询某一个分类的数量
    int findNameCount(String name);

    //查询5条分类的数量
    List<Type> findOnePage();
}
