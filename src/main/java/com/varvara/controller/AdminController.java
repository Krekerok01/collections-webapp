package com.varvara.controller;


import com.varvara.service.UserServiceImpl;
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

    private UserServiceImpl userServiceImpl;

    @Autowired
    public AdminController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }


    @GetMapping("/list")
    public String showListPage(Model model){
        model.addAttribute("users", userServiceImpl.getAllUsers());
        return "users_list.html";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("userId") int id){
        userServiceImpl.delete(id);
        return (id == authenticationUserId) ? "redirect:/login" : "redirect:/users/list";
    }


    @GetMapping("/blockUser")
    public String blockUser(@RequestParam("username") String username){
        userServiceImpl.blockUser(username);
        return username.equals(authenticationUserName) ? "redirect:/login" : "redirect:/users/list";
    }

    @GetMapping("/unblockUser")
    public String unblockUser(@RequestParam("username") String username){
        userServiceImpl.unblockUser(username);
        return "redirect:/users/list";
    }

    @GetMapping("/addToAdmins")
    public String addToAdmins(@RequestParam("username") String username){
        userServiceImpl.addUserToAdmins(username);
        return "redirect:/users/list";
    }


    @GetMapping("/removeFromAdmins")
    public String removeFromAdmins(@RequestParam("username") String username){
        userServiceImpl.removeUserFromAdmins(username);
        return username.equals(authenticationUserName) ? "redirect:/login" : "redirect:/users/list";
    }
}
