package com.varvara.controller;


import com.varvara.service.UserServiceImpl;
import com.varvara.dto.UserDataFromInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.logging.Logger;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserServiceImpl userServiceImpl;

	@Autowired
	public RegistrationController(UserServiceImpl userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

	private Logger logger = Logger.getLogger(getClass().getName());

	
	@GetMapping("/registration")
	public String showRegistrationPage(Model theModel) {
		theModel.addAttribute("userDataFromInput", new UserDataFromInput());
		return "registration";
	}

	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(@ModelAttribute("userDataFromInput") @Valid UserDataFromInput userDataFromInput, BindingResult result, Model theModel) {

		if (result.hasErrors()){
			return "registration";
		}

        userServiceImpl.saveUserDataFromInput(userDataFromInput);
        logger.info("Successfully created user: " + userDataFromInput.getUsername());
        
        return "registration-confirmation";		
	}
}
