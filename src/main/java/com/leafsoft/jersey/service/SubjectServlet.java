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
import com.leafsoft.school.dao.SubjectsDao;
import com.leafsoft.school.model.Subject;

@Path("/subject")
public class SubjectServlet {

		private static final Logger LOGGER = Logger.getLogger(StudentServlet.class.getName());

		@GET
		@Path("/{subjectId}")
		@Produces(MediaType.APPLICATION_JSON)
		public Subject getSubject(@PathParam("subjectId") int subjectId) {
			SubjectsDao subjectDao = DaoSelectorUtil.getSubjectDao();
			Subject subjects = subjectDao.getSubjectDetailsById(subjectId);
			return subjects;

		}
		
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public Response getSubject() {
			SubjectsDao subjectDao = DaoSelectorUtil.getSubjectDao();
			List<Subject> subjects = subjectDao.getAllSubject();
			return Response.ok().entity(new JSONArray(subjects).toString()).build();

		}
		
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response createStudent(Subject subject) throws AppException {
			SubjectsDao subjectDao = DaoSelectorUtil.getSubjectDao();
			LOGGER.log(Level.INFO,OrgUtil.getOrgdb());
			LOGGER.log(Level.INFO,new Date().toString());
			LOGGER.log(Level.INFO,String.valueOf(System.currentTimeMillis()));
			LOGGER.log(Level.INFO,new Date(System.currentTimeMillis()).toString());
			int subjectid = subjectDao.addNewSubject(subject);
			subject.setSubjectid(subjectid);
			return Response.status(201).entity(subject).build();
		}
}
