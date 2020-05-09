package com.blog.controller;

import com.blog.domain.BlogDto;
import com.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private BlogService blogService;

    @RequestMapping(value = "/toCommentPage/{id}", method = RequestMethod.GET)
    public String toCommentPage(@PathVariable("id") Integer bid, Model model){
        //查询id对应的博客信息
        BlogDto blog = blogService.findById(bid);
        //浏览数+1
        blogService.updateView(bid);
        //存值
        model.addAttribute("blog", blog);
        return "comment/commentPage";
    }
}
