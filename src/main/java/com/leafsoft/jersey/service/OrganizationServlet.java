package com.leafsoft.jersey.service;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.leafsoft.jersey.errorhandling.AppException;
import com.leafsoft.org.OrgUtil;
import com.leafsoft.school.dao.DaoSelectorUtil;
import com.leafsoft.school.dao.OrgDetailsDao;
import com.leafsoft.school.model.OrgDetail;

	@Path("/organization")
	public class OrganizationServlet {
		
		@RolesAllowed("ROLE_ADMIN") 
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public Response getOrganizaiontInJSON() throws AppException{
			List<OrgDetail> orgArray = new ArrayList<>();
			OrgDetailsDao orgdao = DaoSelectorUtil.getOrganizationDao();
			orgArray = orgdao.loadOrgDetailByUserId(OrgUtil.getOwnerid());
			return Response.ok().entity(orgArray.toString()).build();
		}
		
		@RolesAllowed("ROLE_ADMIN")
		@GET
	    @Path("/{orgid}")
	    @Produces(MediaType.APPLICATION_JSON)
		public Response getOrganizaiontInJSON(@PathParam("orgid") int orgid) throws AppException{
			OrgDetail orgDetail = new OrgDetail();
			OrgDetailsDao orgdao = DaoSelectorUtil.getOrganizationDao();
			orgDetail = orgdao.loadOrgDetailByOrgId(orgid,OrgUtil.getOwnerid());
			if(orgDetail == null) {
				throw new AppException(404, 5001, "Resource Not Available", "", "");
			}
			return Response.ok().entity(orgDetail.toString()).build();
		}

		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response createOrganization(OrgDetail orgDetail) throws AppException {

			OrgDetailsDao orgdao = DaoSelectorUtil.getOrganizationDao();
			int orgId = orgdao.insert(orgDetail);
			orgDetail.setOrgid(orgId);
			return Response.status(201).entity(orgDetail.toString()).build();
			
		}
		
		
		@POST
		@Path("/{orgid}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response updateOrganization(@PathParam("orgid") int orgid,OrgDetail orgDetail) throws AppException {

			OrgDetailsDao orgdao = DaoSelectorUtil.getOrganizationDao();
			boolean success = orgdao.update(OrgUtil.getOwnerid(),orgid, orgDetail);
			orgDetail.setOrgid(orgid);
			return Response.status(202).entity(orgDetail.toString()).build();
			
		}
		
		@POST
		@Path("/{orgid}/{status}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response disableOrganization(@PathParam("orgid") int orgid,@PathParam("status") int status) throws AppException, JSONException {
			JSONObject resJson = new JSONObject();
			OrgDetailsDao orgdao = DaoSelectorUtil.getOrganizationDao();
			boolean success = orgdao.updateOrgStatus(orgid,OrgUtil.getOwnerid(),status);
			resJson.put("message","updated");
			return Response.status(202).entity(resJson.toString()).build();
			
		}
	}
