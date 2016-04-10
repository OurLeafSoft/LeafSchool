package com.app.security;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.leafsoft.org.OrgUtil;
import com.leafsoft.school.dao.DaoSelectorUtil;
import com.leafsoft.school.dao.OrgUserRolesDao;
import com.leafsoft.school.dao.OrgUsersDao;
import com.leafsoft.school.model.LoginDetail;
import com.leafsoft.school.model.OrgUser;
import com.leafsoft.school.model.OrgUserRole;
import com.leafsoft.school.util.CommonUtil;
import com.leafsoft.security.LAMUtil;
import com.leafsoft.user.LeafUser;
import com.leafsoft.util.Constants;

@SuppressWarnings("rawtypes")
public class UserDetailsServiceImpl implements AuthenticationUserDetailsService {

	public UserDetails loadUserDetails(Authentication token)
			throws UsernameNotFoundException {
		UserDetails userDetails = null;

		boolean principal = Boolean.valueOf(token.getPrincipal().toString());
		JSONObject details = token.getCredentials()!=null ? new JSONObject(token.getCredentials().toString()) : new JSONObject();;
		String usertype = details.has(Constants.USER_TYPE) ? details.getString(Constants.USER_TYPE) : null;
		if(usertype==null || usertype.equalsIgnoreCase(Constants.ADMIN_USER)) {
			LeafUser leafuser = LAMUtil.getCurrentUser()!=null ? (LeafUser) LAMUtil.getCurrentUser() : null;
			if (leafuser != null && principal == true) {
				int lid = leafuser.getLid();
				String name = leafuser.getUsername();
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
		    		OrgUserRolesDao orgroleDAO = DaoSelectorUtil.getOrgUserRolesDao();
		    		int orgId = details.has(Constants.ORG_ID) && details.getInt(Constants.ORG_ID) != -1 ? details.getInt(Constants.ORG_ID) : orgUser.getDefaultorgid();
		    		OrgUserRole orgUserRole = orgroleDAO.loadOrgUserByLuid(orgUser.getLuid(), orgId);
			    	if(orgUser != null && orgUserRole != null) {
			    		OrgUtil.setRole(orgUserRole.getRolename());
						if (orgUserRole.getRolename().equals(Constants.ROLE_ADMIN)) {
							userDetails = getAdminUser(name);
						} else if (orgUserRole.getRolename().equals(Constants.ROLE_DBA)) {
							userDetails = getDBAUser(name);
						}
			    	}
		    	} else {
		    		userDetails = getCommonUser(name);
		    	}
			} 
//			else {
//				userDetails = getGuestUser("guest");
//			}
			if (userDetails == null) {
				throw new UsernameNotFoundException("Invalid user");
			}
		} else if(usertype!=null && usertype.equalsIgnoreCase(Constants.NONADMIN_USER)) {
			LoginDetail logindetail = LAMUtil.getCurrentUser() !=null ? (LoginDetail) LAMUtil.getCurrentUser() : null;
			if(logindetail!=null) {
				String role = logindetail.getRole();
				String name = logindetail.getUsername();
				if(role.equalsIgnoreCase(Constants.ROLE_STAFF)) {
					userDetails = getStaffUser(name);
				} else if(role.equalsIgnoreCase(Constants.ROLE_STUDENT)) {
					userDetails = getStudentUser(name);
				} else if(role.equalsIgnoreCase(Constants.ROLE_PARENT)) {
					userDetails = getParentUser(name);
				}
			}
			if (userDetails == null) {
				throw new UsernameNotFoundException("Invalid user");
			}
		}


		return userDetails;
	}

	private UserDetails getAdminUser(String username) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority(Constants.ROLE_STAFF));
		grantedAuthorities.add(new SimpleGrantedAuthority(Constants.ROLE_DBA));
		grantedAuthorities.add(new SimpleGrantedAuthority(Constants.ROLE_COMMONUSER));
		grantedAuthorities.add(new SimpleGrantedAuthority(Constants.ROLE_ADMIN));
		grantedAuthorities.add(new SimpleGrantedAuthority(Constants.ROLE_GUEST));
		JSONArray userRole = new JSONArray(grantedAuthorities.toString());
		OrgUtil.setUserRole(userRole);
		return new User(username, "notused", true, true, true, true,
				grantedAuthorities);
	}

	private UserDetails getDBAUser(String username) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority(Constants.ROLE_STAFF));
		grantedAuthorities.add(new SimpleGrantedAuthority(Constants.ROLE_DBA));
		grantedAuthorities.add(new SimpleGrantedAuthority(Constants.ROLE_COMMONUSER));
		grantedAuthorities.add(new SimpleGrantedAuthority(Constants.ROLE_GUEST));
		JSONArray userRole = new JSONArray(grantedAuthorities.toString());
		OrgUtil.setUserRole(userRole);
		return new User(username, "notused", true, true, true, true,
				grantedAuthorities);
	}

	private UserDetails getStaffUser(String username) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority(Constants.ROLE_STAFF));
		grantedAuthorities.add(new SimpleGrantedAuthority(Constants.ROLE_GUEST));
		JSONArray userRole = new JSONArray(grantedAuthorities.toString());
		OrgUtil.setUserRole(userRole);
		return new User(username, "notused", true, true, true, true,
				grantedAuthorities);
	}
	
	private UserDetails getCommonUser(String username) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority(Constants.ROLE_COMMONUSER));
		grantedAuthorities.add(new SimpleGrantedAuthority(Constants.ROLE_GUEST));
		JSONArray userRole = new JSONArray(grantedAuthorities.toString());
		OrgUtil.setUserRole(userRole);
		return new User(username, "notused", true, true, true, true,
				grantedAuthorities);
	}
	
	private UserDetails getParentUser(String username) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority(Constants.ROLE_PARENT));
		grantedAuthorities.add(new SimpleGrantedAuthority(Constants.ROLE_GUEST));
		JSONArray userRole = new JSONArray(grantedAuthorities.toString());
		OrgUtil.setUserRole(userRole);
		return new User(username, "notused", true, true, true, true,
				grantedAuthorities);
	}
	
	private UserDetails getStudentUser(String username) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority(Constants.ROLE_STUDENT));
		grantedAuthorities.add(new SimpleGrantedAuthority(Constants.ROLE_GUEST));
		JSONArray userRole = new JSONArray(grantedAuthorities.toString());
		OrgUtil.setUserRole(userRole);
		return new User(username, "notused", true, true, true, true,
				grantedAuthorities);
	}
	
	private UserDetails getGuestUser(String username) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority(Constants.ROLE_GUEST));
		JSONArray userRole = new JSONArray(grantedAuthorities.toString());
		OrgUtil.setUserRole(userRole);
		return new User(username, "notused", true, true, true, true,
				grantedAuthorities);
	}
	
}
