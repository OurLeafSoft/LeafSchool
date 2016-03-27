package com.app.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.leafsoft.org.OrgUtil;
import com.leafsoft.util.JSONUtil;

@Controller
public class DashBoardController {
	
	private static Logger LOGGER = Logger.getLogger(SchoolController.class.getName());
	
	@RequestMapping(produces="application/json",value = "/dashboard/sidebar", method = RequestMethod.GET)
	public @ResponseBody String getEmployees(HttpServletRequest request) 
	{
		JSONObject response = new JSONObject();
		try {
			JSONObject sidbar_json = JSONUtil.getInstance().getSidebarJSON();
			JSONArray rolebased_array = sidbar_json.getJSONArray("admin");
			response.put("code", 0);
			response.put("sidbar_array", rolebased_array);
		
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		return response.toString();

	}
}
