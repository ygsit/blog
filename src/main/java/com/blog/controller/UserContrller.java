package com.blog.controller;

import com.blog.domain.User;
import com.blog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserContrller {

    @Autowired
    private UserService userService;

    //判断用户名是否存在
    @RequestMapping("/usernameIsExist")
    public void usernameIsExist(String username, HttpServletResponse response) {
        try {
            User user = userService.usernameIsExist(username);
            if (user == null) {
                //不存在
                response.getWriter().write("false");
            } else {
                response.getWriter().write("true");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //重置密码
    @RequestMapping("/resetPassword")
    public String resetPassword(User user) {
        userService.resetPassword(user);
        return "login";
    }

    //用户注册
    @RequestMapping("/userRegister")
    public String userRegister(User user) {
        userService.userRegister(user);
        return "login";
    }

    //用户登录
    @RequestMapping("/userLogin")
    public String userLogin(User user, String checkCode, Model model, HttpServletRequest request) {
        String verifyCode = (String)request.getSession().getAttribute("verifyCode");
        request.getSession().removeAttribute("verifyCode");
        if(!verifyCode.equalsIgnoreCase(checkCode)){
            model.addAttribute("login_err", "验证码错误!");
            //跳转到当前登录页面
            return "login";
        }
        User loginUser = userService.userLogin(user);
        if (loginUser == null) {
            model.addAttribute("login_err", "用户名或密码错误!");
            //跳转到当前登录页面
            return "login";
        }
        request.getSession().setAttribute("user", loginUser);
        return "redirect:/";
    }

    //跳到用户列表
    @RequestMapping("/toUserList")
    public String toUserList() {
        return "user/userList";
    }

    //查询用户列表
    @RequestMapping("/findAll")
    @ResponseBody
    public String findAll(int page, int limit, User user) {
        List<User> userList = userService.findAll(page, limit, user);
        Map<String, Object> map = new HashMap<String, Object>();
        ObjectMapper mapper = new ObjectMapper();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", userService.findTotalCount(user));
        map.put("data", userList);
        String value = null;
        try {
            value = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return value;
    }

    //根据id删除
    @RequestMapping("/deleteById")
    @ResponseBody
    public void deleteById(int uid) {
        userService.deleteById(uid);
    }

    //根据id查询一个
    @RequestMapping("/findById")
    public void findById(int uid) {
        User user = userService.findById(uid);
    }

    //用户添加
    @RequestMapping("/userAdd")
    public void userAdd(User user) {
        userService.userRegister(user);
    }

    //用户信息修改
    @RequestMapping("/userupdate")
    @ResponseBody
    public void userupdate(User user){
        userService.userupdate(user);
    }

    //跳转到我的信息页
    @RequestMapping("/showMe")
    public String showMe(int uid, Model model){
        User findUser = userService.findById(uid);
        model.addAttribute("findUser", findUser);
        return "user/myInfo";
    }

    //退出
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return "login";
    }

}
