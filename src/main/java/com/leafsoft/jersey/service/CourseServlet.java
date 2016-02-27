package com.leafsoft.jersey.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
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
import com.leafsoft.school.dao.CoursesDao;
import com.leafsoft.school.dao.DaoSelectorUtil;
import com.leafsoft.school.dao.OrgDetailsDao;
import com.leafsoft.school.model.Course;

@Path("/course")
public class CourseServlet {

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCourses() throws AppException{
		List<Course> orgArray = new ArrayList<>();
		CoursesDao courseDao = DaoSelectorUtil.getCourseDao();
		orgArray = courseDao.getAllCourses();
		return Response.ok().entity(orgArray.toString()).build();
	}
	
	@GET
    @Path("/{courseid}")
    @Produces(MediaType.APPLICATION_JSON)
	public Response getCoursesById(@PathParam("courseid") long courseid) throws AppException{
		Course course = new Course();
		CoursesDao courseDao = DaoSelectorUtil.getCourseDao();
		course = courseDao.getCourseByCourseId(courseid);
		if(course == null) {
			throw new AppException(404, 5001, "Resource Not Available", "", "");
		}
		return Response.ok().entity(course.toString()).build();
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createCourse(Course course) throws AppException {

		CoursesDao courseDao = DaoSelectorUtil.getCourseDao();
		if(!courseDao.hasCourseWithSection(course.getCourse(),course.getSection())) {
			int courseid = courseDao.addCourse(course);
			course.setCourseid(courseid);
			return Response.status(201).entity(course.toString()).build();
		} else {
			throw new AppException(409, 5002, "Resource Already Available", "", "");
		}
		 
	}
	
	@POST
	@Path("/{courseid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCourse(@PathParam("courseid") int courseid,Course course) throws AppException {
		CoursesDao courseDao = DaoSelectorUtil.getCourseDao();
		if(courseDao.getCourseByCourseId(courseid)!=null) {
			if(!courseDao.hasCourseWithSection(course.getCourse(),course.getSection())) {
				boolean success = courseDao.updateCourseDetails(course,courseid);
				if(success) {
					return Response.status(202).entity(course.toString()).build();
				} else {
					throw new AppException(500, 5003, "Internal Error", "", "");
				}
			} else {
				throw new AppException(409, 5002, "Resource Already Available", "", "");
			}
		} else {
			throw new AppException(404, 5001, "Resource Not Available", "", "");
		}
	}
	
	@POST
	@Path("/{courseid}/{status}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response disableOrganization(@PathParam("courseid") long courseid,@PathParam("status") int status) throws AppException, JSONException {
		JSONObject resJson = new JSONObject();
		CoursesDao courseDao = DaoSelectorUtil.getCourseDao();
		boolean success = courseDao.updateCourseStatus(status, courseid);
		resJson.put("message","updated");
		return Response.status(202).entity(resJson.toString()).build();
		
	}
}