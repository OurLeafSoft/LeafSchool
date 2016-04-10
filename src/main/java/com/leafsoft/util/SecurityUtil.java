package com.leafsoft.util;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityUtil {

	public String getHashPassword(String password) {  
		  BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();  
		  String hashedPassword = passwordEncoder.encode(password);  
		  return hashedPassword;  
		 } 
	
	public static void logout(HttpServletRequest request) {
		SecurityContextHolder.getContext().setAuthentication(null);
		SecurityContextHolder.clearContext();
		HttpSession hs = request.getSession();
		Enumeration<String> e = hs.getAttributeNames();
			while (e.hasMoreElements()) {
				String attr = e.nextElement();
				hs.setAttribute(attr, null);
			}
		removeCookies(request);
		hs.invalidate();
		}
	
		public static void removeCookies(HttpServletRequest request) {
			Cookie[] cookies = request.getCookies();
			if (cookies != null && cookies.length > 0) {
				for (int i = 0; i < cookies.length; i++) {
					cookies[i].setMaxAge(0);
				}
			}
		}
		
		public static boolean isCommonUser() {
			boolean commonUser = false;
			Authentication a = SecurityContextHolder.getContext().getAuthentication();
			JSONArray authorities = new JSONArray(a.getAuthorities().toString());
			for (int i = 0; i < authorities.length(); i++) {
				if(authorities.getString(i).equals(Constants.ROLE_COMMONUSER)) {
					commonUser = true;
					break;
				}
			}
			return commonUser;
		}
}
