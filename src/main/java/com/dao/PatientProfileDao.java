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

	public void addFamilyMember(PatientProfileBean patientProfileBean) {

		stmt.update(
				"insert into patientprofile(patientname,gender,phoneno,email,age,profilepic,cityid,pincode,userid) values(?,?,?,?,?,?,?,?,?)",
				patientProfileBean.getPatientName(), patientProfileBean.getGender(), patientProfileBean.getPhoneNo(),
				patientProfileBean.getEmail(), patientProfileBean.getAge(), patientProfileBean.getProfilePic(),
				patientProfileBean.getCityId(), patientProfileBean.getPincode(), patientProfileBean.getUserId());

	}

	public List<PatientProfileBean> listPatientProfile() {
		List<PatientProfileBean> patientProfileBean = stmt.query("select * from patientprofile",
				BeanPropertyRowMapper.newInstance(PatientProfileBean.class));

		return patientProfileBean;
	}

	public List<PatientProfileBean> listUserPatient(int userId) {

		List<PatientProfileBean> userPatientBean = stmt.query(
				"select pp.*,up.userid from patientprofile as pp,users as up where pp.userid = up.userid and pp.isdeleted =0 and up.userid= ?",
				new Object[] { userId }, BeanPropertyRowMapper.newInstance(PatientProfileBean.class));
		return userPatientBean;
	}

	public PatientProfileBean getPatientProfileById(int userId) {
		
		PatientProfileBean bean = null;
		try {
			System.out.println("this is patient profile userid " + userId);
			bean = stmt.queryForObject(
					"select *,city.cityname from patientprofile as p join city using(cityid) where p.cityid = cityid and userid=? limit 1",
					new Object[] { userId }, BeanPropertyRowMapper.newInstance(PatientProfileBean.class));
		} catch (Exception e) {
		
			e.printStackTrace();

		}
		return bean;
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
