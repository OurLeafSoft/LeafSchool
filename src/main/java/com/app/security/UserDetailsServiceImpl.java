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
import com.leafsoft.school.model.OrgUser;
import com.leafsoft.school.model.OrgUserRole;
import com.leafsoft.user.LeafUser;

public class UserDetailsServiceImpl implements AuthenticationUserDetailsService {

	public UserDetails loadUserDetails(Authentication token)
			throws UsernameNotFoundException {
		UserDetails userDetails = null;

		LeafUser credentials = (LeafUser) token.getCredentials();
		boolean principal = Boolean.valueOf(token.getPrincipal().toString());

		if (credentials != null && principal == true) {
			int lid = credentials.getLid();
			String name = credentials.getUsername();
			OrgUsersDao orgUserDao = DaoSelectorUtil.getOrgUserDao();
	    	
	    	OrgUser orguser = orgUserDao.loadOrgUserByLid(lid);
	    	OrgUserRolesDao orgroleDAO = DaoSelectorUtil.getOrgUserRolesDao();
	    	if(OrgUtil.getOrgId() !=null && orguser.getLuid() == OrgUtil.getOwnerid()) {
	    		OrgUserRole orgUserRole = orgroleDAO.loadOrgUserByLuid(orguser.getLuid(), OrgUtil.getOrgId());
		    	if(orguser != null && orgUserRole != null) {
					if (orgUserRole.getRolename().equals("ROLE_ADMIN")) {
						userDetails = getAdminUser(name);
					} else if (orgUserRole.getRolename().equals("ROLE_DBA")) {
						userDetails = getDBAUser(name);
					} else if (orgUserRole.getRolename().equals("ROLE_USER")) {
						userDetails = getUserUser(name);
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

		return userDetails;
	}

	private UserDetails getAdminUser(String username) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_DBA"));
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_COMMONUSER"));
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_GUEST"));
		JSONArray userRole = new JSONArray(grantedAuthorities.toString());
		OrgUtil.setUserRole(userRole);
		return new User(username, "notused", true, true, true, true,
				grantedAuthorities);
	}

	private UserDetails getDBAUser(String username) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_DBA"));
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_COMMONUSER"));
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_GUEST"));
		JSONArray userRole = new JSONArray(grantedAuthorities.toString());
		OrgUtil.setUserRole(userRole);
		return new User(username, "notused", true, true, true, true,
				grantedAuthorities);
	}

	private UserDetails getUserUser(String username) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_COMMONUSER"));
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_GUEST"));
		JSONArray userRole = new JSONArray(grantedAuthorities.toString());
		OrgUtil.setUserRole(userRole);
		return new User(username, "notused", true, true, true, true,
				grantedAuthorities);
	}
	
	private UserDetails getCommonUser(String username) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_COMMONUSER"));
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_GUEST"));
		JSONArray userRole = new JSONArray(grantedAuthorities.toString());
		OrgUtil.setUserRole(userRole);
		return new User(username, "notused", true, true, true, true,
				grantedAuthorities);
	}
	
	private UserDetails getGuestUser(String username) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_GUEST"));
		JSONArray userRole = new JSONArray(grantedAuthorities.toString());
		OrgUtil.setUserRole(userRole);
		return new User(username, "notused", true, true, true, true,
				grantedAuthorities);
	}
}
