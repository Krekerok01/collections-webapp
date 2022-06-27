package com.varvara.controller;


import com.varvara.service.CollectionService;
import com.varvara.service.ItemService;
import com.varvara.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import com.varvara.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class ContentController {

    private UserService userService;
    private TagService tagService;
    private CollectionService collectionService;
    private ItemService itemService;

    @Autowired
    public ContentController(UserService userService, TagService tagService, CollectionService collectionService, ItemService itemService) {
        this.userService = userService;
        this.tagService = tagService;
        this.collectionService = collectionService;
        this.itemService = itemService;
    }



    @GetMapping("/")
    public String showMainPage(Model model){

        model.addAttribute("largestCollectionsList", collectionService.getLargestCollections());
        model.addAttribute("lastAddedItemsList", itemService.getLastAddedItems());
        model.addAttribute("tagsSet", tagService.getSetOfAllTags());


        return "first-content-page";
    }

    @GetMapping("/showItemsByTag")
    public String showItemsByTag(@RequestParam("tagId") int tagId, Model model){

        String tagName = tagService.getTagById(tagId).getName();

        model.addAttribute("tagName", tagName);
        model.addAttribute("itemsThatContainsSpecificTagList", itemService.getItemsThatContainsTheSpecificTag(tagName));

        return "items-that-contains-a-specific-tag-page.html";
    }

}
