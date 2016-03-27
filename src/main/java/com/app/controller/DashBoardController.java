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
import com.leafsoft.school.dao.DaoSelectorUtil;
import com.leafsoft.school.dao.OrgDetailsDao;
import com.leafsoft.school.model.OrgDetail;
import com.leafsoft.util.JSONUtil;

@Controller
public class DashBoardController {
	
	private static Logger LOGGER = Logger.getLogger(SchoolController.class.getName());
	
	@RequestMapping(produces="application/json",value = "/dashboard", method = RequestMethod.GET)
	public @ResponseBody String getEmployees(HttpServletRequest request) 
	{
		JSONObject response = new JSONObject();
		try {
			JSONObject sidbar_json = JSONUtil.getInstance().getSidebarJSON();
			OrgDetailsDao orgdao = DaoSelectorUtil.getOrganizationDao();
			OrgDetail ordetails = orgdao.loadOrgDetailByOrgId(OrgUtil.getOrgId(), OrgUtil.getOwnerid());
			JSONArray rolebased_array = sidbar_json.getJSONArray("admin");
			response.put("code", 0);
			response.put("sidbar_array", rolebased_array);
			response.put("user", new JSONObject(OrgUtil.getCurrentUser()));
			response.put("orgdetails", new JSONObject(ordetails));
		
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		return response.toString();

	}
}
