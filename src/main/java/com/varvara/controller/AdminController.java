package com.varvara.controller;


import com.varvara.entity.Collection;
import com.varvara.entity.Role;
import com.varvara.entity.User;
import com.varvara.service.CollectionService;
import com.varvara.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

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

        List<User> users = userService.getAllUsers();

        model.addAttribute("users", users);


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

        User user = userService.findByUsername(username);

        user.setAccountNonLocked(false);

        userService.saveUser(user);

        if (username.equals(authenticationUserName)){
            return "redirect:/login";
        } else {
            return "redirect:/users/list";
        }
    }

    @GetMapping("/unblockUser")
    public String unblockUser(@RequestParam("username") String username){

        User user = userService.findByUsername(username);

        user.setAccountNonLocked(true);

        userService.saveUser(user);
        return "redirect:/users/list";
    }

    @GetMapping("/addToAdmins")
    public String addToAdmins(@RequestParam("username") String username){

        User user = userService.findByUsername(username);

        List<Role> roles = (List<Role>) user.getRoles();
        if (roles.size() == 1){
            Role role = userService.findRoleByName("ROLE_ADMIN");
            roles.add(role);
        }

        user.setRoles(roles);

        userService.saveUser(user);
        return "redirect:/users/list";
    }


    @GetMapping("/removeFromAdmins")
    public String removeFromAdmins(@RequestParam("username") String username){

        User user = userService.findByUsername(username);

        List<Role> roles = new ArrayList<>();

        Role role = userService.findRoleByName("ROLE_USER");
        roles.add(role);

        user.setRoles(roles);


        userService.saveUser(user);
        return "redirect:/users/list";
    }



}
