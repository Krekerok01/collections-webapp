package com.varvara.controller;

import com.varvara.entity.Collection;
import com.varvara.entity.Role;
import com.varvara.entity.User;
import com.varvara.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

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

        List<Role> roles = (List<Role>) user.getRoles();

        String rolesString = "";

        for (Role r: roles){
            rolesString += r.getName() + " ";
        }

        model.addAttribute("rolesString", rolesString);

        List<Collection> collections = user.getCollections();
        model.addAttribute("collections", collections);

        return "user-info-page";
    }


    @GetMapping("/showFormForAddCollection")
    public String showFormForAddCollection(Model model){

        Collection collection = new Collection();

        model.addAttribute("collection", collection);

        return "collection-add-form";
    }

    @PostMapping("/saveCollection")
    public String saveCollection(@ModelAttribute("collection") @Valid  Collection collection, BindingResult result, Model model){

        if (result.hasErrors()){
            return "collection-add-form";
        }
        User user = userService.findByUsername(authenticationUserName);
        List<Collection> userCollections = user.getCollections();

        userCollections.add(collection);
        userService.saveUser(user);

        return "redirect:/user/info";

    }
}
