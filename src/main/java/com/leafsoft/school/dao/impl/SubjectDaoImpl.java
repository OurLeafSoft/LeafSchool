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
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.leafsoft.school.dao.SubjectsDao;
import com.leafsoft.school.model.OrgDetail;
import com.leafsoft.school.model.StudentContactDetail;
import com.leafsoft.school.model.StudentDetail;
import com.leafsoft.school.model.Subject;
import com.leafsoft.school.rowmapper.RowMapper;

public class SubjectDaoImpl implements SubjectsDao {

private static final Logger LOGGER = Logger.getLogger(StaffDetailsDaoImpl.class.getName());
	
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource datasource) {
		this.dataSource = datasource;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Subject getSubjectDetailsById(long subjectid) {
		Subject subject = null;
		try {
		String sql = "SELECT * FROM Subjects WHERE subjectid = ?";
		subject = jdbcTemplate.queryForObject(sql,new Object[]{subjectid},  new BeanPropertyRowMapper<Subject>(Subject.class));
		}catch(Exception e) {
			LOGGER.log(Level.INFO,"findByCustomerId():::"+subjectid+e.getMessage(),e);
		}
		return subject;
	}
	
	@Override
	public int addNewSubject(Subject subject) {
		final Subject finalSubject = subject;
		KeyHolder keyHolder = new GeneratedKeyHolder();
		try {
		final String sql = "INSERT INTO Subjects " +
				"(subjectname, subjecttype, status) VALUES (?, ?, ?)";
			
		jdbcTemplate.update(
				    new PreparedStatementCreator() {
				        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				            PreparedStatement ps =
				                connection.prepareStatement(sql, new String[] {"subjectid"});
				            ps.setString(1, finalSubject.getSubjectname());
				            ps.setInt(2, finalSubject.getSubjecttype());
				            ps.setInt(3, finalSubject.getStatus());
				            return ps;
				        }
				    },
				    keyHolder);
			LOGGER.log(Level.INFO, "subject::" + keyHolder.getKey().intValue());
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE,e.getMessage(),e);
		}
		    return  keyHolder.getKey().intValue();
	}

	@Override
	public int getTotalNumberOfSubject() {
		int total = 0;
		try {
		String sql = "SELECT COUNT(*) FROM Subjects";
		total = jdbcTemplate.queryForObject(
                sql, Integer.class);
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE,e.getMessage(),e);
		}
		return total;
	}

	@Override
	public List<Subject> getAllSubject() {
		List<Subject> orgArray = new ArrayList<>();
		try {
		String sql = "SELECT * FROM Subjects";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map<String, Object> row : rows) {
			Subject subject = RowMapper.getSubjectRow(row);
			orgArray.add(subject);
		}
		}catch(Exception e) {
			LOGGER.log(Level.INFO,"getAllSubject():::"+e.getMessage(),e);
		}
		return orgArray;
	}

}
