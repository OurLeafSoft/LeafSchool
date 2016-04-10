package com.app.filter;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import com.leafsoft.security.LAMUtil;
import com.leafsoft.util.Constants;

public class UrlParametersAuthenticationFilter  extends AbstractPreAuthenticatedProcessingFilter {
	
	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		// Check for cookies
		User user = LAMUtil.getLoggedInUser();
		if(user==null) {
			return LAMUtil.authenticateUser(request);
		} else {
			return true;
		}
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();
		String login_type = request.getParameter(Constants.USER_TYPE);
		if(login_type==null || login_type.equals(Constants.ADMIN_USER)) {
			jsonObject.put(Constants.USER_TYPE, Constants.ADMIN_USER);
			jsonObject.put(Constants.ORG_ID, LAMUtil.getOrgId());
		} else {
			jsonObject.put(Constants.USER_TYPE, Constants.NONADMIN_USER);
			jsonObject.put(Constants.ORG_ID, LAMUtil.getOrgId());
		}
		return jsonObject.toString();
	}
}
