package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.DoctorClinicBean;

@Repository
public class DoctorClinicDao {

	@Autowired
	JdbcTemplate stmt;

	public void addDoctClinic(DoctorClinicBean doctorClinicBean) {
		stmt.update(
				"insert into doctorclinic(doctorprofileid,clinicid,monday,tuesday,wednesday,thursday,friday,saturday,sunday,threshold) values (?,?,?,?,?,?,?,?,?,?)",
				doctorClinicBean.getDoctorClinicId(), doctorClinicBean.getClinicId(), doctorClinicBean.getMonday(),
				doctorClinicBean.getTuesday(), doctorClinicBean.getWednesday(), doctorClinicBean.getThursday(),
				doctorClinicBean.getFriday(), doctorClinicBean.getSaturday(), doctorClinicBean.getSunday(),
				doctorClinicBean.getThreshold());
	}

	public List<DoctorClinicBean> listDoctorCLinic(int userId) {
		List<DoctorClinicBean> doctorClinicBean = stmt.query(
				"select dc.*,dp.userid,cl.clinicname,u.firstname from doctorclinic as dc,clinic as cl,doctorprofile as dp,users as u where dp.userid = dc.doctorprofileid and cl.clinicid and u.userid = dp.userid and dc.isdeleted = 0 and u.userid = ?",
				new Object[] { userId }, BeanPropertyRowMapper.newInstance(DoctorClinicBean.class));
		return doctorClinicBean;
	}

}
