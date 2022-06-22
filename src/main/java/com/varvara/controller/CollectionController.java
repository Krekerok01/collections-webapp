package com.varvara.controller;

import com.varvara.entity.Collection;
import com.varvara.entity.Item;
import com.varvara.entity.Tag;
import com.varvara.entity.User;
import com.varvara.service.CollectionServiceImpl;
import com.varvara.service.ItemService;
import com.varvara.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

    @Autowired
    private ItemService itemService;


    @GetMapping("/showFormForAddCollection")
    public String showFormForAddCollection(Model model){

        List<String> themes = getThemesListFromFile();

        Collection collection = new Collection();

        model.addAttribute("collection", collection);
        model.addAttribute("themeslist", themes);

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



    @GetMapping("/addItem")
    public String addItem(@RequestParam("collectionId") int collectionId, Model model){

        collection = collectionService.getCollectionById(collectionId);
        Item item = new Item();

        model.addAttribute("item", item);

        return "item-add-form";
    }

    @PostMapping("/saveItem")
    public String saveItem(@ModelAttribute("item") Item item, @RequestParam(value = "tagsString") String tagsString){

        List<Tag> tags = getTagsFromTagsString(item, tagsString);


        item.setTags(tags);
        item.setCollection(collection);

        itemService.saveItem(item);

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

    private List<String> getThemesListFromFile(){
        List<String> themes = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("src/main/resources/static/themes.txt")));

            String s = reader.readLine();
            while (s != null){
                themes.add(s);
                s = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return themes;
    }

    private List<Tag> getTagsFromTagsString(Item item, String tagsString){

        List<Tag> tags = new ArrayList<>();


        List<String> stringTagsList = Arrays.asList(tagsString.split("[, ]+"));
        for (String s: stringTagsList){
            Tag tag = new Tag();
            tag.setName(s);
            tag.setItem(item);
            tags.add(tag);
        }

        return tags;
    }

}
