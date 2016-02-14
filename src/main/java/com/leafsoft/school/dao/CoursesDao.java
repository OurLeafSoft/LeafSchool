package com.leafsoft.school.dao;


import java.util.List;

import javax.sql.DataSource;

import com.leafsoft.school.model.Course;

public interface CoursesDao {
	
	public void setDataSource(DataSource datasource);
	
	public Course getCourseByCourseName(String coursename);
	
	public Course getCourseByCourseId(long courseid);

	public List<Course> getAllCourses();
	
	public int addCourse(Course course);
	
	public boolean hasCourseWithSection(String coursename,String section);
	
	public boolean updateCourseDetails(Course course,int courseid);
}
