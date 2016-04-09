package com.leafsoft.org;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import com.app.controller.SchoolController;
import com.leafsoft.http.HttpUtil;
import com.leafsoft.school.dao.DaoSelectorUtil;
import com.leafsoft.school.dao.LoginDetailsDao;
import com.leafsoft.school.dao.OrgUserRolesDao;
import com.leafsoft.school.dao.OrgUsersDao;
import com.leafsoft.school.dao.StaffDetailsDao;
import com.leafsoft.school.dao.OrgDetailsDao;
import com.leafsoft.school.model.LoginDetail;
import com.leafsoft.school.model.OrgDetail;
import com.leafsoft.school.model.OrgUser;
import com.leafsoft.school.model.OrgUserRole;
import com.leafsoft.school.util.CommonUtil;
import com.leafsoft.user.LeafUser;
import com.leafsoft.util.AppResources;
import com.leafsoft.util.Constants;
import com.leafsoft.util.JSONUtil;
import com.leafsoft.util.JdbcUtil;


public class OrgUtil {
	
	private static Logger LOGGER = Logger.getLogger(OrgUtil.class.getName());
	
	private static ThreadLocal<OrgUser> OWNER = new ThreadLocal<OrgUser>();
	private static ThreadLocal<JSONArray> USER_ROLE = new ThreadLocal<JSONArray>();
	private static ThreadLocal<Integer> OWNERID = new ThreadLocal<Integer>();
	private static ThreadLocal<Integer> USERLID = new ThreadLocal<Integer>();
	private static ThreadLocal<LeafUser> USER = new ThreadLocal<LeafUser>();
	private static ThreadLocal<JSONArray> ORGADMINS = new ThreadLocal<JSONArray>();
	private static ThreadLocal<String> REMOTEUSERIPADDRESS = new ThreadLocal<String>();
	private static ThreadLocal<String> ORGDB = new ThreadLocal<String>();
	private static ThreadLocal<OrgUser> ORGUSER = new ThreadLocal<OrgUser>();
	private static ThreadLocal<GrantedAuthority> AUTHORITIES = new ThreadLocal<GrantedAuthority>();
	private static ThreadLocal<OrgDetail> ORGDETAILS = new ThreadLocal<OrgDetail>();
	private static ThreadLocal<Integer> ORGID = new ThreadLocal<Integer>();
	private static ThreadLocal<Boolean> VALIDORG = new ThreadLocal<Boolean>();
	private static ThreadLocal<HttpServletRequest> REQUEST = new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<String> USER_TYPE = new ThreadLocal<String>();
	private static ThreadLocal<Object> NONADMINUSER = new ThreadLocal<Object>();
	private static ThreadLocal<String> ROLE = new ThreadLocal<String>(); 
	/**
	 * @return the owner
	 */
	public static OrgUser getOwner() {
		return OWNER.get();
	}
	/**
	 * @return the userRole
	 */
	public static JSONArray getUserRole() {
		return USER_ROLE.get();
	}
	/**
	 * @return the ownerid
	 */
	public static Integer getOwnerid() {
		return OWNERID.get();
	}
	/**
	 * @return the userlid
	 */
	public static Integer getUserlid() {
		return USERLID.get();
	}
	/**
	 * @return the orgid
	 */
	public static Integer getOrgId() {
		return ORGID.get();
	}
	/**
	 * @return the validorg for the user
	 */
	public static Boolean isValidOrg() {
		return VALIDORG.get();
	}
	/**
	 * @return the user
	 */
	public static LeafUser getUser() {
		return USER.get();
	}
	/**
	 * @return the orgadmins
	 */
	public static JSONArray getOrgadmins() {
		return ORGADMINS.get();
	}
	/**
	 * @return the remoteuseripaddress
	 */
	public static String getRemoteuseripaddress() {
		return REMOTEUSERIPADDRESS.get();
	}
	/**
	 * @return the orgdb
	 */
	public static String getOrgdb() {
		return ORGDB.get();
	}
	/**
	 * @return the rEQUEST
	 */
	public static HttpServletRequest getRequest() {
		return REQUEST.get();
	}
	/**
	 * @return the uSER_TYPE
	 */
	public static String getUserType() {
		return USER_TYPE.get();
	}
	/**
	 * @param owner the owner to set
	 */
	public static void setOwner(OrgUser owner) {
		OWNER.set(owner);
	}
	/**
	 * @param userRole the userRole to set
	 */
	public static void setUserRole(JSONArray userRole) {
		USER_ROLE.set(userRole);
	}
	/**
	 * @param ownerid the ownerid to set
	 */
	public static void setOwnerid(Integer ownerid) {
		OWNERID.set(ownerid);
	}
	/**
	 * @param userlid the userlid to set
	 */
	public static void setUserlid(Integer userlid) {
		USERLID.set(userlid);
	}
	/**
	 * @param userlid the orgid to set
	 */
	public static void setOrgId(Integer orgId) {
		ORGID.set(orgId);
	}
	/**
	 * @param user the user to set
	 */
	public static void setUser(LeafUser user) {
		USER.set(user);
	}
	/**
	 * @param orgadmins the orgadmins to set
	 */
	public static void setOrgadmins(ThreadLocal<JSONArray> orgadmins) {
		ORGADMINS = orgadmins;
	}
	/**
	 * @param remoteuseripaddress the remoteuseripaddress to set
	 */
	public static void setRemoteuseripaddress(String remoteuseripaddress) {
		REMOTEUSERIPADDRESS.set(remoteuseripaddress);
	}
	/**
	 * @param orgdb the orgdb to set
	 */
	public static void setOrgdb(String orgdb) {
		ORGDB.set(orgdb);
	}
	/**
	 * @param orgdb the validOrg to set
	 */
	public static void setValidOrg(Boolean isValidOrg) {
		VALIDORG.set(isValidOrg);
	}
	/**
	 * @param rEQUEST the rEQUEST to set
	 */
	public static void setRequest(HttpServletRequest rEQUEST) {
		REQUEST.set(rEQUEST);
	}
	/**
	 * @param uSER_TYPE the uSER_TYPE to set
	 */
	public static void setUserType(String uSER_TYPE) {
		USER_TYPE.set(uSER_TYPE);
	}
	/**
	 * @return the nONADMINUSER
	 */
	public static Object getNonAdminUser() {
		return NONADMINUSER.get();
	}
	/**
	 * @param nONADMINUSER the nONADMINUSER to set
	 */
	public static void setNonAdminUser(Object nONADMINUSER) {
		NONADMINUSER.set(nONADMINUSER);
	}
	
	public static void init(LeafUser currentUser,String orgId,String remoteIp) {
		try {
			OrgUtil.setRemoteuseripaddress(remoteIp);
			if (currentUser == null) {
				return;
			}
			Integer lid =currentUser.getLid();
			
			if(lid != null) {
				setUser(currentUser);
				setUserlid(lid);
				if(orgId!=null) {
					setOrgdb("db"+orgId);
				}
			}
			OrgUtil.setOwnerid(OrgUtil.getOwnerid() != null ? OrgUtil.getOwnerid() :null);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
	}
	
	/**
	 * @return the oRG_USER
	 */
	public static OrgUser getOrgUser() {
		return ORGUSER.get();
	}
	/**
	 * @param oRG_USER the oRG_USER to set
	 */
	public static void setOrgUser(OrgUser oRGUSER) {
		ORGUSER.set(oRGUSER);
	}
	/**
	 * @return the authorities
	 */
	public static GrantedAuthority getAuthorities() {
		return AUTHORITIES.get();
	}
	/**
	 * @param authorities the authorities to set
	 */
	public static void setAuthorities(List<String> roles) {
        List<GrantedAuthority> listOfAuthorities = new ArrayList<GrantedAuthority>();
        for (String role : roles) {
            listOfAuthorities.add(new GrantedAuthorityImpl(role));
        }
        AUTHORITIES.set((GrantedAuthority)listOfAuthorities);
    }
	/**
	 * @return the oRGDETAILS
	 */
	public static OrgDetail getOrgDetails() {
		return ORGDETAILS.get();
	}
	/**
	 * @param oRGDETAILS the oRGDETAILS to set
	 */
	public static void setOrgDetails(OrgDetail oRGDETAILS) {
		ORGDETAILS.set(oRGDETAILS);
	}
	
	/**
	 * @return the rOLE
	 */
	public static String getRole() {
		return ROLE.get();
	}
	/**
	 * @param rOLE the rOLE to set
	 */
	public static void setRole(String rOLE) {
		ROLE.set(rOLE);
	}
	
	public static boolean setAdmin(HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			OrgUser orgUser = getCurrentUser();
			if(orgUser == null || orgUser.getUsername().equalsIgnoreCase("guest")) {
				Cookie[] cookie_jar = request.getCookies();
				System.out.print("sessionidprincipal"+request.getUserPrincipal());
				System.out.print("sessionidname"+session.getAttribute("user"));
			// Check to see if any cookies exists
				JSONObject sessionUser = null;
				if (cookie_jar != null)
				{
					for (int i =0; i< cookie_jar.length; i++)
					{
						Cookie aCookie = cookie_jar[i];
						System.out.println ("Name : " + aCookie.getName());
						System.out.println ("Value: " + aCookie.getValue());
						if(!aCookie.getValue().equals(request.getRequestedSessionId()) || Boolean.valueOf(AppResources.getInstance().isDevelopmentMode())) {
							try {
								if(Boolean.valueOf(AppResources.getInstance().isDevelopmentMode())) {
									sessionUser = JSONUtil.getInstance().getDebugJson().getJSONObject("1").getJSONObject("userInfo");
								} else {
									String httpResponse = HttpUtil.makeApiCall(AppResources.getInstance().getAccountsUrl()+"/loginUsers", aCookie.getValue(),"GET");
									System.out.print("response:::::::::"+httpResponse);
									sessionUser = new JSONObject(httpResponse);
								} 
								} catch(Exception e) {
									e.printStackTrace();
								}
							}
					}
				}
				else if(Boolean.valueOf(AppResources.getInstance().isDevelopmentMode())) {
					sessionUser = JSONUtil.getInstance().getDebugJson().getJSONObject("1").getJSONObject("userInfo");
				}
				if(sessionUser!= null && sessionUser.length() <=0) {
					return false;
				} else if(sessionUser!=null){
					LeafUser leafuser = CommonUtil.getLeafUserFromSessionJson(sessionUser);
					OrgUtil.init(leafuser, request.getParameter("orgid"), request.getRemoteAddr());
//						SecurityContext securityContext = SecurityContextHolder.getContext();
//						session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
					OrgUtil.setUser(leafuser);
					OrgUtil.setUserlid(leafuser.getLid());
			        // Inject the datasource into the dao
			    	OrgUsersDao userDAO = DaoSelectorUtil.getOrgUserDao();
			    	orgUser = userDAO.loadOrgUserByLid(leafuser.getLid());
			    	if(orgUser == null) {
			    		orgUser = new OrgUser();
			    		orgUser.setEmail(leafuser.getEmail());
			    		orgUser.setLid(leafuser.getLid());
			    		orgUser.setUsername(leafuser.getUsername());
			    		orgUser.setDefaultorgid(-1);
			    		int luid = userDAO.insert(orgUser);
			    		orgUser.setLuid(luid);
			    	}
			    	initAdminOrgDetails(request, orgUser);
			    	
					return true;
				}
						
				} else {
//				Authentication a = SecurityContextHolder.getContext().getAuthentication();
//				a.setAuthenticated(false);
					initAdminOrgDetails(request, orgUser);
				return true;
			}
				
			} catch (Exception e) {
				LOGGER.log(Level.INFO, e.getMessage(), e);
			}
	    return false;
	}
	
	public static OrgUser getCurrentUser() {
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
	
	public static void initAdminOrgDetails(HttpServletRequest request, OrgUser orgUser) throws Exception{
		OrgUserRolesDao userRoleDao = DaoSelectorUtil.getOrgUserRolesDao();
		OrgDetailsDao orgDao = DaoSelectorUtil.getOrganizationDao();
    	int totalOrg = userRoleDao.getTotalNumberOfOrgForUser(orgUser.getLuid());//Check number orgs assoicated with the current user
    	
    	if(totalOrg == 1) {//if customer has only one org than allow him to access that org details 
    		//OrgUserRole orgUserRole  = userRoleDao.getSingleOrg(orgUser.getLuid());
    		OrgDetail orgDetails = orgDao.loadOrgDetailByOrgIdAndUserId(orgUser.getDefaultorgid(),orgUser.getLuid());
			OrgUtil.setOrgdb(CommonUtil.getOrgDb(orgDetails.getOrgid()));
			OrgUtil.setOrgDetails(orgDetails);
			OrgUtil.setOrgId(orgDetails.getOrgid());
			OrgUtil.setValidOrg(true);
    	} else if(totalOrg > 1) { //If the customer has multiple orgs than get orgid from the browser if the org id is null than load the default org of the user
    		boolean isValidOrg = false;
    		String orgid = request.getParameter("org_id");
    		JSONArray orgUserRoles = userRoleDao.findAllUserOrg(orgUser.getLuid());
    		if(orgid != null) {
    			int org = Integer.valueOf(orgid);
    			for(int i=0; i<orgUserRoles.length();i++) {
    				OrgUserRole orgUserRole =(OrgUserRole) orgUserRoles.get(i);
    				if(org == orgUserRole.getOrgDetail().getOrgid()) {
    					isValidOrg = true;
    					OrgUtil.setOrgdb(CommonUtil.getOrgDb(orgUserRole.getOrgDetail().getOrgid()));
    					OrgUtil.setOrgDetails(orgUserRole.getOrgDetail());
    					OrgUtil.setOrgId(orgUserRole.getOrgDetail().getOrgid());
    					OrgUtil.setRole(orgUserRole.getRolename());
    					break;
    				}
    			}
    		} else {
    			OrgDetail orgDetails = orgDao.loadOrgDetailByOrgIdAndUserId(orgUser.getDefaultorgid(),orgUser.getLuid());
    			OrgUtil.setOrgdb(CommonUtil.getOrgDb(orgDetails.getOrgid()));
				OrgUtil.setOrgDetails(orgDetails);
				OrgUtil.setOrgId(orgDetails.getOrgid());
				isValidOrg = true;
    		}
    		OrgUtil.setValidOrg(isValidOrg);
    	}
    	OrgUtil.setOrgUser(orgUser);
    	OrgUtil.setOwner(orgUser);
    	OrgUtil.setOwnerid(orgUser.getLuid());
    	OrgUtil.setUserlid(orgUser.getLid());
    	OrgUtil.setRemoteuseripaddress(request.getRemoteAddr());
    	OrgUtil.setRequest(request);
    	OrgUtil.setUserType(Constants.ADMIN_USER);
	}
	
	public static void resetAuthorities(HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		List<GrantedAuthority> updatedAuthorities = new ArrayList<GrantedAuthority>();
		updatedAuthorities.add(new SimpleGrantedAuthority(Constants.ROLE_GUEST));
		updatedAuthorities.add(new SimpleGrantedAuthority(Constants.ROLE_COMMONUSER));

		Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);

		SecurityContextHolder.getContext().setAuthentication(newAuth);
		request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
	}
	
	public boolean setNonAdmin(HttpServletRequest request) {
		try {
		String login_type = request.getParameter("user_type");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String org_id = request.getParameter("org_id");
		LoginDetail logindetail = null;
		OrgDetailsDao orgDao = DaoSelectorUtil.getOrganizationDao();
		OrgDetail orgDetails = orgDao.loadOrgDetailByOrgId(Long.valueOf(org_id));
		OrgUtil.setOrgdb(CommonUtil.getOrgDb(Integer.parseInt(org_id)));
		logindetail = getloginNon_AdminUser();
		if(logindetail!=null){
			OrgUtil.setOrgdb(CommonUtil.getOrgDb(Integer.parseInt(org_id)));
		} else if(login_type!=null && username!=null && password!=null && org_id!=null && login_type.equalsIgnoreCase(Constants.NONADMIN_USER) && orgDetails!=null) {
			OrgUtil.setOrgdb(CommonUtil.getOrgDb(orgDetails.getOrgid()));
			LoginDetailsDao logindao = DaoSelectorUtil.getLoginDao();
			logindetail = logindao.validateUser(username, password);
		} 
		if(logindetail!=null) {
			initNonAdminOrgDetails(request, logindetail, org_id);
			return true;
		}
		} catch(Exception e) {
			LOGGER.log(Level.INFO, e.getMessage(), e);
		}
		return false;
	}
	
	public static void initNonAdminOrgDetails(HttpServletRequest request, LoginDetail loginDetail,String org_id) throws Exception{
		OrgDetailsDao orgDao = DaoSelectorUtil.getOrganizationDao();
		OrgDetail orgDetails = orgDao.loadOrgDetailByOrgId(Long.valueOf(org_id));
		Object non_admin = null;
		if(loginDetail.getRole().equalsIgnoreCase(Constants.ROLE_STAFF)) {
			StaffDetailsDao staffdao = DaoSelectorUtil.getStaffDetailsDao();
			non_admin = staffdao.getStaffDetailsById(loginDetail.getUserid());
		} else if(loginDetail.getRole().equalsIgnoreCase(Constants.ROLE_PARENT)) {
			
		} else if(loginDetail.getRole().equalsIgnoreCase(Constants.ROLE_STUDENT)) {
			
		}
		OrgUtil.setOrgdb(CommonUtil.getOrgDb(orgDetails.getOrgid()));
		OrgUtil.setOrgDetails(orgDetails);
		OrgUtil.setOrgId(orgDetails.getOrgid());
		OrgUtil.setValidOrg(true);
    	OrgUtil.setOrgUser(null);
    	OrgUtil.setOwner(null);
    	OrgUtil.setNonAdminUser(loginDetail);
    	OrgUtil.setOwnerid(loginDetail.getUserid());
    	OrgUtil.setUserlid(loginDetail.getId());
    	OrgUtil.setRemoteuseripaddress(request.getRemoteAddr());
    	OrgUtil.setRequest(request);
    	OrgUtil.setUserType(Constants.NONADMIN_USER);
    	OrgUtil.setRole(loginDetail.getRole());
	}
	
	public static void cleanup() {
		OrgUtil.setOwner(null);
		OrgUtil.setUser(null);
		OrgUtil.setUserRole(null);
		OrgUtil.setOrgdb(null);
		OrgUtil.setRemoteuseripaddress(null);
		OrgUtil.setUserlid(null);
		OrgUtil.setOrgadmins(null);
		OrgUtil.setOwnerid(null);
		OrgUtil.setOrgUser(null);
		OrgUtil.setOrgId(null);
		OrgUtil.setOrgDetails(null);
		OrgUtil.setValidOrg(false);
		OrgUtil.setRequest(null);
		OrgUtil.setUserType(null);
		OrgUtil.setRole(null);
	}
	
//	public static void initOrgDetails(HttpServletRequest request) {
//		OrgUser orgUser = getCurrentUser();
//		if(orgUser != null && orgUser.getUsername().equalsIgnoreCase("guest") && OrgUtil.getUserRole() != null && OrgUtil.getUserRole().length() > 0) {
//			if(OrgUtil.getUserRole().toString().contains(Constants.ROLE_ADMIN)) {
//				initAdminOrgDetails(request, orgUser);
//			} else {
//				LoginDetailsDao logindao = DaoSelectorUtil.getLoginDao();
//				LoginDetail loginDetail = logindao.getLoginDetailByUserName(orgUser.getUsername());
//				String org_id = request.getParameter("org_id");
//				initNonAdminOrgDetails(request, loginDetail, org_id);
//			}
//		}
//	}
}
