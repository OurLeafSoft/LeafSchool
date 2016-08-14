package com.leafsoft.school.dao;

import org.json.JSONArray;

import com.leafsoft.school.model.StudentDetail;

public interface StudentDetailsDao extends DaoBean {

	public StudentDetail getStudentDetailsById(long id);
	
	public int addNewStudent(StudentDetail studentDetail);
	
	public int getTotalNumberOfStudent(int luid);
	
	public JSONArray getAllStudents();
}
