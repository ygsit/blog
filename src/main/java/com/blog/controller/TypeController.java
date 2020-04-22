package com.blog.controller;

import com.blog.domain.*;
import com.blog.service.BlogService;
import com.blog.service.TypeService;
import com.blog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/type")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @RequestMapping("/toType")
    public String toType(Model model, Integer tid, Integer currentPage, Integer pageCount) {
        Map<String, Object> map = new HashMap<>();
        List<Type> types = typeService.findAll();
        List<TypeDto> list = new ArrayList<>();
        for (Type type : types) {
            TypeDto typeDto = new TypeDto();
            typeDto.setId(type.getTid());
            typeDto.setName(type.getName());
            //查询分类名相关博客数量
            int count = typeService.findNameCount(type.getName());
            typeDto.setCount(count);
            list.add(typeDto);
        }
        map.put("list", list);
        //查询总分类数
        int counts = typeService.findCounts();
        map.put("counts", counts);

        //分类对应的博客，并分页处理
        if(currentPage == null){
            currentPage = 1;
        }
        if(pageCount == null){
            pageCount = 3;
        }

        List<BlogDto> blogs = new ArrayList<>();
        Integer typeBlogCount = null;
        if(tid == null){
            //根据分类id，当前页一页显示的数量查询一页的博客数
            blogs = blogService.findByTid(types.get(0).getTid(), currentPage, pageCount);
            //根据分类id查询分类博客的数量
            typeBlogCount = blogService.findPageTypeBlogCount(types.get(0).getTid());
        } else {
            blogs = blogService.findByTid(tid, currentPage, pageCount);
            typeBlogCount = blogService.findPageTypeBlogCount(tid);
        }

        /*//查询总博客数
        int tatalCounts = blogService.findTotalCounts();*/
        //查询最大页数
        int totalPage = (typeBlogCount % pageCount == 0) ? (typeBlogCount / pageCount) : (typeBlogCount / pageCount) + 1;

        //存入当前页
        map.put("currentPage", currentPage);
        //存入一页显示数
        map.put("pageCount", pageCount);
        //存入最大页
        map.put("totalPage", totalPage);

        //存入分类博客的数量
        map.put("typeBlogCount", typeBlogCount);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        for (BlogDto blog : blogs) {
            String format = simpleDateFormat.format(blog.getCreateTime());
            blog.setShowTime(format);
            //处理博客内容
            String content = blog.getContent();
            if(content.length() > 20){
                String substring = content.substring(0, 19) + "....";
                blog.setContent(substring);
            }
        }
        map.put("blogs", blogs);
        model.addAttribute("map", map);
        return "type/type";
    }

    //判断分类名是否存在
    @RequestMapping("/typeNameIsExist")
    @ResponseBody
    public String typeNameIsExist(String name){
        Integer integer = typeService.typeNameIsExist(name);
        if (integer != null && integer != 0){
            //已存在
            return "true";
        }
        return "false";
    }

    //添加分类
    @RequestMapping("/typeAdd")
    @ResponseBody
    public void typeAdd(Type type){
        typeService.typeAdd(type);
    }

    //跳转
    @RequestMapping("/toTypeList")
    public String toTypeList(){
        return "type/typeList";
    }

    //查询所有
    @RequestMapping("/findAll")
    @ResponseBody
    public String findAll(int page, int limit, Type type){
        List<Type> types = typeService.findAllByPage(page, limit, type);
        List<TypePageDto> list = new ArrayList<>();
        for (Type type1 : types) {
            TypePageDto typePageDto = new TypePageDto();
            typePageDto.setTid(type1.getTid());
            typePageDto.setName(type1.getName());
            typePageDto.setCreateTime(type1.getCreateTime());
            typePageDto.setUpdateTime(type1.getUpdateTime());
            String username = userService.findUsernameByUid(type1.getUid());
            typePageDto.setUsername(username);
            list.add(typePageDto);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        ObjectMapper mapper = new ObjectMapper();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", typeService.findCountsByUid(type));
        map.put("data", list);
        String value = null;
        try {
            value = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return value;
    }

    //跳转到分类管理页面
    @RequestMapping("/toTypeManage")
    public String toTypeManage(Model model){
        model.addAttribute("manager","1");
        return "type/typeList";
    }

    //跳转到增加分类页
    @RequestMapping("/toTypeAddPage")
    public String toTypeAddPage(){
        return "type/typeAdd";
    }


    //添加分类，跳转到分类页
    @RequestMapping("/pageTypeAdd")
    public String pageTypeAdd(Type type){
        typeService.typeAdd(type);
        return "redirect:/type/toType";
    }

    //删除分类
    @RequestMapping("/deleteById")
    @ResponseBody
    public String deleteById(Integer tid){
        //查询是否有与此相关的博客
        Integer count = blogService.findTypeBlogs(tid);
        if(count != null && count != 0){
            return "false";
        }
        typeService.deleteById(tid);
        return "true";
    }


    //编辑博客
    @RequestMapping("/typeUpdate")
    @ResponseBody
    public String typeUpdate(Type type, HttpServletRequest request){
        //查询是否有与此相关的博客
        Integer count = blogService.findTypeBlogs(type.getTid());
        if(count != null && count != 0){
            return "false";
        }
        User user = (User) request.getSession().getAttribute("user");
        type.setUid(user.getUid());
        typeService.typeUpdate(type);
        return "true";
    }

}
