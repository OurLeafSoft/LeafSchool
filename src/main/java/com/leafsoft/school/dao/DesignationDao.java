package com.leafsoft.school.dao;


import java.util.List;

import javax.sql.DataSource;

import com.leafsoft.school.model.Designation;

public interface DesignationDao
{
	public void setDataSource(DataSource datasource);
	
	public Designation getDesiganationByDesignationName(String designationname);
	
	public Designation getDesignationByDesingationId(long DesignationId);
	
	public List<Designation> getAllDesignations();
	
	public int addDesignation(Designation designation);
	
	public boolean hasDesignation(String designationname);
	
	public boolean updateDesignationDetails(Designation designation,int designationid);
}