package com.leafsoft.school.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.leafsoft.school.dao.LoginDetailsDao;
import com.leafsoft.school.model.Course;
import com.leafsoft.school.model.LoginDetail;

public class LoginDetailDaoImpl implements LoginDetailsDao {

	private static final Logger LOGGER = Logger.getLogger(LoginDetailDaoImpl.class.getName());
	
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void setDataSource(DataSource datasource) {
		this.dataSource = datasource;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public LoginDetail validateUser(String username, String password) {
		LoginDetail loginDetail = null;
		try {
		String sql = "SELECT * FROM LoginDetails WHERE username = ? and password=?";
		loginDetail = jdbcTemplate.queryForObject(sql,new Object[]{username,password},  new BeanPropertyRowMapper<LoginDetail>(LoginDetail.class));
		}catch(Exception e) {
			LOGGER.log(Level.INFO,"findByCustomerId():::"+username+e.getMessage(),e);
		}
		return loginDetail;
	}

	@Override
	public LoginDetail getLoginDetailByUserName(String userName) {
		LoginDetail loginDetail = null;
		try {
		String sql = "SELECT * FROM LoginDetails WHERE username = ?";
		loginDetail = jdbcTemplate.queryForObject(sql,new Object[]{userName},  new BeanPropertyRowMapper<LoginDetail>(LoginDetail.class));
		}catch(Exception e) {
			LOGGER.log(Level.INFO,"findByCustomerId():::"+userName+e.getMessage(),e);
		}
		return loginDetail;
	}

	@Override
	public LoginDetail getLoginByUserId(String userId) {
		LoginDetail loginDetail = null;
		try {
		String sql = "SELECT * FROM LoginDetails WHERE userid = ?";
		loginDetail = jdbcTemplate.queryForObject(sql,new Object[]{userId},  new BeanPropertyRowMapper<LoginDetail>(LoginDetail.class));
		}catch(Exception e) {
			LOGGER.log(Level.INFO,"findByCustomerId():::"+userId+e.getMessage(),e);
		}
		return loginDetail;
	}

	@Override
	public LoginDetail getLoginById(String id) {
		LoginDetail loginDetail = null;
		try {
		String sql = "SELECT * FROM LoginDetails WHERE id = ?";
		loginDetail = jdbcTemplate.queryForObject(sql,new Object[]{id},  new BeanPropertyRowMapper<LoginDetail>(LoginDetail.class));
		}catch(Exception e) {
			LOGGER.log(Level.INFO,"findByCustomerId():::"+id+e.getMessage(),e);
		}
		return loginDetail;
	}

	@Override
	public LoginDetail insertLoginDetails(LoginDetail loginDetail) {
		final LoginDetail finalLogin = loginDetail;
		
		final String sql = "INSERT INTO LoginDetails " +
				"(userid, username,password,role,status) VALUES (?, ?, ?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
			
		jdbcTemplate.update(
				    new PreparedStatementCreator() {
				        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				            PreparedStatement ps =
				                connection.prepareStatement(sql, new String[] {"courseid"});
				            ps.setInt(1, finalLogin.getUserid());
				            ps.setString(2, finalLogin.getUsername());
				            ps.setString(3, finalLogin.getPassword());
				            ps.setString(4, finalLogin.getRole());
				            ps.setInt(5, 1);
				            return ps;
				        }
				    },
				    keyHolder);
			
			int id = Integer.valueOf(keyHolder.getKey().toString());
			loginDetail.setId(id);
			return loginDetail;
	}

	@Override
	public LoginDetail updateStatus(String id, String status) {
		
		return null;
	}


}
