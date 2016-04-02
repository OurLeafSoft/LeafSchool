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

import org.json.JSONArray;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.leafsoft.org.OrgUtil;
import com.leafsoft.school.dao.OrgDetailsDao;
import com.leafsoft.school.model.OrgDetail;
import com.leafsoft.school.model.OrgUserRole;
import com.leafsoft.school.rowmapper.RowMapper;
import com.leafsoft.school.util.CommonUtil;
import com.leafsoft.util.JdbcUtil;

public class OrgDetailsDaoImpl implements OrgDetailsDao{
	
	private static final Logger LOGGER = Logger.getLogger(OrgDetailsDao.class.getName());
	
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource datasource) {
		this.dataSource = datasource;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public int insert(OrgDetail org){
		
		
		final OrgDetail finalOrg = org;
		
		final String sql = "INSERT INTO OrgDetails " +
				"(orgname, address, country, state, city, zipcode,timetype,dateformat,currencycode,createdtime,status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		final String sql1 = "INSERT INTO OrgUserRoles " +
				"(orgid, luid, rolename) VALUES (?, ?, ?)";
		final String sql2 = "update OrgUsers set defaultorgid = ? where luid = ?";
		KeyHolder keyHolder = new GeneratedKeyHolder();
			
		jdbcTemplate.update(
				    new PreparedStatementCreator() {
				        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				            PreparedStatement ps =
				                connection.prepareStatement(sql, new String[] {"orgid"});
				            ps.setString(1, finalOrg.getOrgname());
				            ps.setString(2, finalOrg.getAddress());
				            ps.setString(3, finalOrg.getCountry());
				            ps.setString(4, finalOrg.getState());
				            ps.setString(5, finalOrg.getCity());
				            ps.setString(6, finalOrg.getZipcode());
				            ps.setString(7, finalOrg.getTimetype());
				            ps.setString(8, finalOrg.getDateformat());
				            ps.setString(9, finalOrg.getCurrencycode());
				            ps.setLong(10, System.currentTimeMillis());
				            ps.setInt(11, finalOrg.getStatus());
				            return ps;
				        }
				    },
				    keyHolder);
			
			jdbcTemplate.update(sql1,
			        new Object[] { keyHolder.getKey(), OrgUtil.getOwnerid(), "ROLE_ADMIN"});
			jdbcTemplate.update(sql2,new Object[] { keyHolder.getKey(),OrgUtil.getOwnerid() });
		    
		    //Create User Database
		    JdbcUtil.createDatabase(CommonUtil.getOrgDb(keyHolder.getKey().intValue()));
		    
		    //Set Userdb
		    OrgUtil.setOrgdb(CommonUtil.getOrgDb(keyHolder.getKey().intValue()));
		    
		    //populate user tables
		    JdbcUtil.executeQueryFromFile(JdbcUtil.getUserDataSource());
		    
		    return  keyHolder.getKey().intValue();
			
	}
	
	public OrgDetail loadOrgDetailByOrgIdAndUserId(long orgId,long userId) {
		OrgDetail org = null;
		try {
		String sql = "SELECT * FROM OrgDetails od inner join OrgUserRoles our on od.orgid = our.orgid inner join OrgUsers ou on ou.luid = our.luid WHERE our.luid = ? and our.orgId = ?";
		org = jdbcTemplate.queryForObject(sql,new Object[]{userId,orgId},  new BeanPropertyRowMapper<OrgDetail>(OrgDetail.class));
		}catch(Exception e) {
			LOGGER.log(Level.INFO,"findByCustomerId():::"+orgId+e.getMessage(),e);
		}
		return org;
	}
	
	public OrgDetail loadOrgDetailByOrgId(long orgId) {
		OrgDetail org = null;
		try {
			String sql = "select *from OrgDetails where orgid = ?"; 
			org = jdbcTemplate.queryForObject(sql,new Object[]{orgId},  new BeanPropertyRowMapper<OrgDetail>(OrgDetail.class));
		}catch(Exception e) {
			LOGGER.log(Level.INFO,"findByCustomerId():::"+orgId+e.getMessage(),e);
		}
		return org;
	}
	
	public List<OrgDetail> loadOrgDetailByUserId(long userId) {
		List<OrgDetail> orgArray = new ArrayList<>();
		try {
		String sql = "SELECT * FROM OrgDetails od inner join OrgUserRoles our on od.orgid = our.orgid inner join OrgUsers ou on ou.luid = our.luid WHERE our.luid = ?";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, new Object[]{userId});
		for (Map<String, Object> row : rows) {
			OrgDetail orgDetails = RowMapper.getOrgDetailRow(row);
			orgArray.add(orgDetails);
		}
		}catch(Exception e) {
			LOGGER.log(Level.INFO,"findByCustomerId():::"+userId+e.getMessage(),e);
		}
		return orgArray;
	}
	
	public boolean hasOrg(String orgId) {
		String sql = "SELECT count(*) FROM OrgDetails WHERE orgid = ?";  
		try {
			int count = jdbcTemplate.queryForObject(sql,new Object[] { orgId }, Integer.class);
			if(count > 0) {
				return true;
			}
		} catch(Exception e) {
			LOGGER.log(Level.INFO,"hasOrg():::"+orgId+e.getMessage(),e);
		}
		return false;
	    
	}
	
	public boolean update(long luid, long orgId, OrgDetail orgdetails) {
		
		String sql = "update OrgDetails od inner join OrgUserRoles our on our.orgid = od.orgid inner join OrgUsers ou on ou.luid = our.luid set orgname = ?, address=?, state=?,city=?,zipcode=?,timetype=? , dateformat=?,country=? WHERE od.orgid = ? and and ou.luid=?";  
		try {
			int updateRecords = jdbcTemplate.update(sql,new Object[]{orgdetails.getOrgname(),orgdetails.getAddress(),orgdetails.getState(),orgdetails.getCity(),orgdetails.getZipcode(),orgdetails.getTimetype(),orgdetails.getDateformat(),orgdetails.getCountry(),orgId,luid});
			if(updateRecords >0) {
				return true;
			}
		} catch(Exception e) {
			LOGGER.log(Level.INFO,"hasOrg():::"+orgId+e.getMessage(),e);
		}
		return false;
	}
	
	public boolean updateOrgStatus(long orgId,long userId,int status) {
		String sql = "update OrgDetails od inner join OrgUserRoles our on our.orgid = od.orgid inner join OrgUsers ou on ou.luid = our.luid set status = ? WHERE od.orgid = ? and ou.luid = ?";
		try {
			int updateRecords = jdbcTemplate.update(sql,new Object[]{status,orgId,userId});
			if(updateRecords >0) {
				return true;
			}
		} catch(Exception e) {
			LOGGER.log(Level.INFO,"hasOrg():::"+orgId+e.getMessage(),e);
		}
		return false;
	}

}
