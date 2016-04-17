package com.leafsoft.school.dao;

import org.json.JSONArray;

import com.leafsoft.school.model.OrgUser;
import com.leafsoft.school.model.OrgUserRole;
import com.leafsoft.school.model.StaffDetail;

public interface StaffDetailsDao extends DaoBean {

	public StaffDetail getStaffDetailsById(long id);
	
	public int addNewStaff(StaffDetail staffDetail);
	
	public int getTotalNumberOfStaffs(int luid);
	
	public JSONArray getAllStaffs();
	
}
