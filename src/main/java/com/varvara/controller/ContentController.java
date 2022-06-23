package com.varvara.controller;

import com.varvara.entity.Collection;
import com.varvara.entity.User;
import com.varvara.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import com.varvara.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ContentController {

    private UserService userService;
    private TagService tagService;

    @Autowired
    public ContentController(UserService userService, TagService tagService) {
        this.userService = userService;
        this.tagService = tagService;
    }

    @GetMapping("/")
    public String showMainPage(Model model){

        List<String> stringListOfAllCollections = userService.getAllCollections();
        List<String> listOfStringTags = tagService.getStringListOfAllTags();


        model.addAttribute("collectionsString", stringListOfAllCollections);
        model.addAttribute("tagsList", listOfStringTags);


        return "first-content-page";
    }

}
