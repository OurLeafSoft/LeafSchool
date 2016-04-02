package com.leafsoft.school.dao;

import com.leafsoft.school.model.LoginDetail;

public interface LoginDetailsDao extends DaoBean {
	
	public LoginDetail validateUser(String username,String password);

	public LoginDetail getLoginDetailByUserName(String userName);
	
	public LoginDetail getLoginByUserId(String userId);
	
	public LoginDetail getLoginById(String id);
	
	public LoginDetail insertLoginDetails(LoginDetail logindetail);
	
	public LoginDetail updateStatus(String id,String status);
	
}
