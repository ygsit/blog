package com.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/type")
public class TypeController {

    @RequestMapping("/toType")
    public String toType(){
        return "type/type";
    }
}
