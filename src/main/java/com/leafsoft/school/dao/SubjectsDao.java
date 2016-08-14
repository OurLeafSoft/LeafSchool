package com.leafsoft.school.dao;

import java.util.List;


import com.leafsoft.school.model.Subject;

public interface SubjectsDao extends DaoBean {
 
	public Subject getSubjectDetailsById(long id);
	
	public int addNewSubject(Subject subject);
	
	public int getTotalNumberOfSubject();
	
	public List<Subject> getAllSubject();

}
