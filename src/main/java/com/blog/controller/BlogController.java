package com.blog.controller;

import com.blog.domain.Blog;
import com.blog.domain.Type;
import com.blog.service.BlogService;
import com.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/blog")
@Controller
public class BlogController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    //写博客
    @RequestMapping("/writeBlog")
    public String writeBlog(Model model){
        List<Type> types = typeService.findAll();
        model.addAttribute("types", types);
        return "blog/writeBlog";
    }

    //保存博客
    @RequestMapping("saveBlog")
    public String saveBlog(Blog blog){
        blogService.saveBlog(blog);
        return "blog/writeSuccess";
    }

    //跳转到我的博客
    @RequestMapping("/showMyBlogs")
    public String showMyBlogs(int uid){
        List<Blog> blogs = blogService.showMyBlogs(uid);
        return "blog/myBlogs";
    }

}
