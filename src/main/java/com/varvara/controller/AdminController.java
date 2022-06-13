package com.varvara.controller;


import com.varvara.entity.User;
import com.varvara.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(@Lazy UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/list")
    public String showListPage(Model model){


        List<User> users = userService.getAllUsers();


        // add to the spring model
        model.addAttribute("users", users);


        return "users_list.html";
    }

}
