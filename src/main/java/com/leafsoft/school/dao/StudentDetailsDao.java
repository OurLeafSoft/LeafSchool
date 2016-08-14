package com.leafsoft.school.dao;

import java.util.List;

import org.json.JSONArray;

import com.leafsoft.school.model.StudentDetail;
import com.leafsoft.school.model.Subject;

public interface StudentDetailsDao extends DaoBean {

	public StudentDetail getStudentDetailsById(long id);
	
	public int addNewStudent(StudentDetail studentDetail);
	
	public int getTotalNumberOfStudent();
	
	public List<StudentDetail> getAllStudents();
}
