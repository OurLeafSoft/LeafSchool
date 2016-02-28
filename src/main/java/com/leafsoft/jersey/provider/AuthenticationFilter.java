	package com.leafsoft.jersey.provider;
	
	import java.lang.reflect.Method;
	import java.util.Arrays;
	import java.util.HashSet;
	import java.util.Set;
	
	import javax.annotation.security.DenyAll;
	import javax.annotation.security.PermitAll;
	import javax.annotation.security.RolesAllowed;
	import javax.ws.rs.container.ContainerRequestContext;
	import javax.ws.rs.container.ResourceInfo;
	import javax.ws.rs.core.Context;
	import javax.ws.rs.core.MultivaluedMap;
	import javax.ws.rs.core.Response;
	import javax.ws.rs.ext.Provider;
	
import org.json.JSONArray;
import org.json.JSONObject;

import com.leafsoft.org.OrgUtil;
	
	/**
	 * This filter verify the access permissions for a user
	 * based on username and passowrd provided in request
	 * */
	@Provider
	public class AuthenticationFilter implements javax.ws.rs.container.ContainerRequestFilter
	{
		
		@Context
	    private ResourceInfo resourceInfo;
		
		private JSONObject resJson = new JSONObject();
	    private static final Response ACCESS_DENIED = Response.status(Response.Status.UNAUTHORIZED)
	    													.entity("{\"message\" : \"You cannot access this resource\"}").build();
	    private static final Response ACCESS_FORBIDDEN = Response.status(Response.Status.FORBIDDEN)
	    													.entity("{\"message\" : \"Access blocked for all users\"}").build();
	     
	    @Override
	    public void filter(ContainerRequestContext requestContext)
	    {
	        Method method = resourceInfo.getResourceMethod();
	        //Access allowed for all
	        if( ! method.isAnnotationPresent(PermitAll.class))
	        {
	            //Access denied for all
	            if(method.isAnnotationPresent(DenyAll.class))
	            {
	                requestContext.abortWith(ACCESS_FORBIDDEN);
	                return;
	            }
	             
	            //Get request headers
	            final MultivaluedMap<String, String> headers = requestContext.getHeaders();
	            
	            JSONArray userRoles = OrgUtil.getUserRole();
	            //If no authorization information present; block access
	            if(userRoles == null || userRoles.length() < 0)
	            {
	                requestContext.abortWith(ACCESS_DENIED);
	                return;
	            }
	             
	             
	 
	             
	            //Verify user access
	            if(method.isAnnotationPresent(RolesAllowed.class))
	            {
	                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
	                Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));
	                 
	                //Is user valid?
	                if( ! isUserAllowed(userRoles,rolesSet))
	                {
	                    requestContext.abortWith(ACCESS_DENIED);
	                    return;
	                }
	            }
	        }
	    }
	    private boolean isUserAllowed(final JSONArray userroles, final Set<String> rolesSet)
	    {
	        boolean isAllowed = false;
	        for(int i=0;i<userroles.length();i++) {
	            if(rolesSet.contains(userroles.get(i)))
	            {
	                isAllowed = true;
	                break;
	            }
	        }
	        return isAllowed;
	    }
	}