package com.leafsoft.school.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.leafsoft.school.dao.StudentDetailsDao;
import com.leafsoft.school.model.StaffDetail;
import com.leafsoft.school.model.StudentContactDetail;
import com.leafsoft.school.model.StudentDetail;
import com.leafsoft.school.model.Subject;
import com.leafsoft.school.rowmapper.RowMapper;

public class StudentDetailsDaoImpl implements StudentDetailsDao {

private static final Logger LOGGER = Logger.getLogger(StaffDetailsDaoImpl.class.getName());
	
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource datasource) {
		this.dataSource = datasource;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public StudentDetail getStudentDetailsById(long studentid) {
		StudentDetail studentDetail = null;
		try {
		String sql = "SELECT * FROM StudentDetails sd inner join StudentContactDetails scd on sd.familyid=scd.familyid WHERE studentid = ?";
		studentDetail = jdbcTemplate.queryForObject(sql,new Object[]{studentid},  new BeanPropertyRowMapper<StudentDetail>(StudentDetail.class));
		StudentContactDetail studentContactDetail = jdbcTemplate.queryForObject(sql,new Object[]{studentid},  new BeanPropertyRowMapper<StudentContactDetail>(StudentContactDetail.class));
		studentDetail.setStudentContactDetail(studentContactDetail);
		}catch(Exception e) {
			LOGGER.log(Level.INFO,"findByCustomerId():::"+studentid+e.getMessage(),e);
		}
		return studentDetail;
	}
	
	@Override
	public int addNewStudent(StudentDetail studentDetail) {
		final StudentDetail finalstudentDetail = studentDetail;
		KeyHolder keyHolder = new GeneratedKeyHolder();
		try {
		final String sql = "INSERT INTO StudentDetails " +
				"(studentname, dob, gender, status, regdate,familyid) VALUES (?, ?, ?, ?, ?, ?)";
			
		jdbcTemplate.update(
				    new PreparedStatementCreator() {
				        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				            PreparedStatement ps =
				                connection.prepareStatement(sql, new String[] {"studentid"});
				            ps.setString(1, finalstudentDetail.getStudentname());
				            ps.setDate(2,(Date) finalstudentDetail.getDob());
				            ps.setInt(3, finalstudentDetail.getGender());
				            ps.setInt(4, finalstudentDetail.getStatus());
				            ps.setLong(5, Long.valueOf(finalstudentDetail.getRegdate().toString()));
				            ps.setInt(6, finalstudentDetail.getStudentContactDetail().getFamilyid());
				            return ps;
				        }
				    },
				    keyHolder);
			LOGGER.log(Level.INFO, "StudentDetails::" + keyHolder.getKey().intValue());
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE,e.getMessage(),e);
		}
		    return  keyHolder.getKey().intValue();
	}

	@Override
	public int getTotalNumberOfStudent() {
		int total = 0;
		try {
		String sql = "SELECT COUNT(*) FROM StudentDetails";
		total = jdbcTemplate.queryForObject(
                sql, Integer.class);
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE,e.getMessage(),e);
		}
		return total;
	}

	@Override
	public List<StudentDetail> getAllStudents() {
		List<StudentDetail> studentArray = new ArrayList<>();
		try {
		String sql = "SELECT * FROM StudentDetails";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map<String, Object> row : rows) {
			StudentDetail studentDetail = RowMapper.getStudentDetailRow(row);
			studentArray.add(studentDetail);
		}
		}catch(Exception e) {
			LOGGER.log(Level.INFO,"getAllSubject():::"+e.getMessage(),e);
		}
		return studentArray;
	}
}
