package com.blog.controller;

import com.blog.domain.Blog;
import com.blog.domain.BlogDto;
import com.blog.domain.Comment;
import com.blog.domain.CommentDto;
import com.blog.service.BlogService;
import com.blog.service.CommentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;

    //博客详情页
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


    //跳到评论管理页面
    @RequestMapping("/toCommentManage")
    public String toCommemtManage(Model model){
        model.addAttribute("manager","1");
        return "comment/commentList";
    }

    //跳转到我的评论页面
    @RequestMapping("/showMyComments")
    public String showMyComments(int uid, Model model){
        model.addAttribute("uid", uid);
        return "comment/commentList";
    }

    @RequestMapping("/listComments")
    @ResponseBody
    public String listComments(Integer page, Integer limit, Comment comment, Model model){
        if(page == null){
            page = 1;
        }
        if(limit == null){
            limit = 5;
        }
        List<CommentDto> commentList = commentService.listManageComments(page, limit, comment);
        Map<String, Object> map = new HashMap<String, Object>();
        ObjectMapper mapper = new ObjectMapper();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", commentService.countComment(comment));
        map.put("data", commentList);
        String value = null;
        try {
            value = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return value;
    }

    //删除评论
    @PostMapping("/deleteComment")
    @ResponseBody
    public void deleteComment(Integer cid){
        System.out.println(cid);
        //根据cid查询判断是否是根评论
        Comment comment = commentService.rootComment(cid);
        if(comment.getRootId() == null){
            //表示是根评论
            //查询所有根评论下是否存在评论
            List<Comment> rootComments = commentService.listRootComments(cid);
            // 删除根评论下所有评论
            commentService.deleteRootComment(cid, rootComments);
        } else{
            //不是根评论
            //根据cid查询判断是否存在子评论，如果存在会将子评论一起删除
            List<Comment> childrenComments = commentService.listChildrenComments(cid);
            commentService.deleteComment(cid, childrenComments);
        }
    }
}
