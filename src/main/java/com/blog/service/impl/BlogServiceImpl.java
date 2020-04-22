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
    public List<HashMap<String, Object>> showMyBlogs(int uid) {
        return blogDao.showMyBlogs(uid);
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

}
