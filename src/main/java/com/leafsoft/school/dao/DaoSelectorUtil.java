package com.leafsoft.school.dao;

import com.leafsoft.school.dao.impl.CoursesDaoImpl;
import com.leafsoft.school.dao.impl.DesignationDaoImpl;
import com.leafsoft.school.dao.impl.LoginDetailDaoImpl;
import com.leafsoft.school.dao.impl.OrgUserRolesDaoImpl;
import com.leafsoft.school.dao.impl.OrgUsersDaoImpl;
import com.leafsoft.school.dao.impl.OrgDetailsDaoImpl;
import com.leafsoft.util.JdbcUtil;

public class DaoSelectorUtil {
	
	public static CoursesDao getCourseDao() {
		CoursesDaoImpl coursesdao = new CoursesDaoImpl();
		coursesdao.setDataSource(JdbcUtil.getUserDataSource());
		return coursesdao;
	}
	
	public static LoginDetailDaoImpl getLoginDao() {
		LoginDetailDaoImpl logindao = new LoginDetailDaoImpl();
		logindao.setDataSource(JdbcUtil.getUserDataSource());
		return logindao;
	}
	
	public static OrgDetailsDao getOrganizationDao() {
		OrgDetailsDaoImpl orgimpl = new OrgDetailsDaoImpl();
		orgimpl.setDataSource(JdbcUtil.getOrgDBDataSource());
		return orgimpl;
	}
	
	public static OrgUserRolesDao getOrgUserRolesDao() {
		OrgUserRolesDaoImpl orguserroledao = new OrgUserRolesDaoImpl();
		orguserroledao.setDataSource(JdbcUtil.getOrgDBDataSource());
		return orguserroledao;
	}
	
	public static OrgUsersDao getOrgUserDao() {
		OrgUsersDaoImpl orguserdaoimpl = new OrgUsersDaoImpl();
		orguserdaoimpl.setDataSource(JdbcUtil.getOrgDBDataSource());
		return orguserdaoimpl;
	}
	
	public static DesignationDao getDesignationDao(){
		DesignationDaoImpl designationdaoimpl = new DesignationDaoImpl();
		designationdaoimpl.setDataSource(JdbcUtil.getUserDataSource());
		return designationdaoimpl;
	}
	
}
