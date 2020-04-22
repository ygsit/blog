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

    //添加分类
    void typeAdd(Type type);

    //判断分类名是否存在
    Integer typeNameIsExist(String name);

    //查询某一个人创建的分类
    List<Type> findAllByPage(int page, int limit, Type type);

    //查询某一个人创建的分类数量
    Integer findCountsByUid(Type type);

    //根据id删除
    void deleteById(Integer tid);

    //修改分类
    void typeUpdate(Type type);
}
