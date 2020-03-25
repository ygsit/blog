package com.blog.service.impl;

import com.blog.dao.BlogDao;
import com.blog.domain.Blog;
import com.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Override
    public void saveBlog(Blog blog) {
        blogDao.saveBlog(blog);
    }

    @Override
    public List<Blog> showMyBlogs(int uid) {
        return blogDao.showMyBlogs(uid);
    }

}
