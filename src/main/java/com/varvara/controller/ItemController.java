package com.varvara.controller;

import com.varvara.entity.*;
import com.varvara.service.UserServiceImpl;
import com.varvara.service.interfaces.CommentService;
import com.varvara.service.interfaces.ItemService;
import com.varvara.service.interfaces.OtherFieldValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



import static com.varvara.config.CustomAuthenticationSuccessHandler.authenticationUserId;

@Controller
@RequestMapping("item")
public class ItemController {

    private Item item;
    private User user;


    private ItemService itemService;
    private CommentService commentService;
    private UserServiceImpl userService;
    private OtherFieldValueService otherFieldValueService;

    @Autowired
    public ItemController(ItemService itemService, CommentService commentService, UserServiceImpl userService, OtherFieldValueService otherFieldValueService) {
        this.itemService = itemService;
        this.commentService = commentService;
        this.userService = userService;
        this.otherFieldValueService = otherFieldValueService;
    }

    @GetMapping("/showItemInfo")
    public String showItemInfo(@RequestParam(value = "itemId") int itemId, Model model){

        item = itemService.getItemById(itemId);

        model.addAttribute("comment", new Comment());
        model.addAttribute("item", itemService.getItemById(itemId));
        model.addAttribute("map", otherFieldValueService.getOtherFieldsValuesMap(item));
        model.addAttribute("itemComments", commentService.getCommentsToThisItem(itemId));
        return "item-info-page";
    }



    @PostMapping("/saveComment")
    public String saveComment(@ModelAttribute("comment") Comment comment,  RedirectAttributes redirectAttributes){

        user = userService.findById(authenticationUserId);

        commentService.saveComment(comment, user, item);
        redirectAttributes.addAttribute("itemId", item.getId());

        return "redirect:showItemInfo" ;
    }
}
