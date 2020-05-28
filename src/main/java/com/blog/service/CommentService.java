package com.blog.service;

import com.blog.domain.Comment;
import com.blog.domain.CommentDto;

import java.util.HashMap;
import java.util.List;

public interface CommentService {

    //根据id查看对应的评论信息
    List<CommentDto> listComments(Integer bid);

    //保存评论
    void saveComment(Comment comment);

    //根据父id找到评论对应的用户名
    String getParentUsername(Integer parentId);

    //评论列表
    List<CommentDto> listManageComments(Integer page, Integer limit, Comment comment);

    //根据传入信息查询评论数量
    Integer countComment(Comment comment);

    //删除评论及子评论
    void deleteComment(Integer cid, List<Comment> list);

    //根据cid查询判断是否是根评论
    Comment rootComment(Integer cid);

    //删除根评论及子评论
    void deleteRootComment(Integer cid, List<Comment> list);

    //查询所有根评论下的评论
    List<Comment> listRootComments(Integer cid);

    //查询所有评论下的子评论
    List<Comment> listChildrenComments(Integer cid);
}
