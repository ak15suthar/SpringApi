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
		List<DoctorProfileBean> doctorProfileBean = stmt.query(
				"select * from doctorprofile as d join users as u using (userid) where u.userid=d.userid and d.isdeleted = 0",
				BeanPropertyRowMapper.newInstance(DoctorProfileBean.class));

		return doctorProfileBean;
	}

	public void updateDoctor(DoctorProfileBean doctorProfileBean) {

		stmt.update(
				"update doctorprofile set qualification=?,specialization=?,experience=?,profilepic=?,about=?,registrationno=? where userid=?",
				doctorProfileBean.getQualification(), doctorProfileBean.getSpecialization(),
				doctorProfileBean.getExperience_in_year(), doctorProfileBean.getProfile_pic(),
				doctorProfileBean.getAbout(), doctorProfileBean.getRegistrationNo(), doctorProfileBean.getUserId());

		stmt.update("update users set email = ?,password = ?,firstname = ?,lastname = ?,gender = ? where userid = ?",
				doctorProfileBean.getEmail(), doctorProfileBean.getPassword(), doctorProfileBean.getFirstName(),
				doctorProfileBean.getLastName(), doctorProfileBean.getGender(), doctorProfileBean.getUserId());
	}

	public DoctorProfileBean getDoctorById(int userId) {

		DoctorProfileBean doctorProfileBean = null;

		try {
			doctorProfileBean = stmt.queryForObject(
					"select * from doctorprofile as d join users using(userid) where userid = d.userid and userid=?",
					new Object[] { userId }, BeanPropertyRowMapper.newInstance(DoctorProfileBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return doctorProfileBean;
	}

	public void deleteDoctor(int userId) {
		stmt.update("update doctorprofile set isdeleted = 1 where userid = ?", userId);

	}

}
