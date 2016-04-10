package com.leafsoft.school.filter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.leafsoft.org.OrgUtil;
import com.leafsoft.school.dao.LoginDetailsDao;
import com.leafsoft.school.model.LoginDetail;
import com.leafsoft.school.model.OrgUser;
import com.leafsoft.school.util.CommonUtil;
import com.leafsoft.util.Constants;

public class OrgFilter implements Filter {

	private static final Logger LOGGER = Logger.getLogger(OrgFilter.class.getName());
	
	public void destroy() {
		// TODO Auto-generated method stub

	}
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc)
			throws IOException, ServletException {
		try {
			HttpServletRequest request = (HttpServletRequest) req;
			if(request.getAttribute(Constants.DOES_NOT_NEED_ORGFILTER) == null || !Boolean.valueOf(request.getAttribute(Constants.DOES_NOT_NEED_ORGFILTER).toString())) {
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				LOGGER.log(Level.INFO,"getOrgId::::::"+OrgUtil.getOrgId());
				LOGGER.log(Level.INFO,"getRequestURI::::::"+request.getRequestURI());
				LOGGER.log(Level.INFO,"Filter:::::ip:"+request.getRemoteAddr());
				LOGGER.log(Level.INFO,"OrgUtil.isValidOrg():::"+OrgUtil.isValidOrg());
				JSONObject authDetails = !(authentication instanceof AnonymousAuthenticationToken) && authentication!=null && authentication.getCredentials()!=null ? new JSONObject(authentication.getCredentials().toString()) : null;
				if(authDetails!=null){
					String user_type = authDetails.has(Constants.USER_TYPE) ? authDetails.getString(Constants.USER_TYPE) : null;
					int org_id = authDetails.has(Constants.ORG_ID) ? authDetails.getInt(Constants.ORG_ID) : -1;
					if(user_type!=null && user_type.equalsIgnoreCase(Constants.NONADMIN_USER)) {
						OrgUtil.setOrgdb(CommonUtil.getOrgDb(org_id));
						LoginDetail loginDetail = OrgUtil.getloginNon_AdminUser();
						OrgUtil.initNonAdminOrgDetails(request, loginDetail, org_id);
					} else {
						OrgUser orgUser = OrgUtil.getCurrentUser();
						OrgUtil.initAdminOrgDetails(org_id, orgUser, request);
					}
				}
				//OrgUtil.resetAuthorities(request);
//				if(OrgUtil.getUserlid() == null) {
//					request.getRequestDispatcher("/invaliduser").forward(request, response);
//					return;
//				} else if(OrgUtil.getOrgId() == null) {
//					request.getRequestDispatcher("/register").forward(request, response);
//					return;
//				} else if(!OrgUtil.isValidOrg()) {
//					request.getRequestDispatcher("/accessdenied").forward(request, response);
//					return;
//				}
			} else {
				//OrgUtil.setCurrentUser(request);
			}
			fc.doFilter(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
	}

	public void init(FilterConfig fc) throws ServletException {
		// TODO Auto-generated method stub

	}
	
	public JSONArray getUserRoleForResource()
	{
//		String zuid = UserUtil.getCurrentUser() != null ? String.valueOf(UserUtil.getCurrentUser().getLid()) : null;
//		logger.log(Level.INFO,"STORE ACCESSS : ZUID {0}",zuid);
//		if(zuid == null)
//		{
//			return null;
//		}
		JSONArray rolename = new JSONArray();
		//logger.log(Level.INFO,"role name returned is: {0}",rolename);
		return rolename;
	}

}
