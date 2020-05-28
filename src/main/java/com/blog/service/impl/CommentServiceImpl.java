package com.blog.service.impl;

import com.blog.dao.CommentDao;
import com.blog.domain.Comment;
import com.blog.domain.CommentDto;
import com.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<CommentDto> listManageComments(Integer page, Integer limit, Comment comment) {
        Map<String, Object> map = new HashMap<>();
        int begin = (page - 1) * limit;
        map.put("begin", begin);
        map.put("limit", limit);
        map.put("comment", comment);
        return commentDao.listManageComments(map);
    }

    @Override
    public Integer countComment(Comment comment) {
        return commentDao.countComment(comment);
    }

    @Override
    public void deleteComment(Integer cid, List<Comment> list) {
        Map<String, Object> map = new HashMap<>();
        map.put("cid", cid);
        map.put("list", list);
        commentDao.deleteComment(map);
    }

    @Override
    public Comment rootComment(Integer cid) {
        return commentDao.rootComment(cid);
    }

    @Override
    public void deleteRootComment(Integer cid, List<Comment> list) {
        Map<String, Object> map = new HashMap<>();
        map.put("cid", cid);
        map.put("list", list);
        commentDao.deleteRootComment(map);
    }

    @Override
    public List<Comment> listRootComments(Integer cid) {
        return commentDao.listRootComments(cid);
    }

    @Override
    public List<Comment> listChildrenComments(Integer cid) {
        return commentDao.listChildrenComments(cid);
    }
}
