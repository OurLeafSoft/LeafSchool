package com.leafsoft.school.dao;

import java.util.List;

import javax.sql.DataSource;

import org.json.JSONArray;

import com.leafsoft.school.model.OrgDetail;

public interface OrgDetailsDao {
	
	public void setDataSource(DataSource datasource);
	
	public int insert(OrgDetail org);
	
	public boolean update(long luid, long orgId, OrgDetail orgdetails);
	
	public OrgDetail loadOrgDetailByOrgId(long orgId,long userId);
	
	public boolean hasOrg(String orgName);
	
	public List<OrgDetail> loadOrgDetailByUserId(long userId);

	public boolean updateOrgStatus(long orgId,long userId,int status);
	
}
