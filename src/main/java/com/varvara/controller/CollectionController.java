package com.varvara.controller;

import com.varvara.entity.Collection;
import com.varvara.entity.Item;
import com.varvara.service.interfaces.CollectionService;
import com.varvara.service.interfaces.ItemService;
import com.varvara.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@Controller
@RequestMapping("user")
public class CollectionController {

    private Collection collection;

    private UserServiceImpl userServiceImpl;
    private CollectionService collectionService;
    private ItemService itemService;


    @Autowired
    public CollectionController(UserServiceImpl userServiceImpl, CollectionService collectionService, ItemService itemService) {
        this.userServiceImpl = userServiceImpl;
        this.collectionService = collectionService;
        this.itemService = itemService;
    }



    @GetMapping("/showFormForAddCollection")
    public String showFormForAddCollection(Model model){

        model.addAttribute("collection", new Collection());
        model.addAttribute("themeslist", collectionService.getThemesNamesList());

        return "collection-add-form";
    }

    @PostMapping("/saveCollection")
    public String saveCollection(@ModelAttribute("collection") @Valid Collection collection, BindingResult result, Model model){

        if (result.hasErrors()){
            model.addAttribute("themeslist",  collectionService.getThemesNamesList());
            return "collection-add-form";
        }

        userServiceImpl.saveCollectionToTheUser(collection);

        return "redirect:/user/info";

    }

    @GetMapping("/showItems")
    public String showItems(@RequestParam("collectionId") int collectionId, Model model){

        Collection collection = collectionService.getCollectionById(collectionId);
        model.addAttribute("collection", collection);
        model.addAttribute("items", collection.getItems());

        return "items-page";
    }



    @GetMapping("/addItem")
    public String addItem(@RequestParam("collectionId") int collectionId, Model model){

        collection = collectionService.getCollectionById(collectionId);
        Item item = new Item();
        model.addAttribute("item", item);

        return "item-add-form";
    }

    @PostMapping("/saveItem")
    public String saveItem(@ModelAttribute("item") Item item, @RequestParam(value = "tagsString") String tagsString){

        itemService.setTagsAndCollectionAndSaveItem(item, tagsString, collection);
        return "redirect:/user/info";
    }


    @GetMapping("/deleteItem")
    public String deleteItem(@RequestParam("itemId") int itemId){

        itemService.deleteItemById(itemId);
        return "redirect:/user/info";
    }


    @GetMapping("/deleteCollection")
    public String deleteCollection(@RequestParam("collectionId") int collectionId){

        collectionService.deleteCollectionById(collectionId);
        return "redirect:/user/info";
    }
}
