package com.blog.controller;

import com.blog.domain.Blog;
import com.blog.domain.Type;
import com.blog.service.BlogService;
import com.blog.service.TypeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/blog")
@Controller
public class BlogController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    /**
     * 写博客
     * @param model
     * @param blog
     * @param tname 分类名
     * @param isUpdate 是否是从更新来的
     * @param manager 是否是管理员更新
     * @return
     */
    @RequestMapping("/writeBlog")
    public String writeBlog(Model model, Blog blog, String tname, String isUpdate, String manager){
        List<Type> types = typeService.findAll();
        if(isUpdate!= null && "1".equals(isUpdate)){
            model.addAttribute("blogHref", "updateBlog");
        } else {
            model.addAttribute("blogHref", "saveBlog");
        }
        model.addAttribute("types", types);
        model.addAttribute("blog", blog);
        if(tname!= null && tname.length() > 0){
            model.addAttribute("tname", tname);
        }
        if(manager!= null){
            model.addAttribute("manager", manager);
        }
        return "blog/writeBlog";
    }

    //保存博客
    @RequestMapping("/saveBlog")
    public String saveBlog(Blog blog){
        blogService.saveBlog(blog);
        return "blog/writeSuccess";
    }

    //跳转到博客列表
    @RequestMapping("/showMyBlogs")
    public String showMyBlogs(int uid, Model model){
        model.addAttribute("uid", uid);
        return "blog/BlogList";
    }

    @RequestMapping("/listBlogs")
    @ResponseBody
    public String listBlogs(Integer page, Integer limit, Blog blog){
        if(page == null){
            page = 1;
        }
        if(limit == null){
            limit = 5;
        }
        List<HashMap<String, Object>> blogList = blogService.listBlogs(page, limit, blog);
        Map<String, Object> map = new HashMap<String, Object>();
        ObjectMapper mapper = new ObjectMapper();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", blogService.countBlog(blog));
        map.put("data", blogList);
        String value = null;
        try {
            value = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return value;
    }

    //跳转到博客管理页面
    @RequestMapping("/toBlogManage")
    public String toBlogManage(Model model){
        model.addAttribute("manager","1");
        return "blog/BlogList";
    }

    //删除博客
    @RequestMapping("/deleteById")
    @ResponseBody
    public void deleteById(Integer bid){
        blogService.deleteById(bid);
    }

    //更新博客
    @RequestMapping("/updateBlog")
    public String updateBlog(Blog blog, Model model, String manager){
        blogService.updateBlog(blog);
        if (manager != null && manager.length() > 0){
            model.addAttribute("manager","1");
        }
        return "blog/BlogList";
    }

    //查找所有博客
    @RequestMapping("/getAllBlogs")
    @ResponseBody
    public String getAllBlogs(Model model) throws JsonProcessingException {
        //查询所有博客
        List<HashMap<String, Object>> blogs = blogService.getBlogs();
        model.addAttribute("blogs", blogs);
        return new ObjectMapper().writeValueAsString(blogs);
    }
}
