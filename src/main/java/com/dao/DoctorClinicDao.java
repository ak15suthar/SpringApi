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

	public void addDoctorClinic(DoctorClinicBean doctorClinicBean) {
		//System.out.println("id : "+doctorClinicBean.getDoctorProfileId());
		//doctorClinicBean.setDoctorProfileId(doctorClinicBean.getDoctorProfileId());
		
		stmt.update(
				"insert into doctorclinic(doctorprofileid,clinicid,monday,tuesday,wednesday,thursday,friday,saturday,sunday,threshold) values (?,?,?,?,?,?,?,?,?,?)",
				doctorClinicBean.getDoctorProfileId(), doctorClinicBean.getClinicId(), doctorClinicBean.getMonday(),
				doctorClinicBean.getTuesday(), doctorClinicBean.getWednesday(), doctorClinicBean.getThursday(),
				doctorClinicBean.getFriday(), doctorClinicBean.getSaturday(), doctorClinicBean.getSunday(),
				doctorClinicBean.getThreshold());
	}

	public List<DoctorClinicBean> listDoctorCLinic(int userId) {
		//select dc.*,dp.userid,cli.clinicname,u.firstname from doctorclinic as dc,clinic cli,doctorprofile as dp,users as u where dp.doctorprofileid  = dc.doctorprofileid and cli.clinicid = dc.clinicid and u.userid = dp.userid and dc.isdeleted =0 and u.userid=?
		//select dc.*,dp.userid,cli.clinicname,u.firstname from doctorclinic as dc,clinic cli,doctorprofile as dp,users as u where dp.doctorprofileid  = dc.doctorprofileid and cli.clinicid = dc.clinicid and u.userid = dp.userid and dc.isdeleted =0 and u.userid=?
		List<DoctorClinicBean> doctorClinicBean = stmt.query(
				"select dc.*,dp.userid,cli.clinicname,u.firstname from doctorclinic as dc,clinic cli,doctorprofile as dp,users as u where dp.userid = dc.doctorprofileid and cli.clinicid = dc.clinicid and u.userid = dp.userid and u.userid=?",
				new Object[] { userId }, BeanPropertyRowMapper.newInstance(DoctorClinicBean.class));
		return doctorClinicBean;
	}

	public List<DoctorClinicBean> listDoctorClinicTiming(int clinicid) {
		
		List<DoctorClinicBean> doctorClinicBean = stmt.query("select dc.* from doctorclinic as dc where dc.clinicid = ?", new Object[]{clinicid}, BeanPropertyRowMapper.newInstance(DoctorClinicBean.class));
		
		return doctorClinicBean;
	}
}
