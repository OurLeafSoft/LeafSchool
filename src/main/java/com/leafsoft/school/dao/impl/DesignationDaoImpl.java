package com.leafsoft.school.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.leafsoft.org.OrgUtil;
import com.leafsoft.school.dao.DesignationDao;
import com.leafsoft.school.model.Course;
import com.leafsoft.school.model.Designation;
import com.leafsoft.school.rowmapper.RowMapper;

public class DesignationDaoImpl implements DesignationDao
{
	private static final Logger LOGGER=Logger.getLogger(DesignationDao.class.getName());
	
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource datasource) {
		this.dataSource = datasource;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public Designation getDesignationByDesignationId(long designationid) {
		Designation desigantion = null;
		try {
		String sql = "SELECT * FROM Designations WHERE designationid = ?";
		desigantion = jdbcTemplate.queryForObject(sql,new Object[]{designationid},  new BeanPropertyRowMapper<Designation>(Designation.class));
		}catch(Exception e) {
			LOGGER.log(Level.INFO,"findByCustomerId():::"+designationid+e.getMessage(),e);
		}
		return desigantion;
	}
	
	public Designation getDesiganationByDesignationName(String designationname)
	{
		return null;
	}
	
	public List<Designation> getAllDesignations()
	{
		return null;
	}
	
	public int addDesignation(Designation designation)
	{
		final Designation finalDesigantion = designation;
		
		final String sql="INSERT INTO designations "+
				"(designationname,designationgroupid,status) VALUES (?,?,?)";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(
			    new PreparedStatementCreator() {
			        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
			            PreparedStatement ps =
			                connection.prepareStatement(sql, new String[] {"designationid"});
			            ps.setString(1, finalDesigantion.getDesignationname());	
			            ps.setInt(2,finalDesigantion.getDesignationGroup().getDesignationgroupid());
			            ps.setInt(3,1);
			            return ps;
			        }
			    },
			    keyHolder);
		
		return Integer.valueOf(keyHolder.getKey().toString());
		
	}
	
	public boolean hasDesignation(String designationname)
	{
		return false;
	}
	
	public boolean updateDesignationDetails(Designation designation,int designationid)
	{
		return false;
	}

	@Override
	public Designation getDesignationByDesingationId(long DesignationId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}