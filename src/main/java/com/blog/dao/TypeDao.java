package com.blog.dao;

import com.blog.domain.Type;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TypeDao {

    //查询所有分类
    List<Type> findAll();

    //查询总数
    int findCounts();

    //查询某一个分类的数量
    int findNameCount(String name);

    //查询首页5条分类的数量
    List<Type> findOnePage();
}
