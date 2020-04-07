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

/**
 * 页面控制器
 */
@Controller
public class RouterController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @RequestMapping({"/", "/index", "/index.html"})
    public String index(Integer currentPage, Integer pageCount, Model model){
        if(currentPage == null || currentPage < 1){
            currentPage = 1;
        }
        if(pageCount == null){
            pageCount = 5;
        }
        Map<String, Object> map = new HashMap<>();
        //查询总博客数
        int tatalCounts = blogService.findTotalCounts();
        //查询最大页数
        int totalPage = (tatalCounts % pageCount == 0) ? (tatalCounts / pageCount) : (tatalCounts / pageCount) + 1;
        if(currentPage > totalPage) {
            currentPage = totalPage;
        }
        map.put("counts", tatalCounts);

        //查询一页的博客
        List<BlogDto> blogs = blogService.findByPage(currentPage, pageCount);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        for (BlogDto blog : blogs) {
            String content = blog.getContent();
            if(content.length() > 20){
                String substring = content.substring(0, 19) + "....";
                blog.setContent(substring);
            }
            String format = simpleDateFormat.format(blog.getCreateTime());
            blog.setShowTime(format);
        }
        map.put("blogs", blogs);

        //存入当前页
        map.put("currentPage", currentPage);
        //存入最大页
        map.put("totalPage", totalPage);

        //查询分类数量，只显示5条
        List<Type> types = typeService.findOnePage();
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

        model.addAttribute("map", map);
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}
