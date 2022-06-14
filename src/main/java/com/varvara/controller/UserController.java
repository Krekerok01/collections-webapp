package com.varvara.controller;

import com.varvara.entity.User;
import com.varvara.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.varvara.config.CustomAuthenticationSuccessHandler.authenticationUserName;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public String showInfoPage(Model model){

        User user = userService.findByUsername(authenticationUserName);

        model.addAttribute("user", user);

        return "user-info-page";
    }
}
