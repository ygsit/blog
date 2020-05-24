package com.blog.dao;

import com.blog.domain.Comment;
import com.blog.domain.CommentDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentDao {

    //根据id查看对应的评论信息
    List<CommentDto> listComments(Integer bid);

    //保存评论
    void saveComment(Comment comment);

    //根据父id找到评论对应的用户名
    String getParentUsername(Integer parentId);
}
