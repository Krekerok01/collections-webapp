package com.varvara.controller;

import com.varvara.entity.Collection;
import com.varvara.entity.Item;
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

    private Collection collection;

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

    @GetMapping("/showItems")
    public String showItems(@RequestParam("collectionId") int collectionId, Model model){

        Collection collection = collectionService.getCollectionById(collectionId);
        model.addAttribute("collection", collection);

        List<Item> items = collection.getItems();
        model.addAttribute("items", items);

        return "items-page";
    }

//    @GetMapping("/addItem")
//    public String addItem(@RequestParam("collectionId") int collectionId, Model model){
//
//        collection = collectionService.getCollectionById(collectionId);
//        Item item = new Item();
//
//        System.out.println(collection);
//
//        model.addAttribute("item", item);
//
//
//        return "item-add-form";
//    }


    @GetMapping("/addItem")
    public String addItem(@RequestParam("collectionId") int collectionId, Model model){

        collection = collectionService.getCollectionById(collectionId);
        Item item = new Item();

        model.addAttribute("item", item);


        return "item-add-form";
    }

    @PostMapping("/saveItem")
    public String saveItem(@ModelAttribute("item") Item item){

        System.out.println("saveItem method()");

        List<Item> items = collection.getItems();
        items.add(item);
        collection.setItems(items);

        collectionService.saveCollection(collection);

        return "redirect:/user/info";
    }

}
