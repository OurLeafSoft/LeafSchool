package com.app.security;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
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
import com.leafsoft.user.LeafUser;
import com.leafsoft.util.Constants;

@SuppressWarnings("rawtypes")
public class UserDetailsServiceImpl implements AuthenticationUserDetailsService {

	public UserDetails loadUserDetails(Authentication token)
			throws UsernameNotFoundException {
		UserDetails userDetails = null;

		boolean principal = Boolean.valueOf(token.getPrincipal().toString());
		String usertype = OrgUtil.getUserType();
		if(usertype==null || usertype.equalsIgnoreCase(Constants.ADMIN_USER)) {
			LeafUser credentials = (LeafUser) token.getCredentials();
			if (credentials != null && principal == true) {
				int lid = credentials.getLid();
				String name = credentials.getUsername();
				OrgUsersDao orgUserDao = DaoSelectorUtil.getOrgUserDao();
		    	
		    	OrgUser orguser = orgUserDao.loadOrgUserByLid(lid);
		    	OrgUserRolesDao orgroleDAO = DaoSelectorUtil.getOrgUserRolesDao();
		    	if(OrgUtil.getOrgId() !=null && orguser.getLuid() == OrgUtil.getOwnerid()) {
		    		OrgUserRole orgUserRole = orgroleDAO.loadOrgUserByLuid(orguser.getLuid(), OrgUtil.getOrgId());
			    	if(orguser != null && orgUserRole != null) {
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
			} else {
				userDetails = getGuestUser("guest");
			}
			if (userDetails == null) {
				throw new UsernameNotFoundException("Invalid user - "
						+ credentials.getLid());
			}
		} else if(usertype!=null && usertype.equalsIgnoreCase(Constants.NONADMIN_USER)) {
			LoginDetail credentials = (LoginDetail) token.getCredentials();
			String role = OrgUtil.getRole();
			String name = credentials.getUsername();
			if(role.equalsIgnoreCase(Constants.ROLE_STAFF)) {
				userDetails = getStaffUser(name);
			} else if(role.equalsIgnoreCase(Constants.ROLE_STUDENT)) {
				userDetails = getStudentUser(name);
			} else if(role.equalsIgnoreCase(Constants.ROLE_PARENT)) {
				userDetails = getParentUser(name);
			}
			if (userDetails == null) {
				throw new UsernameNotFoundException("Invalid user - "
						+ credentials.getUserid());
			}
		}


		return userDetails;
	}

	private UserDetails getAdminUser(String username) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority(Constants.ROLE_STAFF));
		grantedAuthorities.add(new SimpleGrantedAuthority(Constants.ROLE_DBA));
		grantedAuthorities.add(new SimpleGrantedAuthority(Constants.ROLE_ADMIN));
		grantedAuthorities.add(new SimpleGrantedAuthority(Constants.ROLE_COMMONUSER));
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
		grantedAuthorities.add(new SimpleGrantedAuthority(Constants.ROLE_COMMONUSER));
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
