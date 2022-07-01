package com.varvara.controller;


import com.varvara.entity.Collection;
import com.varvara.service.interfaces.CollectionService;
import com.varvara.service.interfaces.ItemService;
import com.varvara.service.interfaces.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class ContentController {


    private TagService tagService;
    private CollectionService collectionService;
    private ItemService itemService;

    @Autowired
    public ContentController(TagService tagService, CollectionService collectionService, ItemService itemService) {
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


    @GetMapping("/showTheSelectedCollection")
    public String showTheSelectedCollection(@RequestParam("collectionId") int collectionId, Model model){

        Collection collection = collectionService.getCollectionById(collectionId);

        model.addAttribute("collection", collection);
        model.addAttribute("items", collection.getItems());
        model.addAttribute("owner", collection.getUsers().get(0));

        return "collection-and-items-page";
    }



    @GetMapping("/showItemsByTag")
    public String showItemsByTag(@RequestParam("tagId") int tagId, Model model){

        String tagName = tagService.getTagById(tagId).getName();

        model.addAttribute("tagName", tagName);
        model.addAttribute("itemsThatContainsSpecificTagList", itemService.getItemsThatContainsTheSpecificTag(tagName));

        return "items-that-contains-a-specific-tag-page";
    }

}
