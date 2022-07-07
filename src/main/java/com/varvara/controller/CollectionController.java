package com.varvara.controller;

import com.varvara.entity.Collection;
import com.varvara.entity.Imagen;
import com.varvara.entity.Item;
import com.varvara.service.CloudinaryService;
import com.varvara.service.ImagenService;
import com.varvara.service.interfaces.CollectionService;
import com.varvara.service.interfaces.ItemService;
import com.varvara.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedList;
import java.util.Map;


@Controller
@RequestMapping("user")
public class CollectionController {

    private Collection collection;

    private UserServiceImpl userServiceImpl;
    private CollectionService collectionService;
    private ItemService itemService;

    private CloudinaryService cloudinaryService;
    private ImagenService imagenService;



    @Autowired
    public CollectionController(UserServiceImpl userServiceImpl, CollectionService collectionService, ItemService itemService
            , CloudinaryService cloudinaryService, ImagenService imagenService) {
        this.userServiceImpl = userServiceImpl;
        this.collectionService = collectionService;
        this.itemService = itemService;

        this.cloudinaryService = cloudinaryService;
        this.imagenService = imagenService;
    }



    @GetMapping("/showFormForAddCollection")
    public String showFormForAddCollection(Model model){
        model.addAttribute("collection", new Collection());
        model.addAttribute("themeslist", collectionService.getThemesNamesList());
        return "collection-add-form";
    }

    private final Path root = Paths.get("src/main/resources/static/images");

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

                                 @RequestPart(value = "userImg") MultipartFile userImg,
                                 Model model){

        if (result.hasErrors()){
            model.addAttribute("themeslist",  collectionService.getThemesNamesList());
            for (Object object : result.getAllErrors()) {
                if(object instanceof FieldError) {
                    FieldError fieldError = (FieldError) object;

                    System.out.println(fieldError.getCode());
                }

                if(object instanceof ObjectError) {
                    ObjectError objectError = (ObjectError) object;

                    System.out.println(objectError.getCode());
                }
            }
            return "collection-add-form";
        }




        Map resultMap = null;
        try {
            resultMap = cloudinaryService.upload(userImg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url = (String)resultMap.get("url");

        Imagen imagen = new Imagen((String)resultMap.get("original_filename"), (String)resultMap.get("url"), (String)resultMap.get("public_id"));
        imagenService.save(imagen);


        collection.setImageUrl(url);




        userServiceImpl.saveCollectionToTheUser(collection,
                firstAdditionalStringType, firstAdditionalStringName, secondAdditionalStringType, secondAdditionalStringName, thirdAdditionalStringType, thirdAdditionalStringName,
                firstAdditionalIntegerType, firstAdditionalIntegerName, secondAdditionalIntegerType, secondAdditionalIntegerName, thirdAdditionalIntegerType, thirdAdditionalIntegerName,
                firstAdditionalMultilineTextType, firstAdditionalMultilineTextName, secondAdditionalMultilineTextType, secondAdditionalMultilineTextName, thirdAdditionalMultilineTextType, thirdAdditionalMultilineTextName,
                firstAdditionalCheckboxType, firstAdditionalCheckboxName, secondAdditionalCheckboxType, secondAdditionalCheckboxName, thirdAdditionalCheckboxType, thirdAdditionalCheckboxName,
                firstAdditionalDateType, firstAdditionalDateName, secondAdditionalDateType, secondAdditionalDateName, thirdAdditionalDateType, thirdAdditionalDateName);


        return "redirect:/user/info";

    }

    @GetMapping("/deleteCollection")
    public String deleteCollection(@RequestParam("collectionId") int collectionId){

        Collection collection = collectionService.getCollectionById(collectionId);
//        Imagen imagen = imagenService.getByImagenUrl(collection.getImageUrl());
//
//        if(imagen == null)
//            throw  new RuntimeException("Img not found");
//
//
//        try {
//            Map result = cloudinaryService.delete(imagen.getImagenId());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        imagenService.delete(imagen.getId());



        collectionService.deleteCollectionById(collectionId);
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

        model.addAttribute("item", new Item());
        model.addAttribute("otherFields", collectionService.getFieldsWithoutCheckBox(collection));
        model.addAttribute("checkBoxesFields", collectionService.getCheckBoxesFields(collection));
        return "item-add-form";
    }

    @PostMapping("/saveItem")
    public String saveItem(@ModelAttribute("item") Item item,
                           @RequestParam(value = "tagsString") String tagsString,
                           @RequestParam(value = "enterValues", required = false) LinkedList<String> enterValues,
                           @RequestParam(value = "checkboxValue", required = false) String checkboxValue,
                           RedirectAttributes redirectAttributes){

        itemService.checkingTheRequestParametersForNullAndCallingTheDesiredMethod(item, tagsString,collection, enterValues, checkboxValue);
        redirectAttributes.addAttribute("collectionId", collection.getId());
        return "redirect:/user/showItems";
    }


    @GetMapping("/deleteItem")
    public String deleteItem(@RequestParam("itemId") int itemId){
        itemService.deleteItemById(itemId);
        return "redirect:/user/info";
    }

}
