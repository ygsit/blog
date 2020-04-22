package com.blog.dao;

import com.blog.domain.Blog;
import com.blog.domain.BlogDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface BlogDao {

    //保存博客
    void saveBlog(Blog blog);

    //我的博客
    List<HashMap<String, Object>> showMyBlogs(int uid);

    //分类页面显示的博客
    List<BlogDto> findByTid(Map<String, Object> map);

    //查询博客总数
    int findTotalCounts();

    //查询一页的博客
    List<BlogDto> findByPage(Map<String, Object> map);

    //根据分类id查询分类博客的数量
    Integer findPageTypeBlogCount(Integer tid);

    //查询是否有与此相关的博客
    Integer findTypeBlogs(Integer tid);
}
