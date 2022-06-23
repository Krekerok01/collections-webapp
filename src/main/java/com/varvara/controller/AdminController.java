package com.varvara.controller;


import com.varvara.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import static com.varvara.config.CustomAuthenticationSuccessHandler.authenticationUserId;
import static com.varvara.config.CustomAuthenticationSuccessHandler.authenticationUserName;

@Controller
@RequestMapping("/users")
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/list")
    public String showListPage(Model model){

        model.addAttribute("users", userService.getAllUsers());

        return "users_list.html";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("userId") int id){

        userService.delete(id);

        if (id == authenticationUserId) {
            return "redirect:/login";
        } else {
            return "redirect:/users/list";
        }
    }


    @GetMapping("/blockUser")
    public String blockUser(@RequestParam("username") String username){

        userService.blockUser(username);

        if (username.equals(authenticationUserName)){
            return "redirect:/login";
        } else {
            return "redirect:/users/list";
        }
    }

    @GetMapping("/unblockUser")
    public String unblockUser(@RequestParam("username") String username){

        userService.unblockUser(username);

        return "redirect:/users/list";
    }

    @GetMapping("/addToAdmins")
    public String addToAdmins(@RequestParam("username") String username){

        userService.addUserToAdmins(username);

        return "redirect:/users/list";
    }


    @GetMapping("/removeFromAdmins")
    public String removeFromAdmins(@RequestParam("username") String username){

        userService.removeUserFromAdmins(username);

        return "redirect:/users/list";
    }
}
