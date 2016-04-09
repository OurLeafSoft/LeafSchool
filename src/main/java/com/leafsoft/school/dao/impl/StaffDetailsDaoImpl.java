package com.leafsoft.school.dao.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.leafsoft.school.dao.StaffDetailsDao;
import com.leafsoft.school.model.Course;
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

}
