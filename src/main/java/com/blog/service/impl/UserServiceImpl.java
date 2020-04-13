package com.blog.service.impl;

import com.blog.dao.UserDao;
import com.blog.domain.User;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User usernameIsExist(String username) {
        return userDao.usernameIsExist(username);
    }

    @Override
    public void resetPassword(User user) {
        userDao.resetPassword(user);
    }

    @Override
    public void userRegister(User user) {
        userDao.userRegister(user);
    }

    @Override
    public User userLogin(User user) {
        return userDao.userLogin(user);
    }

    @Override
    public List<User> findAll(int page, int limit, User user) {
        Map<String, Object> map = new HashMap<>();
        int begin = (page - 1) * limit;
        map.put("begin", begin);
        map.put("limit", limit);
        map.put("user", user);
        return userDao.findAll(map);
    }

    @Override
    public Integer findTotalCount(User user) {
        return userDao.findTotalCount(user);
    }

    @Override
    public void deleteById(int uid) {
        userDao.deleteById(uid);
    }

    @Override
    public User findById(int uid) {
        return userDao.findById(uid);
    }

    @Override
    public void userupdate(User user) {
        userDao.userupdate(user);
    }

    @Override
    public String findUsernameByUid(Integer uid) {
        return userDao.findUsernameByUid(uid);
    }
}
