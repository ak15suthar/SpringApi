package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bean.UserBean;

@Repository
public class SignupDao {

	@Autowired
	JdbcTemplate stmt;

	public void insertSignup(UserBean signupBean) {
		stmt.update("insert into users (email,password,firstname,lastname,gender,roleid) values(?,?,?,?,?,?)",
				signupBean.getEmail(), signupBean.getPassword(), signupBean.getFirstName(), signupBean.getLastName(),
				signupBean.getGender(), signupBean.getRoleId());

	}

	public List<UserBean> listSignup() {

		List<UserBean> signupBean = stmt.query("select * from users",
				BeanPropertyRowMapper.newInstance(UserBean.class));

		return signupBean;
	}

	public void updateSignup(UserBean signupBean) {
		stmt.update(
				"update users set email = ?,password = ?,firstname = ?,lastname = ?,gender = ?,roleid = ? where userid = ?",
				signupBean.getEmail(), signupBean.getPassword(), signupBean.getFirstName(), signupBean.getLastName(),
				signupBean.getGender(), signupBean.getRoleId(), signupBean.getUserId());

	}

	public void deleteSignup(int userId) {
		stmt.update("delete from users where userid = ?", userId);

	}

	public UserBean login(String email, String password) {

		UserBean signupBean = stmt.queryForObject("select * from users where email=? and password=?",
				new Object[] {email,password}, BeanPropertyRowMapper.newInstance(UserBean.class));

		return signupBean;
	}

}
