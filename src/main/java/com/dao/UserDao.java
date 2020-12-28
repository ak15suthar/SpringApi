package com.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.UserBean;

@Repository
public class UserDao {

	@Autowired
	JdbcTemplate stmt;

	public UserBean getUserByEmail(String email) {

		UserBean userBean = null;

		try {
			userBean = stmt.queryForObject("select * from users where email = ?",

					new Object[] { email }, BeanPropertyRowMapper.newInstance(UserBean.class));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return userBean;
	}

	public void updatePassword(UserBean userBean) {
		stmt.update("update users set password = ? where email  = ? ",userBean.getPassword(),userBean.getEmail());
	}
}
