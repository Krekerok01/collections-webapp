package com.varvara.controller;


import com.varvara.service.UserService;
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

    private UserService userService;

	@Autowired
	public RegistrationController(UserService userService) {
		this.userService = userService;
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

		String userName = userDataFromInput.getUsername();

		logger.info("Processing registration form for: " + userName);
		logger.info("UserDataFromInput: " + userDataFromInput);


        userService.save(userDataFromInput);

        logger.info("Successfully created user: " + userName);
        
        return "registration-confirmation";		
	}
}
