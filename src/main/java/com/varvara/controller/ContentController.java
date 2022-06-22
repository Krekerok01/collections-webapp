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

        List<User> users = userService.getAllUsers();
        List<String> collectionsString = workWithCollections(users);

        List<String> listOfStringTags = tagService.getStringListOfAllTags();


        model.addAttribute("collectionsString", collectionsString);
        model.addAttribute("tagsList", listOfStringTags);


        return "main-page";
    }




    private List<String> workWithCollections( List<User> users){
        List<String> collectionsString = new ArrayList<>();

        for (User u: users){
            String username = u.getUsername();
            List<Collection> userCollections = u.getCollections();

            if (!userCollections.isEmpty()){
                for (Collection collection : userCollections){
                    collectionsString.add(buildCollectionString(username, collection));
                }
            }
        }

        return collectionsString;
    }

    private String buildCollectionString(String username, Collection collection){
        StringBuilder sb = new StringBuilder();

        sb.append("Owner : ").append(username);
        sb.append(", Collection: ").append("name - ").append(collection.getName());
        sb.append(", theme - ").append(collection.getTheme());
        sb.append(", description - ").append(collection.getDescription());

        return sb.toString();
    }
}
