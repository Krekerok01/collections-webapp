package com.varvara.controller;

import com.varvara.entity.Comment;
import com.varvara.entity.Item;
import com.varvara.entity.User;
import com.varvara.service.UserServiceImpl;
import com.varvara.service.interfaces.CommentService;
import com.varvara.service.interfaces.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static com.varvara.config.CustomAuthenticationSuccessHandler.authenticationUserId;

@Controller
@RequestMapping("item")
public class ItemController {

    private Item item;
    private User user;


    private ItemService itemService;
    private CommentService commentService;
    private UserServiceImpl userService;

    @Autowired
    public ItemController(ItemService itemService, CommentService commentService, UserServiceImpl userService) {
        this.itemService = itemService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping("/showItemInfo")
    public String showItemInfo(@RequestParam(value = "itemId") int itemId, Model model){

        model.addAttribute("item", itemService.getItemById(itemId));
        return "item-info-page";
    }

    @GetMapping("/addCommentToTheItem")
    public String addCommentToTheItem(@RequestParam(value = "itemId") int itemId, Model model){

        item = itemService.getItemById(itemId);
        user = userService.findById(authenticationUserId);
        //user = item.getCollection().getUsers().get(0);
        model.addAttribute("comment", new Comment());

        return "comment-add-page";
    }

    @PostMapping("/saveComment")
    public String saveComment(@ModelAttribute("comment") Comment comment,  RedirectAttributes redirectAttributes){

        commentService.saveComment(comment, user, item);
        redirectAttributes.addAttribute("itemId", item.getId());

        return "redirect:showItemInfo" ;
    }
}
