package com.leafsoft.org.rest;


import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.leafsoft.school.model.StudentDetail;

	@Path("/student")
	public class Student {

		@GET
		@Path("/get")
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
		
	}
