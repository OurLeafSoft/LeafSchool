package com.leafsoft.school.dao;

import com.leafsoft.school.model.StaffDetail;

public interface StaffDetailsDao extends DaoBean {

	public StaffDetail getStaffDetailsById(long id);
	
}
