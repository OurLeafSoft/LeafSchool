package com.leafsoft.school.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.leafsoft.school.dao.StaffQualificationDao;
import com.leafsoft.school.model.StaffQualification;
import com.leafsoft.school.rowmapper.RowMapper;

public class StaffQualificationDaoImpl implements StaffQualificationDao {
	
private static final Logger LOGGER = Logger.getLogger(StaffQualificationDao.class.getName());
	
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource datasource) {
		this.dataSource = datasource;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public StaffQualification getStaffQualificationById(long staffId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StaffQualification> addNewStaffQualification(List<StaffQualification> staffQualifications) {
		List<StaffQualification> resultQualification = new ArrayList<>();
		final String sql = "INSERT INTO StaffQualification " +
				"(institution,qdate, type, grade, percentage,staffid) VALUES (?, ?, ?, ?, ?, ?)";
		for(final StaffQualification qualification : staffQualifications) {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(
				    new PreparedStatementCreator() {
				        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				            PreparedStatement ps =
				                connection.prepareStatement(sql, new String[] {"id"});
				            ps.setString(1, qualification.getInstitution());
				            ps.setTimestamp(2,(Timestamp) qualification.getQdate());
				            ps.setString(3, qualification.getType());
				            ps.setDouble(4, qualification.getGrade());
				            ps.setDouble(5, qualification.getPercentage());
				            ps.setInt(6, qualification.getStaffDetail().getStaffid());
				            return ps;
				        }
				    },
				    keyHolder);
			qualification.setId(keyHolder.getKey().intValue());
			resultQualification.add(qualification);
		}
		return resultQualification;
	}

	@Override
	public int getTotalNumberOfStaffQualification(long staffId) {
		int total = 0;
		try {
		String sql = "SELECT COUNT(*) FROM StaffQualification where staffid = ?";
		total = jdbcTemplate.queryForObject(
                sql, new Object[]{staffId}, Integer.class);
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE,e.getMessage(),e);
		}
		return total;
	}

	@Override
	public List<StaffQualification> getStaffDetailsByStaffId(int staffid) {
		List<StaffQualification> qualificationArray = new ArrayList<>();
		try {
		String sql = "SELECT * FROM StaffQualification where staffid = ?";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,new Object[]{staffid});
		for (Map<String, Object> row : rows) {
			StaffQualification staffQualification = RowMapper.getStaffQualificationRow(row);
			qualificationArray.add(staffQualification);
		}
		}catch(Exception e) {
			LOGGER.log(Level.INFO,"getAllSubject():::"+e.getMessage(),e);
		}
		return qualificationArray;
	}

}
