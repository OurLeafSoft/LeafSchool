<%@page import="com.leafsoft.school.model.OrgUserRole"%>
<%@page import="com.leafsoft.school.dao.OrgUsersDao"%>
<%@page import="com.leafsoft.util.JdbcUtil"%>
<%@page import="org.springframework.jdbc.datasource.DriverManagerDataSource"%>
<%@page import="com.leafsoft.school.dao.OrgUserRolesDao"%>
<%@page import="com.leafsoft.school.dao.impl.OrgUserRolesDaoImpl"%>
<% 
/* DriverManagerDataSource datasource = new JdbcUtil().getAccountsDataSource();
OrgUserRolesDao userRoleDao = new OrgUserRolesDaoImpl();
userRoleDao.setDataSource(datasource);
OrgUserRole orguser = userRoleDao.loadOrgUserByLuid(24,1004);
out.print(orguser.getOrgDetail()); */
%>