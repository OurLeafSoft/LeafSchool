package com.leafsoft.security;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.leafsoft.http.HttpUtil;
import com.leafsoft.org.OrgUtil;
import com.leafsoft.school.dao.DaoSelectorUtil;
import com.leafsoft.school.dao.LoginDetailsDao;
import com.leafsoft.school.dao.OrgDetailsDao;
import com.leafsoft.school.dao.OrgUserRolesDao;
import com.leafsoft.school.dao.OrgUsersDao;
import com.leafsoft.school.dao.impl.CoursesDaoImpl;
import com.leafsoft.school.model.LoginDetail;
import com.leafsoft.school.model.OrgDetail;
import com.leafsoft.school.model.OrgUser;
import com.leafsoft.school.util.CommonUtil;
import com.leafsoft.user.LeafUser;
import com.leafsoft.util.AppResources;
import com.leafsoft.util.Constants;
import com.leafsoft.util.JSONUtil;

public class LAMUtil {
	
	private static ThreadLocal<Object> CURRENTUSER = new ThreadLocal<Object>();
	private static ThreadLocal<String> USER_TYPE = new ThreadLocal<String>();
	private static ThreadLocal<Integer> ORG_ID = new ThreadLocal<Integer>();
	
	/**
	 * @return the uSER
	 */
	public static Object getCurrentUser() {
		return CURRENTUSER.get();
	}

	/**
	 * @param uSER the uSER to set
	 */
	public static void setCurrentUser(Object uSER) {
		CURRENTUSER.set(uSER);
	}
	
	/**
	 * @return the uSER_TYPE
	 */
	public static String getUserType() {
		return USER_TYPE.get();
	}

	/**
	 * @return the oRG_ID
	 */
	public static Integer getOrgId() {
		return ORG_ID.get();
	}

	/**
	 * @param uSER_TYPE the uSER_TYPE to set
	 */
	public static void setUserType(String uSER_TYPE) {
		USER_TYPE.set(uSER_TYPE);
	}

	/**
	 * @param oRG_ID the oRG_ID to set
	 */
	public static void setOrgId(Integer oRG_ID) {
		ORG_ID.set(oRG_ID);
	}
	
	private static final Logger LOGGER = Logger.getLogger(CoursesDaoImpl.class.getName());
	public static User getLoggedInUser() {
		User user = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		try{
			if(authentication!=null && authentication.isAuthenticated()) {
				user = (User) authentication.getPrincipal();
				JSONObject details = authentication.getCredentials()!=null? new JSONObject(authentication.getCredentials()) : null;
				if(details!=null) {
					setOrgId(details.has(Constants.ORG_ID) ? details.getInt(Constants.ORG_ID) : null);
					setUserType(details.has(Constants.USER_TYPE) ? details.getString(Constants.USER_TYPE) : null);
				}
				LOGGER.log(Level.INFO,"user::::"+user);
				setCurrentUser(user);
			} else {
				return null;
			}
		}catch(Exception e) {
			return null;
		}
		return user;
	}
	
	public static boolean authenticateUser(HttpServletRequest request) {
		boolean authenticated = false;
		try {
			String login_type = request.getParameter("user_type");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String org_id = request.getParameter("org_id");
			LoginDetail logindetail = null;
			JSONObject jsonUser = null;
			if(login_type!=null && username!=null && password!=null && org_id!=null && 
					login_type.equalsIgnoreCase(Constants.NONADMIN_USER)) {
				setUserType(Constants.NONADMIN_USER);
				OrgDetailsDao orgDao = DaoSelectorUtil.getOrganizationDao();
				OrgDetail orgDetails = orgDao.loadOrgDetailByOrgId(Long.valueOf(org_id));
				if(orgDetails!=null) {
					OrgUtil.setOrgdb(CommonUtil.getOrgDb(orgDetails.getOrgid()));
					LoginDetailsDao logindao = DaoSelectorUtil.getLoginDao();
					logindetail = logindao.validateUser(username, password);
					setCurrentUser(logindetail);
					setOrgId(orgDetails.getOrgid());
				}
			} else {
				setUserType(Constants.ADMIN_USER);
				Cookie[] cookie_jar = request.getCookies();
				System.out.print("sessionidprincipal"+request.getUserPrincipal());
			// Check to see if any cookies exists
				if (cookie_jar != null)
				{
					for (int i =0; i< cookie_jar.length; i++) {
						Cookie aCookie = cookie_jar[i];
						LOGGER.log(Level.INFO,"Name : " + aCookie.getName());
						LOGGER.log(Level.INFO,"Value: " + aCookie.getValue());
						if(!aCookie.getValue().equals(request.getRequestedSessionId()) || 
								Boolean.valueOf(AppResources.getInstance().isDevelopmentMode())) {
							try {
								if(Boolean.valueOf(AppResources.getInstance().isDevelopmentMode())) {
									jsonUser = JSONUtil.getInstance().getDebugJson().getJSONObject("1").getJSONObject("userInfo");
									LeafUser leafuser = CommonUtil.getLeafUserFromSessionJson(jsonUser);
									setCurrentUser(leafuser);
									setOrgId(getOrgId(leafuser,request));
								} else {
									String lamuser = HttpUtil.makeApiCall(AppResources.getInstance().getAccountsUrl()+"/loginUsers", aCookie.getValue(),"GET");
									LOGGER.log(Level.INFO,"response:::::::::"+lamuser);
									jsonUser = new JSONObject(lamuser);
									LeafUser leafuser = CommonUtil.getLeafUserFromSessionJson(jsonUser);
									setCurrentUser(leafuser);
									setOrgId(getOrgId(leafuser,request));
								} 
							} catch(Exception e) {
								LOGGER.log(Level.SEVERE,e.getMessage(),e);
							}
						}
					}
				}
				else if(Boolean.valueOf(AppResources.getInstance().isDevelopmentMode())) {
					jsonUser = JSONUtil.getInstance().getDebugJson().getJSONObject("1").getJSONObject("userInfo");
					LeafUser leafuser = CommonUtil.getLeafUserFromSessionJson(jsonUser);
					setCurrentUser(leafuser);
					setOrgId(getOrgId(leafuser,request));
				}
			}
			if(logindetail!=null || jsonUser!=null) {
				authenticated = true;
			}
		} catch(Exception e) {
			LOGGER.log(Level.INFO, e.getMessage(), e);
		}
		return authenticated;
	}
	
	public static OrgUser getCurrentUser1() {
		OrgUser orguser = null;
		User user = null;
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		try{
			if(a.isAuthenticated()) {
				user = (User) a.getPrincipal();
				System.out.print("user::::"+user);
				if(user!=null) {
					OrgUsersDao userDAO = DaoSelectorUtil.getOrgUserDao();
					JSONArray userRole = new JSONArray(a.getAuthorities().toString());
					OrgUtil.setUserRole(userRole);
					orguser = userDAO.loadUserByUsername(user.getUsername());
					System.out.print("username::::"+user.getUsername());
				}
			} else {
				return null;
			}
		}catch(Exception e) {
			return null;
		}
		return orguser;
	}
	
	public static LoginDetail getloginNon_AdminUser() {
		LoginDetail logindetail = null;
		User user = null;
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		try{
			if(a!=null && a.isAuthenticated()) {
				user = (User) a.getPrincipal();
				System.out.print("user::::"+user);
				if(user!=null) {
					LoginDetailsDao logindao = DaoSelectorUtil.getLoginDao();
					logindetail = logindao.getLoginDetailByUserName(user.getUsername());
					JSONArray userRole = new JSONArray(a.getAuthorities().toString());
					OrgUtil.setUserRole(userRole);
					System.out.print("username::::"+user.getUsername());
				}
			} else {
				return null;
			}
		}catch(Exception e) {
			return null;
		}
		return logindetail;
	}

	public static int getOrgId(LeafUser leafuser,HttpServletRequest request) {
	int orgid = -1;
		if (leafuser != null) {
			int lid = leafuser.getLid();
			OrgUsersDao orgUserDao = DaoSelectorUtil.getOrgUserDao();
	    	OrgUser orgUser = orgUserDao.loadOrgUserByLid(lid);
	    	if(orgUser == null) {
	    		orgUser = new OrgUser();
	    		orgUser.setEmail(leafuser.getEmail());
	    		orgUser.setLid(leafuser.getLid());
	    		orgUser.setUsername(leafuser.getUsername());
	    		orgUser.setDefaultorgid(-1);
	    		int luid = orgUserDao.insert(orgUser);
	    		orgUser.setLuid(luid);
	    	}
	    	if(orgUser.getDefaultorgid() != -1) {
	    		orgid = request.getParameter(Constants.ORG_ID)!=null ?  Integer.parseInt(request.getParameter(Constants.ORG_ID)) : orgUser.getDefaultorgid();
	    	}
		}
		return orgid;
	}
}
