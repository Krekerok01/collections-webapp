package com.varvara.controller;

import com.varvara.entity.Collection;
import com.varvara.entity.Role;
import com.varvara.entity.User;
import com.varvara.service.CollectionServiceImpl;
import com.varvara.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.varvara.config.CustomAuthenticationSuccessHandler.authenticationUserName;

@Controller
@RequestMapping("user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/info")
    public String showInfoPage(Model model){

        User user = userService.findByUsername(authenticationUserName);

        model.addAttribute("user", user);
        model.addAttribute("stringOfUserRoles", userService.getStringOfUserRoles(user));
        model.addAttribute("collections", user.getCollections());

        return "user-info-page";
    }
}
