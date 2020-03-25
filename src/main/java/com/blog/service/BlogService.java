package com.blog.service;

import com.blog.domain.Blog;

import java.util.List;

public interface BlogService {

    //博客保存
    void saveBlog(Blog blog);

    //我的博客
    List<Blog> showMyBlogs(int uid);
}
