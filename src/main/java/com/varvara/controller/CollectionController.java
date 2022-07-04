package com.varvara.controller;

import com.varvara.entity.Collection;
import com.varvara.entity.Item;
import com.varvara.entity.OtherField;
import com.varvara.entity.User;
import com.varvara.service.interfaces.CollectionService;
import com.varvara.service.interfaces.ItemService;
import com.varvara.service.UserServiceImpl;
import com.varvara.service.interfaces.OtherFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.NonUniqueResultException;
import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import static com.varvara.config.CustomAuthenticationSuccessHandler.authenticationUserName;


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
    public String saveCollection(@ModelAttribute("collection") @Valid Collection collection, BindingResult result,
                                 @RequestParam(value = "firstAdditionalStringType", required = false) String firstAdditionalStringType,
                                 @RequestParam(value = "firstAdditionalStringName", required = false) String firstAdditionalStringName,
                                 @RequestParam(value = "secondAdditionalStringType", required = false) String secondAdditionalStringType,
                                 @RequestParam(value = "secondAdditionalStringName", required = false) String secondAdditionalStringName,
                                 @RequestParam(value = "thirdAdditionalStringType", required = false) String thirdAdditionalStringType,
                                 @RequestParam(value = "thirdAdditionalStringName", required = false) String thirdAdditionalStringName,
                                 @RequestParam(value = "firstAdditionalIntegerType", required = false) String firstAdditionalIntegerType,
                                 @RequestParam(value = "firstAdditionalIntegerName", required = false) String firstAdditionalIntegerName,
                                 @RequestParam(value = "secondAdditionalIntegerType", required = false) String secondAdditionalIntegerType,
                                 @RequestParam(value = "secondAdditionalIntegerName", required = false) String secondAdditionalIntegerName,
                                 @RequestParam(value = "thirdAdditionalIntegerType", required = false) String thirdAdditionalIntegerType,
                                 @RequestParam(value = "thirdAdditionalIntegerName", required = false) String thirdAdditionalIntegerName,
                                 @RequestParam(value = "firstAdditionalMultilineTextType", required = false) String firstAdditionalMultilineTextType,
                                 @RequestParam(value = "firstAdditionalMultilineTextName", required = false) String firstAdditionalMultilineTextName,
                                 @RequestParam(value = "secondAdditionalMultilineTextType", required = false) String secondAdditionalMultilineTextType,
                                 @RequestParam(value = "secondAdditionalMultilineTextName", required = false) String secondAdditionalMultilineTextName,
                                 @RequestParam(value = "thirdAdditionalMultilineTextType", required = false) String thirdAdditionalMultilineTextType,
                                 @RequestParam(value = "thirdAdditionalMultilineTextName", required = false) String thirdAdditionalMultilineTextName,
                                 @RequestParam(value = "firstAdditionalCheckboxType", required = false) String firstAdditionalCheckboxType,
                                 @RequestParam(value = "firstAdditionalCheckboxName", required = false) String firstAdditionalCheckboxName,
                                 @RequestParam(value = "secondAdditionalCheckboxType", required = false) String secondAdditionalCheckboxType,
                                 @RequestParam(value = "secondAdditionalCheckboxName", required = false) String secondAdditionalCheckboxName,
                                 @RequestParam(value = "thirdAdditionalCheckboxType", required = false) String thirdAdditionalCheckboxType,
                                 @RequestParam(value = "thirdAdditionalCheckboxName", required = false) String thirdAdditionalCheckboxName,
                                 @RequestParam(value = "firstAdditionalDateType", required = false) String firstAdditionalDateType,
                                 @RequestParam(value = "firstAdditionalDateName", required = false) String firstAdditionalDateName,
                                 @RequestParam(value = "secondAdditionalDateType", required = false) String secondAdditionalDateType,
                                 @RequestParam(value = "secondAdditionalDateName", required = false) String secondAdditionalDateName,
                                 @RequestParam(value = "thirdAdditionalDateType", required = false) String thirdAdditionalDateType,
                                 @RequestParam(value = "thirdAdditionalDateName", required = false) String thirdAdditionalDateName,
                                 Model model){

        if (result.hasErrors()){
            model.addAttribute("themeslist",  collectionService.getThemesNamesList());
            return "collection-add-form";
        }

        userServiceImpl.saveCollectionToTheUser(collection,
                firstAdditionalStringType, firstAdditionalStringName, secondAdditionalStringType, secondAdditionalStringName, thirdAdditionalStringType, thirdAdditionalStringName,
                firstAdditionalIntegerType, firstAdditionalIntegerName, secondAdditionalIntegerType, secondAdditionalIntegerName, thirdAdditionalIntegerType, thirdAdditionalIntegerName,
                firstAdditionalMultilineTextType, firstAdditionalMultilineTextName, secondAdditionalMultilineTextType, secondAdditionalMultilineTextName, thirdAdditionalMultilineTextType, thirdAdditionalMultilineTextName,
                firstAdditionalCheckboxType, firstAdditionalCheckboxName, secondAdditionalCheckboxType, secondAdditionalCheckboxName, thirdAdditionalCheckboxType, thirdAdditionalCheckboxName,
                firstAdditionalDateType, firstAdditionalDateName, secondAdditionalDateType, secondAdditionalDateName, thirdAdditionalDateType, thirdAdditionalDateName);


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

        List<OtherField> otherFields = collection.getOtherFields();

        List<OtherField> otherFieldsWithoutCheckBox = new LinkedList<>();
        List<OtherField> checkBoxesFields = new LinkedList<>();

        for (OtherField o: otherFields){
            if (o.getType().equals("checkbox")){
                checkBoxesFields.add(o);
            } else {
                otherFieldsWithoutCheckBox.add(o);
            }

        }


        model.addAttribute("item", new Item());
        //model.addAttribute("otherFields", collection.getOtherFields());
        model.addAttribute("otherFields", otherFieldsWithoutCheckBox);
        model.addAttribute("checkBoxesFields", checkBoxesFields);

        return "item-add-form";
    }

    @PostMapping("/saveItem")
    public String saveItem(@ModelAttribute("item") Item item,
                           @RequestParam(value = "tagsString") String tagsString,
                           @RequestParam(value = "enterValues", required = false) LinkedList<String> enterValues,
                           @RequestParam(value = "checkboxValue", required = false) String checkboxValue,
                           RedirectAttributes redirectAttributes){

        System.out.println(enterValues.size());
        System.out.println(checkboxValue);

        if (enterValues != null){
            if (checkboxValue != null && checkboxValue != "") {
                enterValues.add(checkboxValue);
            }

            itemService.setTagsAndCollectionAndOtherFieldsAndSaveItem(item, tagsString, collection, enterValues);
        } else {
            itemService.setTagsAndCollectionAndSaveItem(item, tagsString, collection);
        }


        redirectAttributes.addAttribute("collectionId", collection.getId());

        return "redirect:/user/showItems";
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
