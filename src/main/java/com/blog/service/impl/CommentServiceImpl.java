package com.blog.service.impl;

import com.blog.dao.CommentDao;
import com.blog.domain.Comment;
import com.blog.domain.CommentDto;
import com.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public List<CommentDto> listComments(Integer bid) {
        return commentDao.listComments(bid);
    }

    @Override
    public void saveComment(Comment comment) {
        commentDao.saveComment(comment);
    }

    @Override
    public String getParentUsername(Integer parentId) {
        return commentDao.getParentUsername(parentId);
    }
}
