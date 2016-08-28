package com.leafsoft.jersey.service;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;

import com.leafsoft.jersey.errorhandling.AppException;
import com.leafsoft.org.OrgUtil;
import com.leafsoft.school.dao.DaoSelectorUtil;
import com.leafsoft.school.dao.StaffQualificationDao;
import com.leafsoft.school.dao.StudentDetailsDao;
import com.leafsoft.school.model.StaffQualification;
import com.leafsoft.school.model.StudentDetail;

@Path("/staffqualification")
public class StaffQualificationService {
	
	private static final Logger LOGGER = Logger.getLogger(StaffQualificationService.class.getName());
	
	@GET
	@Path("/{staffid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response setStaffQualification(@PathParam("staffid") int staffid) {
		StaffQualificationDao staffQualificationdao = DaoSelectorUtil.getStaffQualificationDao();
		List<StaffQualification> result = staffQualificationdao.getStaffDetailsByStaffId(staffid);
		return Response.ok().entity(new JSONArray(result).toString()).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStudent() {
		StudentDetailsDao studentDao = DaoSelectorUtil.getStudentDatailsDao();
		List<StudentDetail> studentdetails = studentDao.getAllStudents();
		return Response.ok().entity(new JSONArray(studentdetails).toString()).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createStudent(List<StaffQualification> staffQualifications) throws AppException {
		StaffQualificationDao staffQualificationdao = DaoSelectorUtil.getStaffQualificationDao();
		LOGGER.log(Level.INFO,OrgUtil.getOrgdb());
		LOGGER.log(Level.INFO,new Date().toString());
		LOGGER.log(Level.INFO,String.valueOf(System.currentTimeMillis()));
		LOGGER.log(Level.INFO,new Date(System.currentTimeMillis()).toString());
		List<StaffQualification> result = staffQualificationdao.addNewStaffQualification(staffQualifications);
		return Response.ok().entity(new JSONArray(result).toString()).build();
	}
	
}

//{"studentname":"3843948","dob":"2016-04-17","gender":0,"status":"0","regdate":"1460884906452","StudentContactDetails":{"familyid":"10000"}}
