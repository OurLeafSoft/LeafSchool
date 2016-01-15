package com.leafsoft.school.dao;


import javax.sql.DataSource;

import com.leafsoft.school.model.Course;

public interface CoursesDao {
	
	public void setDataSource(DataSource datasource);

	public Course loadCourseByCourse(String courseName);
}
