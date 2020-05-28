package com.blog.service.impl;

import com.blog.dao.BlogDao;
import com.blog.domain.Blog;
import com.blog.domain.BlogDto;
import com.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Override
    public void saveBlog(Blog blog) {
        blogDao.saveBlog(blog);
    }

    @Override
    public List<BlogDto> findByTid(Integer tid, Integer currentPage, Integer pageCount) {
        Map<String, Object> map = new HashMap<>();
        int offset = (currentPage-1)*pageCount;
        map.put("tid", tid);
        map.put("offset", offset);
        map.put("pageCount", pageCount);
        return blogDao.findByTid(map);
    }

    @Override
    public int findTotalCounts() {
        return blogDao.findTotalCounts();
    }

    @Override
    public List<BlogDto> findByPage(Integer currentPage, Integer pageCount) {
        Map<String, Object> map = new HashMap<>();
        int offset = (currentPage-1)*pageCount;
        map.put("offset", offset);
        map.put("pageCount", pageCount);
        return blogDao.findByPage(map);
    }

    @Override
    public Integer findPageTypeBlogCount(Integer tid) {
        return blogDao.findPageTypeBlogCount(tid);
    }

    @Override
    public Integer findTypeBlogs(Integer tid) {
        return blogDao.findTypeBlogs(tid);
    }

    @Override
    public List<HashMap<String, Object>> listBlogs(int page, int limit, Blog blog) {
        Map<String, Object> map = new HashMap<>();
        int begin = (page - 1) * limit;
        map.put("begin", begin);
        map.put("limit", limit);
        map.put("blog", blog);
        return blogDao.listBlogs(map);
    }

    @Override
    public Integer countBlog(Blog blog) {
        return blogDao.countBlog(blog);
    }

    @Override
    public void deleteById(Integer bid) {
        blogDao.deleteById(bid);
    }

    @Override
    public void updateBlog(Blog blog) {
        blogDao.updateBlog(blog);
    }

    @Override
    public BlogDto findById(Integer bid) {
        return blogDao.findById(bid);
    }

    @Override
    public void updateView(Integer bid) {
        blogDao.updateView(bid);
    }

    @Override
    public List<HashMap<String, Object>> getBlogs() {
        return blogDao.getBlogs();
    }

}
