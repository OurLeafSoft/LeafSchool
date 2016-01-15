package com.leafsoft.school.dao;


import javax.sql.DataSource;

import com.leafsoft.school.model.OrgUser;


public interface OrgUsersDao {
	
	public void setDataSource(DataSource datasource);

	public int insert(OrgUser user);
	
	public OrgUser loadOrgUserByLid(int lid);
	
	public OrgUser loadUserByUsername(String userName);
	
	public boolean hasUser(int lid) ;
	
}
