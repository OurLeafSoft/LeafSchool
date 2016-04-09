package com.app.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import com.leafsoft.org.OrgUtil;
import com.leafsoft.user.LeafUser;
import com.leafsoft.util.Constants;

public class UrlParametersAuthenticationFilter  extends AbstractPreAuthenticatedProcessingFilter {
	
	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		// Check for cookies
		String login_type = request.getParameter("user_type");
		if(login_type==null || login_type.equals(Constants.ADMIN_USER)) {
			return OrgUtil.setAdmin(request);
		} else{
			return new OrgUtil().setNonAdmin(request);
		}
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		
		LeafUser leafuser = null;
	    
		//Getting authentication credentials
		if(OrgUtil.getUser()!=null) { 
			leafuser = OrgUtil.getUser();
		} else if((OrgUtil.getUserType() != null && OrgUtil.getUserType().equals(Constants.NONADMIN_USER))) {
			return OrgUtil.getNonAdminUser();
		} else if(OrgUtil.getOrgUser() == null){
			leafuser = new LeafUser();
			leafuser.setUsername("guest");
		}
	    
	    return leafuser;
	}
}
