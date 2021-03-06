package com.app.controller;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.leafsoft.mail.SendMail;
import com.leafsoft.org.OrgUtil;
import com.leafsoft.school.dao.DaoSelectorUtil;
import com.leafsoft.school.dao.OrgUsersDao;
import com.leafsoft.school.dao.OrgDetailsDao;
import com.leafsoft.school.model.Course;
import com.leafsoft.school.model.OrgDetail;
import com.leafsoft.school.model.OrgUser;
import com.leafsoft.user.LeafUser;
 
@Controller
public class SchoolRegisterController {
	private static final Logger LOGGER = Logger.getLogger(SchoolRegisterController.class.getName());
	@RequestMapping(value = "/register", method = RequestMethod.GET)
    public String viewRegistration(Map<String, Object> model) {
        OrgDetail orgDetails = new OrgDetail();    
        model.put("orgForm", orgDetails);
        System.out.print("sessionid"+OrgUtil.getOrgdb());
        return "SchoolRegistration";
    }
     
	@RequestMapping(value = "/schoolRegister", method = RequestMethod.POST)
    public ModelAndView processRegistration(@ModelAttribute("orgForm") OrgDetail org,
            Map<String, Object> model,HttpServletRequest request,HttpServletResponse response) {
         
        // for testing purpose:
    	LOGGER.log(Level.INFO,"username: " + org.getOrgname());
    	ModelAndView modelview = new ModelAndView();
    	 int orguserid = -1;
    	 OrgUser orguser = null;
         OrgUsersDao orgUserDao = DaoSelectorUtil.getOrgUserDao();
         LOGGER.log(Level.INFO,"userLid::::"+OrgUtil.getUserlid());
	    	if(!orgUserDao.hasUser(OrgUtil.getUserlid())) {
	    		orguser = new OrgUser();
	        	orguser.setEmail(OrgUtil.getUser().getEmail());
	        	orguser.setUsername(OrgUtil.getUser().getUsername());
	        	orguser.setLid(OrgUtil.getUser().getLid());
	        	orguserid = orgUserDao.insert(orguser);
	        } else {
	        	orguser = orgUserDao.loadOrgUserByLid(OrgUtil.getUserlid());
	        	orguserid = orguser.getLuid();
	        }
	    	OrgUtil.setOwnerid(orguserid);
	    	OrgUtil.setOwner(orguser);
	    	OrgDetailsDao orgDao = DaoSelectorUtil.getOrganizationDao();
	    	if(!org.getOrgname().equals("")) { 
	    	// Inject the datasource into the dao
	        int orgId = orgDao.insert(org);
	        LOGGER.log(Level.INFO,"Registered orgId::::::"+orgId);
	        org.setOrgid(orgId);
	        OrgUtil.setOrgdb("db"+orgId);
	        OrgUtil.setOrgDetails(org);
	        modelview.addObject("user",OrgUtil.getOwner());
	        modelview.addObject("org",OrgUtil.getOrgDetails());
	        modelview.setViewName("home");
	        modelview.addObject("msg", "Your account has been created!");
	        String serverName = request.getServerName();
	        if(serverName.contains("localhost")) {
	        	serverName = serverName+":"+request.getServerPort()+(request.getServletContext().getContextPath());
	        }
	        String msg = "<img src='images/leafsoft.png' alt='LeafSoft'><br><h3>Hi "+org.getOrgname()+"</h3>,<br>"+"<h4>Welcome to LeafSoft!<br>Now that you've successfully created an LeafSoft School Management Online account.";
	        msg = msg + "<br><br>Thanks<br>The LeafSoft Team";
	        if(OrgUtil.getOwner()!=null) {
	        	SendMail.send(OrgUtil.getOwner().getEmail(), "Welcome to LeafSoft SchoolManagement", msg);
	        }
	        try {
	        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	        redirectStrategy.sendRedirect(request, response, "/html/login.html");
	        } catch(Exception e) {
	        	LOGGER.log(Level.SEVERE,e.getMessage(),e);
	        }
    	}
    	else {
    		modelview.setViewName("Registration");
            modelview.addObject("error", "orgname can't be empty..");
    	}
        SecurityContextHolder.clearContext();
        return modelview;
    }
	
	@RequestMapping(value = "/course", method = RequestMethod.GET)
    public String addCourse(Map<String, Object> model) {
        Course course = new Course();    
        model.put("courseForm", course);
        System.out.print("sessionid"+OrgUtil.getOrgdb());
        return "course";
    }
	
	@RequestMapping(value = "/course", method = RequestMethod.POST)
    public void courseProcess(@ModelAttribute("courseForm") Course course,
            Map<String, Object> model,HttpServletRequest request) {
         
        // for testing purpose:
    	LOGGER.log(Level.INFO,"username: " + course.getCourse());
    }
    
}