package com.leafsoft.org.rest;


import java.util.ArrayList;
import java.util.List;

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

import org.codehaus.jettison.json.JSONObject;

import com.leafsoft.org.OrgUtil;
import com.leafsoft.school.dao.DaoSelectorUtil;
import com.leafsoft.school.dao.OrganizationDao;
import com.leafsoft.school.model.OrgDetail;
import com.leafsoft.org.rest.errorhandling.AppException;

	@Path("/organization")
	public class OrganizationServlet {
		
		
		@GET
		@Path("/")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getOrganizaiontInJSON() throws AppException{
			List<OrgDetail> orgArray = new ArrayList<>();
			OrganizationDao orgdao = DaoSelectorUtil.getOrganizationDao();
			orgArray = orgdao.loadOrgDetailByUserId(OrgUtil.getOwnerid());
			return Response.ok().entity(orgArray).build();
		}
		
		@GET
	    @Path("/{orgid}")
	    @Produces(MediaType.APPLICATION_JSON)
		public Response getOrganizaiontInJSON(@PathParam("orgid") int orgid) throws AppException{
			OrgDetail orgDetail = new OrgDetail();
			OrganizationDao orgdao = DaoSelectorUtil.getOrganizationDao();
			orgDetail = orgdao.loadOrgDetailByOrgId(orgid,OrgUtil.getOwnerid());
			if(orgDetail == null) {
				throw new AppException(500, 5001, "Resource Not Available", "", "");
			}
			return Response.ok().entity(orgDetail).build();
		   }

		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response createOrganization(OrgDetail orgDetail) throws AppException {

			OrganizationDao orgdao = DaoSelectorUtil.getOrganizationDao();
			int orgId = orgdao.insert(orgDetail);
			orgDetail.setOrgid(orgId);
			return Response.status(201).entity(orgDetail).build();
			
		}
		
		
		@POST
		@Path("/{orgid}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response updateOrganization(@PathParam("orgid") int orgid,OrgDetail orgDetail) throws AppException {

			OrganizationDao orgdao = DaoSelectorUtil.getOrganizationDao();
			boolean success = orgdao.update(OrgUtil.getOwnerid(),orgid, orgDetail);
			orgDetail.setOrgid(orgid);
			return Response.status(202).entity(orgDetail).build();
			
		}
		
		@DELETE
		@Path("/{orgid}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response deleteOrganization(@PathParam("orgid") int orgid,OrgDetail orgDetail) throws AppException {
			JSONObject resJson = new JSONObject();
			OrganizationDao orgdao = DaoSelectorUtil.getOrganizationDao();
			boolean success = orgdao.update(OrgUtil.getOwnerid(),orgid, orgDetail);
			orgDetail.setOrgid(orgid);
			return Response.status(202).entity(orgDetail).build();
			
		}
	}
