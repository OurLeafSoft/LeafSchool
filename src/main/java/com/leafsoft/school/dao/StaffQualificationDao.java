package com.leafsoft.school.dao;

import java.util.List;

import com.leafsoft.school.model.StaffQualification;

public interface StaffQualificationDao extends DaoBean {

	public StaffQualification getStaffQualificationById(long staffId);
	
	public List<StaffQualification> addNewStaffQualification(List<StaffQualification> staffQualifications);
	
	public int getTotalNumberOfStaffQualification(long staffId);
	
	public List<StaffQualification> getStaffDetailsByStaffId(int staffid);
}
