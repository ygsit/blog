package com.blog.controller;

import com.blog.domain.BlogDto;
import com.blog.domain.Type;
import com.blog.domain.TypeDto;
import com.blog.service.BlogService;
import com.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
            String substring = content.substring(0, 19) + "....";
            blog.setContent(substring);
        }
        map.put("blogs", blogs);
        model.addAttribute("map", map);
        return "type/type";
    }

}
