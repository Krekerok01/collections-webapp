package com.varvara.config;


import com.varvara.entity.User;
import com.varvara.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserServiceImpl userServiceImpl;

    public static String authenticationUserName;
    public static int authenticationUserId;
	private Logger logger = Logger.getLogger(getClass().getName());

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {


		String username = authentication.getName();
		authenticationUserName = username;


		logger.info("Successfully authenticated user with username - " + username);


		User theUser = userServiceImpl.findByUsername(username);
		authenticationUserId = theUser.getId();


		HttpSession session = request.getSession();
		session.setAttribute("user", theUser);
		
		response.sendRedirect(request.getContextPath() + "/");
	}

}
