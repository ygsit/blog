package com.blog.dao;

import com.blog.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserDao {

    //查询用户名是否存在
    User usernameIsExist(String username);

    //重置密码
    void resetPassword(User user);

    //用户注册
    void userRegister(User user);

    //用户登录
    User userLogin(User user);

    //查询所有用户
    List<User> findAll(Map<String, Object> map);

    //查询用户总条数
    Integer findTotalCount(User user);

    //根据id删除用户
    void deleteById(int uid);

    //根据id查询
    User findById(int uid);

    //用户信息修改
    void userupdate(User user);

    //根据id查询姓名
    String findUsernameByUid(Integer uid);

}
