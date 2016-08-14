package com.leafsoft.jersey.service;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;
import org.springframework.web.HttpRequestHandler;

import com.leafsoft.jersey.errorhandling.AppException;
import com.leafsoft.org.OrgUtil;
import com.leafsoft.school.dao.DaoSelectorUtil;
import com.leafsoft.school.dao.OrgDetailsDao;
import com.leafsoft.school.dao.StaffDetailsDao;
import com.leafsoft.school.dao.impl.StaffDetailsDaoImpl;
import com.leafsoft.school.model.OrgDetail;
import com.leafsoft.school.model.StaffDetail;
import com.leafsoft.school.model.StudentDetail;

@Path("/staff")
public class StaffServlet {

	private static final Logger LOGGER = Logger.getLogger(StaffServlet.class.getName());
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public StudentDetail getStudentInJSON() {

		StudentDetail studentdetails = new StudentDetail();
		studentdetails.setGender(((byte) 0));
		studentdetails.setDob(new Date());

		return studentdetails;

	}

	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createStudentInJSON(StudentDetail studentdetail) {

		String result = "Track saved : " + studentdetail;
		return Response.status(201).entity(result).build();
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addStaff(StaffDetail staffdetails) throws AppException {
		StaffDetailsDao staffdao = DaoSelectorUtil.getStaffDetailsDao();
		LOGGER.log(Level.INFO,OrgUtil.getOrgdb());
		LOGGER.log(Level.INFO,new Date().toString());
		LOGGER.log(Level.INFO,String.valueOf(System.currentTimeMillis()));
		LOGGER.log(Level.INFO,new Date(System.currentTimeMillis()).toString());
		int staffid = staffdao.addNewStaff(staffdetails);
		staffdetails.setStaffid(staffid);
		return Response.status(201).entity(staffdetails).build();
	}
}

/*
 * {"staffname":"kumar","contactnumber":"3843948","email":"dinesh@sandbox.com","joiningdate":"2016-04-17","prv_experience":"test","gender":"0","dob":"2016-04-17","designationid":"1000","status":"0","address":"street","state":"TN","city":"chennai","zipcode":"34343","accountno":"34343443","nationality":"indian","primarylanguage":"tamil","secondarylanguage":"english","prvExperience":"test","StaffQualification":[{"qdate":"1460884906452","institution":"de britto","type":"school","grade":"A","percentage":"89"}],"designation":{"designationname":"teacher","designationid":"1000","status":"0"}}
 */
