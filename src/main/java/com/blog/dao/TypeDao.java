package com.blog.dao;

import com.blog.domain.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface TypeDao {

    //查询所有分类
    List<Type> findAll();

    //查询总数
    int findCounts();

    //查询某一个分类的数量
    int findNameCount(String name);

    //查询首页5条分类的数量
    List<Type> findOnePage();

    //添加分类
    void typeAdd(Type type);

    //判断分类名是否存在
    Integer typeNameIsExist(String name);

    //查询某一个人创建的分类
    List<Type> findAllByPage(Map<String, Object> map);

    //查询某一个人创建的分类数量
    Integer findCountsByUid(Type type);

    //根据id删除
    void deleteById(Integer tid);

    //修改分类
    void typeUpdate(Type type);
}
