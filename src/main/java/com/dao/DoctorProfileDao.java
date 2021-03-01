package com.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bean.DoctorProfileBean;

@Repository
public class DoctorProfileDao {

	@Autowired
	JdbcTemplate stmt;

	public List<DoctorProfileBean> listDoctor() {
		List<DoctorProfileBean> doctorProfileBean = stmt.query("select * from doctorprofile as d join users as u using (userid) where u.userid=d.userid",
				BeanPropertyRowMapper.newInstance(DoctorProfileBean.class));
		
		return doctorProfileBean;
	}

}
