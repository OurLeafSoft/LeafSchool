package com.leafsoft.school.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.json.JSONArray;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.leafsoft.school.dao.StaffDetailsDao;
import com.leafsoft.school.model.StaffDetail;

public class StaffDetailsDaoImpl implements StaffDetailsDao {

	private static final Logger LOGGER = Logger.getLogger(StaffDetailsDaoImpl.class.getName());
	
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource datasource) {
		this.dataSource = datasource;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public StaffDetail getStaffDetailsById(long staffid) {
		StaffDetail staffdetail = null;
		try {
		String sql = "SELECT * FROM StaffDetails WHERE staffid = ?";
		staffdetail = jdbcTemplate.queryForObject(sql,new Object[]{staffid},  new BeanPropertyRowMapper<StaffDetail>(StaffDetail.class));
		}catch(Exception e) {
			LOGGER.log(Level.INFO,"findByCustomerId():::"+staffid+e.getMessage(),e);
		}
		return staffdetail;
	}

	@Override
	public int addNewStaff(StaffDetail staffDetail) {
		final StaffDetail finalStaffDetail = staffDetail;
		KeyHolder keyHolder = new GeneratedKeyHolder();
		try {
		final String sql = "INSERT INTO StaffDetails " +
				"(staffname, contactnumber, email, joiningdate, prv_experience,gender,dob, designationid, status,address,state,city,zipcode,accountno,nationality,primarylanguage, secondarylanguage) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?,?)";
			
		jdbcTemplate.update(
				    new PreparedStatementCreator() {
				        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				            PreparedStatement ps =
				                connection.prepareStatement(sql, new String[] {"staffid"});
				            ps.setString(1, finalStaffDetail.getStaffname());
				            ps.setLong(2, Long.valueOf(finalStaffDetail.getContactnumber().toString()));
				            ps.setString(3, finalStaffDetail.getEmail());
				            ps.setDate(4, (Date) finalStaffDetail.getJoiningdate());
				            ps.setString(5, finalStaffDetail.getPrvExperience());
				            ps.setInt(6, finalStaffDetail.getGender());
				            ps.setDate(7, (Date) finalStaffDetail.getDob());
				            ps.setInt(8, finalStaffDetail.getDesignation().getDesignationid());
				            ps.setInt(9, 0);
				            ps.setString(10, finalStaffDetail.getAddress());
				            ps.setString(11, finalStaffDetail.getState());
				            ps.setString(12, finalStaffDetail.getCity());
				            ps.setString(13, finalStaffDetail.getZipcode());
				            ps.setLong(14, Long.valueOf(finalStaffDetail.getAccountno().toString()));
				            ps.setString(15, finalStaffDetail.getNationality());
				            ps.setString(16, finalStaffDetail.getPrimarylanguage());
				            ps.setString(17, finalStaffDetail.getSecondarylanguage());
				            return ps;
				        }
				    },
				    keyHolder);
			LOGGER.log(Level.INFO, "OrgUserRoles:Id::" + keyHolder.getKey().intValue());
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE,e.getMessage(),e);
		}
		    return  keyHolder.getKey().intValue();
	}

	@Override
	public int getTotalNumberOfStaffs(int luid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public JSONArray getAllStaffs() {
		// TODO Auto-generated method stub
		return null;
	}

}
