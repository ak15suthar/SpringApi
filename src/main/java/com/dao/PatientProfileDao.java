package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.PatientProfileBean;

@Repository
public class PatientProfileDao {

	@Autowired
	JdbcTemplate stmt;

	public void addPatientProfile(PatientProfileBean patientProfileBean) {
		stmt.update(
				"insert into patientprofile(patientname,gender,phoneno,email,age,profilepic,cityid,pincode) values(?,?,?,?,?,?,?,?)",
				patientProfileBean.getPatientName(), patientProfileBean.getGender(), patientProfileBean.getPhoneNo(),
				patientProfileBean.getEmail(), patientProfileBean.getAge(), patientProfileBean.getProfilePic(),
				patientProfileBean.getCityId(), patientProfileBean.getPincode());

	}

	public List<PatientProfileBean> listPatientProfile() {
		List<PatientProfileBean> patientProfileBean = stmt.query("select * from patientprofile",
				BeanPropertyRowMapper.newInstance(PatientProfileBean.class));

		return patientProfileBean;
	}

	public void updatePatientProfile(PatientProfileBean patientProfileBean) {
		stmt.update(
				"update patientprofile set patientname = ?,gender = ?,phoneno = ?,email = ?,age = ?,profilepic = ?,cityid = ?,pincode = ? where patientprofileid = ?",
				patientProfileBean.getPatientName(), patientProfileBean.getGender(), patientProfileBean.getPhoneNo(),
				patientProfileBean.getEmail(), patientProfileBean.getAge(), patientProfileBean.getProfilePic(),
				patientProfileBean.getCityId(), patientProfileBean.getPincode(),
				patientProfileBean.getPatientProfileId());

	}

	public void deletePatientProfile(int patientProfileId) {
		stmt.update("delete from patientprofile where patientprofileid = ?", patientProfileId);

	}

}
