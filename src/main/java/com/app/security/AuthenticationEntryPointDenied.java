package com.app.security;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;

import com.leafsoft.org.OrgUtil;
import com.leafsoft.security.LAMUtil;
import com.leafsoft.util.AppResources;

public class AuthenticationEntryPointDenied implements AuthenticationEntryPoint {
	private static final Logger LOGGER = Logger.getLogger(AuthenticationEntryPointDenied.class.getName());
	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		// Redirecting service to access denied page for invalid users 
		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		if (LAMUtil.getCurrentUser() == null) {
			SecurityContextHolder.clearContext();
			redirectStrategy.sendRedirect(request, response, "/html/login.html");
		} 
		else {
				try {
				//request.getRequestDispatcher("/register").forward(request, response);
				redirectStrategy.sendRedirect(request, response, "/register");
				} catch(Exception e) {
					LOGGER.log(Level.SEVERE,e.getMessage(),e);
				}
			}
		
	}

}
