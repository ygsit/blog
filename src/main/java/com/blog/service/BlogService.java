package com.blog.service;

import com.blog.domain.Blog;
import com.blog.domain.BlogDto;

import java.util.HashMap;
import java.util.List;

public interface BlogService {

    //博客保存
    void saveBlog(Blog blog);

    //分类页面显示的博客
    List<BlogDto> findByTid(Integer tid, Integer currentPage, Integer pageCount);

    //查询博客总数
    int findTotalCounts();

    //查询一页的博客
    List<BlogDto> findByPage(Integer currentPage, Integer pageCount);

    //根据分类id查询分类博客的数量
    Integer findPageTypeBlogCount(Integer tid);

    //查询是否有与此相关的博客
    Integer findTypeBlogs(Integer tid);

    //博客列表
    List<HashMap<String, Object>> listBlogs(int page, int limit, Blog blog);

    //根据传入信息查询博客数量
    Integer countBlog(Blog blog);

    //删除博客
    void deleteById(Integer bid);

    //更新博客
    void updateBlog(Blog blog);

    //根据id查询博客
    BlogDto findById(Integer bid);

    //浏览数+1
    void updateView(Integer bid);

    //查找所有博客
    List<HashMap<String, Object>> getBlogs();
}