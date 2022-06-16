package com.varvara.controller;

import com.varvara.entity.Collection;
import com.varvara.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import com.varvara.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.varvara.config.CustomAuthenticationSuccessHandler.authenticationUserName;

@Controller
public class MainController {

    private UserService userService;

    @Autowired
    public MainController(@Lazy UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showMainPage(Model model){

        List<User> users = userService.getAllUsers();
        List<String> collectionsString = new ArrayList<>();


        for (User u: users){
            String username = u.getUsername();
            List<Collection> userCollections = u.getCollections();


            if (!userCollections.isEmpty()){
                for (Collection collection : userCollections){
                    StringBuilder sb = new StringBuilder();

                    sb.append("Owner : ");
                    sb.append(username);
                    sb.append(", Collection: ");
                    sb.append("name - ");
                    sb.append(collection.getName());
                    sb.append("theme - ");
                    sb.append(collection.getTheme());
                    sb.append(", description - ");
                    sb.append(collection.getDescription());

                    collectionsString.add(sb.toString());
                }

            }
        }

        model.addAttribute("collectionsString", collectionsString);

        return "main-page";
    }
}
