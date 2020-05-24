package com.blog.controller;

import com.blog.domain.BlogDto;
import com.blog.domain.Comment;
import com.blog.domain.CommentDto;
import com.blog.service.BlogService;
import com.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/toCommentPage/{id}/{isView}", method = RequestMethod.GET)
    public String toCommentPage(@PathVariable("id") Integer bid, @PathVariable("isView") Integer isView, Model model){
        //添加评论不更新浏览数(1表示是从页面进来的，0表示添加评论刷新的)
        if(isView == 1){
            //浏览数+1
            blogService.updateView(bid);
        }
        //查询id对应的博客信息
        BlogDto blog = blogService.findById(bid);
        //根据id查看对应的所有评论信息
        List<CommentDto> comments = commentService.listComments(bid);
        //根评论信息
        List<CommentDto> rootComments = new ArrayList<>();
        for (CommentDto comment : comments) {
            if(comment.getRootId() == null){
                rootComments.add(comment);
            }
        }
        //根评论下的评论信息
        List<CommentDto> childrenComments = new ArrayList<>();
        for (CommentDto comment : comments) {
            if(comment.getRootId() != null){
                //找到parentUsername
                String parentUsername = commentService.getParentUsername(comment.getParentId());
                comment.setParentUsername(parentUsername);
                childrenComments.add(comment);
            }
        }
        //存值
        model.addAttribute("blog", blog);
        model.addAttribute("rootComments", rootComments);
        model.addAttribute("childrenComments", childrenComments);
        return "comment/commentPage";
    }

    @RequestMapping(value = "/submitComment", method = RequestMethod.POST)
    public String submitComment(Comment comment){
        //保存评论
        commentService.saveComment(comment);
        return "redirect:/comment/toCommentPage/"+comment.getBid()+"/0";
    }
}
