package com.blog.service;

import com.blog.domain.Blog;
import com.blog.domain.BlogDto;

import java.util.HashMap;
import java.util.List;

public interface BlogService {

    //博客保存
    void saveBlog(Blog blog);

    //我的博客
    List<HashMap<String, Object>> showMyBlogs(int uid);

    //分类页面显示的博客
    List<BlogDto> findByTid(Integer tid, Integer currentPage, Integer pageCount);

    //查询博客总数
    int findTotalCounts();

    //查询一页的博客
    List<BlogDto> findByPage(Integer currentPage, Integer pageCount);

    //根据分类id查询分类博客的数量
    Integer findPageTypeBlogCount(Integer tid);

}