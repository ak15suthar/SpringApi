package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.ResponseBean;
import com.bean.RoleBean;

@Repository
public class RoleDao {

	@Autowired
	JdbcTemplate stmt;

	public void insertRole(RoleBean roleBean) {
		
		stmt.update("insert into role (rolename) values(?)", roleBean.getRoleName());
	}

	public List<RoleBean> listRole() {
		
		List<RoleBean> roleBean = stmt.query("select * from role", BeanPropertyRowMapper.newInstance(RoleBean.class));
		
		return roleBean;
	}

	public void updateRole(RoleBean roleBean) {
		
		stmt.update("update role set rolename = ? where roleid = ?",roleBean.getRoleName(),roleBean.getRoleId());
		
	}

	public void deleteRole(int roleid) {
		
		stmt.update("delete from role where roleid = ?",roleid);
		
	}

}
