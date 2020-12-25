package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.AppointmentStatusBean;

@Repository
public class AppointmentStatusDao {

	@Autowired
	JdbcTemplate stmt;

	public void addAppointmentStatus(AppointmentStatusBean appointmentStatusBean) {
		stmt.update("insert into appointmentstatus(appointmentstatusname) values(?)",
				appointmentStatusBean.getAppointmentStatusName());

	}

	public List<AppointmentStatusBean> listAppointmentStatus() {
		List<AppointmentStatusBean> appointmentStatusBean = stmt.query("select * from appointmentstatus",
				BeanPropertyRowMapper.newInstance(AppointmentStatusBean.class));
		return appointmentStatusBean;
	}

	public void updateAppointmentStatus(AppointmentStatusBean appointmentStatusBean) {
		stmt.update("update appointmentstatus set appointmentstatusname = ? where appointmentstatusid = ?",
				appointmentStatusBean.getAppointmentStatusName(), appointmentStatusBean.getAppointmentStatusId());

	}

	public void deleteAppointmentStatus(int appointmentStatusId) {
		stmt.update("delete from appointmentstatus where appointmentstatusid = ?", appointmentStatusId);

	}

}
