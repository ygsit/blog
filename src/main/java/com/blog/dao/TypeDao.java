package com.blog.dao;

import com.blog.domain.Type;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TypeDao {

    //查询所有分类
    List<Type> findAll();

}
