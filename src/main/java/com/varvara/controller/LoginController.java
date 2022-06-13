package com.varvara.controller;

import com.varvara.entity.User;
import com.varvara.service.UserService;
import com.varvara.user.CrmUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.logging.Logger;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage(){
        return "login";
    }


    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }
}
