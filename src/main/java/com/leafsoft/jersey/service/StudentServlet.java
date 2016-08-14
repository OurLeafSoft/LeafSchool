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
import org.json.JSONObject;

import com.leafsoft.jersey.errorhandling.AppException;
import com.leafsoft.org.OrgUtil;
import com.leafsoft.school.dao.DaoSelectorUtil;
import com.leafsoft.school.dao.StaffDetailsDao;
import com.leafsoft.school.dao.StudentDetailsDao;
import com.leafsoft.school.model.StudentDetail;

	@Path("/student")
	public class StudentServlet {

		private static final Logger LOGGER = Logger.getLogger(StudentServlet.class.getName());
		@GET
		@Path("/get")
		@Produces(MediaType.APPLICATION_JSON)
		public StudentDetail getStudentInJSON() {

			StudentDetail studentdetails = new StudentDetail();
			studentdetails.setGender(((byte) 0));
			studentdetails.setDob(new Date());

			return studentdetails;

		}

		@GET
		@Path("/{studentId}")
		@Produces(MediaType.APPLICATION_JSON)
		public StudentDetail getStudent(@PathParam("studentId") int studentId) {
			StudentDetailsDao studentDao = DaoSelectorUtil.getStudentDatailsDao();
			StudentDetail studentdetails = studentDao.getStudentDetailsById(studentId);
			return studentdetails;

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
		public Response createStudent(StudentDetail studentDetail) throws AppException {
			StudentDetailsDao studentDao = DaoSelectorUtil.getStudentDatailsDao();
			LOGGER.log(Level.INFO,OrgUtil.getOrgdb());
			LOGGER.log(Level.INFO,new Date().toString());
			LOGGER.log(Level.INFO,String.valueOf(System.currentTimeMillis()));
			LOGGER.log(Level.INFO,new Date(System.currentTimeMillis()).toString());
			int studentId = studentDao.addNewStudent(studentDetail);
			studentDetail.setStudentid(studentId);
			return Response.status(201).entity(studentDetail).build();
		}
		
	}
//{"studentname":"3843948","dob":"2016-04-17","gender":0,"status":"0","regdate":"1460884906452","StudentContactDetails":{"familyid":"10000"}}