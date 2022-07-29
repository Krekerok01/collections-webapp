package com.varvara.controller;

import com.varvara.entity.User;
import com.varvara.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.varvara.config.CustomAuthenticationSuccessHandler.authenticationUserName;

@Controller
@RequestMapping("user")
public class UserController {

    private UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/info")
    public String showInfoPage(Model model){
        User user = userServiceImpl.findByUsername(authenticationUserName);
        model.addAttribute("user", user);
        model.addAttribute("stringOfUserRoles", userServiceImpl.getStringOfUserRoles(user));
        model.addAttribute("collections", user.getCollections());
        return "user-info-page";
    }
}
