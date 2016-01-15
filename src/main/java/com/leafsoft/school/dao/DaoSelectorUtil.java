package com.leafsoft.school.dao;

import com.leafsoft.school.dao.impl.CoursesDaoImpl;
import com.leafsoft.school.dao.impl.OrgUserRolesDaoImpl;
import com.leafsoft.school.dao.impl.OrgUsersDaoImpl;
import com.leafsoft.school.dao.impl.OrganizationDaoImpl;
import com.leafsoft.util.JdbcUtil;

public class DaoSelectorUtil {
	
	public static CoursesDao getCoursesDao() {
		CoursesDaoImpl coursesdao = new CoursesDaoImpl();
		coursesdao.setDataSource(JdbcUtil.getUserDataSource());
		return coursesdao;
	}
	
	public static OrganizationDao getOrganizationDao() {
		OrganizationDaoImpl orgimpl = new OrganizationDaoImpl();
		orgimpl.setDataSource(JdbcUtil.getUserDataSource());
		return orgimpl;
	}
	
	public static OrgUserRolesDao getOrgUserRolesDao() {
		OrgUserRolesDaoImpl orguserroledao = new OrgUserRolesDaoImpl();
		orguserroledao.setDataSource(JdbcUtil.getUserDataSource());
		return orguserroledao;
	}
	
	public static OrgUsersDao getOrgUserDao() {
		OrgUsersDaoImpl orguserdaoimpl = new OrgUsersDaoImpl();
		orguserdaoimpl.setDataSource(JdbcUtil.getUserDataSource());
		return orguserdaoimpl;
	}
}
