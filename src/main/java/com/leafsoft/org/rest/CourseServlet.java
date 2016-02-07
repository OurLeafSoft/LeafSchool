package com.leafsoft.org.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.leafsoft.org.OrgUtil;
import com.leafsoft.school.dao.DaoSelectorUtil;
import com.leafsoft.school.dao.OrganizationDao;
import com.leafsoft.school.model.OrgDetail;

@Path("/course")
public class CourseServlet {

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public OrgDetail getAllCourses() {

		OrgDetail orgDetail = new OrgDetail();
		OrganizationDao orgdao = DaoSelectorUtil.getOrganizationDao();
		return orgDetail;

	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createOrganizationInJSON(OrgDetail orgDetail) {

		OrganizationDao orgdao = DaoSelectorUtil.getOrganizationDao();
		orgdao.insert(orgDetail);
		String result = "OrgDetail saved : " + orgDetail;

		return Response.status(201).entity(result).build();
		
	}
}