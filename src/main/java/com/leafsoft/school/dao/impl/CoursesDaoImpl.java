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
import com.leafsoft.school.dao.CoursesDao;
import com.leafsoft.school.model.Course;
import com.leafsoft.school.model.OrgDetail;
import com.leafsoft.school.rowmapper.RowMapper;

public class CoursesDaoImpl implements CoursesDao {
	
		private static final Logger LOGGER = Logger.getLogger(CoursesDaoImpl.class.getName());
		
		private DataSource dataSource;
		
		private JdbcTemplate jdbcTemplate;
		
		public void setDataSource(DataSource datasource) {
			this.dataSource = datasource;
			jdbcTemplate = new JdbcTemplate(dataSource);
		}
		
		public Course getCourseByCourseName(String coursename) {
			Course course = null;
			try {
			String sql = "SELECT * FROM Courses WHERE course = ?";
			course = jdbcTemplate.queryForObject(sql,new Object[]{coursename},  new BeanPropertyRowMapper<Course>(Course.class));
			}catch(Exception e) {
				LOGGER.log(Level.INFO,"findByCustomerId():::"+coursename+e.getMessage(),e);
			}
			return course;
		}
		
		public List<Course> getAllCourses() {
			List<Course> courseArray = new ArrayList<>();
			try {
			String sql = "SELECT * FROM Courses";
			courseArray  = jdbcTemplate.query(sql,
					new BeanPropertyRowMapper<Course>(Course.class));
			}catch(Exception e) {
				LOGGER.log(Level.INFO,"getAllCourses():::"+e.getMessage(),e);
			}
			return courseArray;
		}
		
		public Course getCourseByCourseId(long courseid) {
			Course course = null;
			try {
			String sql = "SELECT * FROM Courses WHERE courseid = ?";
			course = jdbcTemplate.queryForObject(sql,new Object[]{courseid},  new BeanPropertyRowMapper<Course>(Course.class));
			}catch(Exception e) {
				LOGGER.log(Level.INFO,"findByCustomerId():::"+courseid+e.getMessage(),e);
			}
			return course;
		}
		
		public int addCourse(Course course) {
			final Course finalCourse = course;
			
			final String sql = "INSERT INTO Courses " +
					"(course, section) VALUES (?, ?)";
			KeyHolder keyHolder = new GeneratedKeyHolder();
				
			jdbcTemplate.update(
					    new PreparedStatementCreator() {
					        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					            PreparedStatement ps =
					                connection.prepareStatement(sql, new String[] {"courseid"});
					            ps.setString(1, finalCourse.getCourse());
					            ps.setString(2, finalCourse.getSection());
					            return ps;
					        }
					    },
					    keyHolder);
				
				return Integer.valueOf(keyHolder.getKey().toString());
		}
		
		public boolean hasCourseWithSection(String coursename,String section) {
			String sql = "SELECT count(*) FROM Courses WHERE course = ? and section = ?";  
			try {
			int count = jdbcTemplate.queryForObject(sql,new Object[] { coursename, section }, Integer.class);
			if(count > 0) {
				return true;
			}
			} catch(Exception e) {
				LOGGER.log(Level.INFO,"hasOrg():::"+coursename+e.getMessage(),e);
			}
			return false;
		    
		}
		
		public boolean updateCourseDetails(Course course,int courseid) {
			String sql = "update Courses set course = ?,section = ? where courseid = ?";
			try {
				int updateRecords = jdbcTemplate.update(sql, new Object[]{course.getCourse(),course.getSection(),courseid});
				if(updateRecords > 0) {
					return true;
				} else {
					return false;
				}
			} catch(Exception e) {
				LOGGER.log(Level.INFO,"hasOrg():::"+course.getCourseid()+e.getMessage(),e);
				return false;
			}
				
		}
		
		public boolean updateCourseStatus(int status,long courseid) {
			String sql = "update Courses set status = ? where courseid = ?";
			try {
				int updateRecords = jdbcTemplate.update(sql,new Object[]{status,courseid});
				if(updateRecords >0) {
					return true;
				}
			} catch(Exception e) {
				LOGGER.log(Level.INFO,"updateCourseStatus()"+courseid+e.getMessage(),e);
			}
			return false;
		}

}
