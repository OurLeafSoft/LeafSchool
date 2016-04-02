package com.leafsoft.school.dao;

import java.util.List;

import javax.sql.DataSource;

import org.json.JSONArray;

import com.leafsoft.school.model.OrgDetail;

public interface OrgDetailsDao extends DaoBean {
	
	public int insert(OrgDetail org);
	
	public boolean update(long luid, long orgId, OrgDetail orgdetails);
	
	public OrgDetail loadOrgDetailByOrgIdAndUserId(long orgId,long userId);
	
	public OrgDetail loadOrgDetailByOrgId(long orgId);
	
	public boolean hasOrg(String orgName);
	
	public List<OrgDetail> loadOrgDetailByUserId(long userId);

	public boolean updateOrgStatus(long orgId,long userId,int status);
	
}
