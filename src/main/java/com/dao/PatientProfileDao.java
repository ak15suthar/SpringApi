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
	
	public PatientProfileBean getEditUserPatient(int patientid) {
		
		PatientProfileBean bean = null;
		
		try {
			bean = stmt.queryForObject("select pp.*,pu.*,cu.cityname from patientprofile as pp,users as pu,city as cu where pp.userid=pu.userid and pp.cityid = cu.cityid and pp.patientprofileid=?", new Object[]{patientid},
			BeanPropertyRowMapper.newInstance(PatientProfileBean.class));
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
	
	public void updateUserProfile(PatientProfileBean patientBean) {
	
		stmt.update("update patientprofile set patientname=?,gender=?,phoneno=?,email=?,age=?,profilepic=? where patientprofileid=?",
		patientBean.getFirstName(), patientBean.getGender(), patientBean.getPhoneNo(), patientBean.getEmail(), patientBean.getAge(),patientBean.getProfilePic(), patientBean.getPatientProfileId());

		stmt.update(
		"update users set email=?,password=?,firstname=?,lastname=?,gender=? WHERE userid = ?",
		patientBean.getEmail(), patientBean.getPassword(), patientBean.getFirstName(), patientBean.getLastName(), patientBean.getGender(), patientBean.getUserId());

	}
	
	public PatientProfileBean getFamilyMember(int patientid) {
		// TODO Auto-generated method stub
		PatientProfileBean bean = null;
        try {
            bean = stmt.queryForObject("select *,city.cityname from patientprofile as p join city using(cityid) where p.cityid = cityid and patientprofileid = ?" 
            		, new Object[]{patientid},
                    BeanPropertyRowMapper.newInstance(PatientProfileBean.class));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return bean;
	}

	public void updateFamilyMember(PatientProfileBean patientBean) {
	        // TODO Auto-generated method stub
	        stmt.update("update patientprofile set patientname=?,gender=?,phoneno=?,email=?,age=?,userid=? where patientprofileid=?",
	        		patientBean.getPatientName(), patientBean.getGender(), patientBean.getPhoneNo(), patientBean.getEmail(),
	                patientBean.getAge(),patientBean.getUserId(), patientBean.getPatientProfileId());
	    
    }


	public void deletePatientProfile(int patientProfileId) {
		stmt.update("delete from patientprofile where patientprofileid = ?", patientProfileId);

	}

}
