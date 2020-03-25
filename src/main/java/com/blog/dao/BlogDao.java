package com.blog.dao;

import com.blog.domain.Blog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogDao {

    //保存博客
    void saveBlog(Blog blog);

    //我的博客
    List<Blog> showMyBlogs(int uid);
}
