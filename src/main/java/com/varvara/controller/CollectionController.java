package com.varvara.controller;

import com.varvara.entity.Collection;
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
public class CollectionController {

    @Autowired
    private UserService userService;

    @Autowired
    private CollectionServiceImpl collectionService;


    @GetMapping("/showFormForAddCollection")
    public String showFormForAddCollection(Model model){

        Collection collection = new Collection();

        model.addAttribute("collection", collection);

        return "collection-add-form";
    }

    @PostMapping("/saveCollection")
    public String saveCollection(@ModelAttribute("collection") @Valid Collection collection, BindingResult result, Model model){

        if (result.hasErrors()){
            return "collection-add-form";
        }
        User user = userService.findByUsername(authenticationUserName);
        List<Collection> userCollections = user.getCollections();

        userCollections.add(collection);
        userService.saveUser(user);

        return "redirect:/user/info";

    }

    @GetMapping("/addItem")
    public String addItem(@RequestParam("collectionId") int collectionId){

        Collection collection = collectionService.getCollectionById(collectionId);

        return "item-add-form";
    }

}
