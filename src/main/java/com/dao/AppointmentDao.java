package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.AppointmentBean;

@Repository
public class AppointmentDao {

	@Autowired
	JdbcTemplate stmt;

	public void addAppointment(AppointmentBean appointmentBean) {
		appointmentBean.setAppointmentStatusId(4);
		stmt.update(
				"insert into appointment(doctorprofileid,patientprofileid,clinicid,appointmentstatusid,appointmentcreatedate,appointmentdate,appointmenttime,comment,reference,complain) values(?,?,?,?,?,?,?,?,?,?)",
				appointmentBean.getDoctorProfileId(), appointmentBean.getPatientProfileId(),
				appointmentBean.getClinicId(), appointmentBean.getAppointmentStatusId(),
				appointmentBean.getAppointmentCreateDate(), appointmentBean.getAppointmentDate(),
				appointmentBean.getAppointmentTime(), appointmentBean.getComment(), appointmentBean.getReference(),
				appointmentBean.getComplain());

	}

	public AppointmentBean getAppointmentById(int appointmentId) {

		AppointmentBean appointmentBean = null;
		try {
			appointmentBean = stmt.queryForObject(
					"select ap.*,pp.*,cli.* from appointment as ap,patientprofile as pp,clinic as cli where ap.clinicid = cli.clinicid and ap.patientprofileid=pp.patientprofileid and ap.appointmentid=?",
					new Object[] { appointmentId }, BeanPropertyRowMapper.newInstance(AppointmentBean.class));
		} catch (Exception e) {

			e.printStackTrace();
		}
		return appointmentBean;
	}

	public List<AppointmentBean> listAllAppointment() {
		
		List<AppointmentBean> appointmentBean = stmt.query("select ap.*,pp.*,s.*,dp.*,cli.*,du.firstname,du.lastname,du.userid from appointment as ap,patientprofile as pp,appointmentstatus as s,users as du,clinic as cli,doctorprofile as dp where ap.patientprofileid = pp.patientprofileid and ap.doctorprofileid = dp.userid and dp.userid = du.userid and ap.appointmentstatusid = s.appointmentstatusid and ap.clinicid = cli.clinicid",
				BeanPropertyRowMapper.newInstance(AppointmentBean.class));
		return appointmentBean;
	}
	
	public List<AppointmentBean> listAppointment(int userId) {
		List<AppointmentBean> appointmentBean = stmt.query(
				"select p.*,a.*,s.*,u.*,dp.*,cli.* from patientprofile as p,clinic as cli,doctorprofile as dp,users as u,appointment as a,appointmentstatus as s where a.patientprofileid = p.patientprofileid and a.clinicid = cli.clinicid and a.appointmentstatusid = s.appointmentstatusid and u.userid = a.doctorprofileid and a.doctorprofileid = dp.userid and u.userid = ?",
				new Object[] { userId }, BeanPropertyRowMapper.newInstance(AppointmentBean.class));
		return appointmentBean;
	}

	public List<AppointmentBean> listAppointmentForDoctor(int userId) {
		
		List<AppointmentBean> appointmentBean = stmt.query(
				"select p.*,a.*,s.*,dp.*,cli.* from patientprofile as p,clinic as cli,users as u,doctorprofile as dp,appointment as a,appointmentstatus as s where a.patientprofileid = p.patientprofileid and u.userid = dp.userid and a.clinicid = cli.clinicid and a.appointmentstatusid = s.appointmentstatusid and dp.userid = ?",
				new Object[] { userId }, BeanPropertyRowMapper.newInstance(AppointmentBean.class));
		return appointmentBean;
	}

	public void updateAppointment(AppointmentBean appointmentBean) {
		stmt.update(
				"update appointment set doctorprofileid = ?,patientprofileid = ?,clinicid = ?,appointmentstatusid = ?,appointmentcreatedate = ?,appointmentdate = ?,appointmenttime = ?,comment = ?,reference = ? where appointmentid = ?",
				appointmentBean.getDoctorProfileId(), appointmentBean.getPatientProfileId(),
				appointmentBean.getClinicId(), appointmentBean.getAppointmentStatusId(),
				appointmentBean.getAppointmentCreateDate(), appointmentBean.getAppointmentDate(),
				appointmentBean.getAppointmentTime(), appointmentBean.getComment(), appointmentBean.getReference(),
				appointmentBean.getAppointmentId());

	}

	public void deleteAppointment(int appointmentId) {
		stmt.update("delete from appointment where appointmentid = ?", appointmentId);

	}

	public void acceptRejectAppointment(AppointmentBean appointmentBean) {
		stmt.update("update appointment set appointmentstatusid = ? where appointmentid = ?",
				appointmentBean.getAppointmentStatusId(), appointmentBean.getAppointmentId());

	}

	public void rescheduleAppointment(AppointmentBean appointmentBean) {
		stmt.update(
				"update Appointment set statusid = ?,appointmentdate = ?,appointmenttime = ? where appointmentid = ? ",
				appointmentBean.getAppointmentStatusId(), appointmentBean.getAppointmentDate(),
				appointmentBean.getAppointmentTime(), appointmentBean.getAppointmentId());

	}

	public List<AppointmentBean> viewPatientAppointment(int userId) {
		
		List<AppointmentBean> appointmentBean = stmt.query("select p.*,a.*,s.*,u.*,cli.*,dp.userid,du.firstname,du.lastname from patientprofile as p,users as du, doctorprofile as dp,clinic as cli,users as u,appointment as a,appointmentstatus as s where not s.appointmentstatusid = 5 and a.doctorprofileid = dp.userid and dp.userid = du.userid and a.patientprofileid = p.patientprofileid and a.clinicid = cli.clinicid and a.appointmentstatusid = s.appointmentstatusid and p.userid = u.userid and u.userid = ?"
		, new Object[] {userId} ,BeanPropertyRowMapper.newInstance(AppointmentBean.class));
		return appointmentBean;
	}
	
	public List<AppointmentBean> pastAppointmentList(int patientProfileId) {
	
		List<AppointmentBean> bean = stmt.query("select ap.*,cli.clinicname,pp.*,aps.* from appointment as ap,patientprofile as pp,clinic as cli,appointmentstatus as aps where ap.appointmentstatusid = aps.appointmentstatusid and aps.appointmentstatusid = 6 and ap.patientprofileid = pp.patientprofileid and ap.clinicid = cli.clinicid and pp.patientprofileid = ?",
		new Object[] {patientProfileId},BeanPropertyRowMapper.newInstance(AppointmentBean.class));
		return bean;
	}
	
	public void doneAppointment(AppointmentBean appointmentBean) {

		stmt.update("update appointment set appointmentstatusid=? where appointmentid=?", appointmentBean.getAppointmentStatusId(), appointmentBean.getAppointmentId());
	}
	
	public AppointmentBean getPatientDetailsById(int appointmentId) {
	
		AppointmentBean appointmentBean = null;
		try {
			appointmentBean = stmt.queryForObject("select ap.*,pp.*,pres.*,cli.clinicname from appointment as ap,prescription as pres,clinic as cli,patientprofile as pp where ap.patientprofileid=pp.patientprofileid and ap.appointmentid = pres.appointmentid and ap.clinicid = cli.clinicid and ap.appointmentid= ?", new Object[]{appointmentId},
			BeanPropertyRowMapper.newInstance(AppointmentBean.class));
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return appointmentBean;
	}
	
	public AppointmentBean getRescheduleReasonByEmail(String email,int appointmentid) {
		
		AppointmentBean appointmentBean = null;

		try {
			appointmentBean = stmt.queryForObject("select ap.*,pp.*,dp.*,du.firstname,du.lastname from appointment as ap,users as du,doctorprofile as dp,patientprofile as pp where ap.doctorprofileid = dp.userid and dp.userid = du.userid and ap.patientprofileid = pp.patientprofileid and pp.email=? and ap.appointmentid=?",
			new Object[]{email,appointmentid}, BeanPropertyRowMapper.newInstance(AppointmentBean.class));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return appointmentBean;
	}
	
	public AppointmentBean getRejectReasonByEmail(String email, int appointmentid) {
	
		AppointmentBean appointmentBean = null;

		try {
			appointmentBean = stmt.queryForObject("select ap.*,pp.*,dp.*,du.firstname,du.lastname from appointment as ap,users as du,doctorprofile as dp,patientprofile as pp where ap.doctorprofileid = dp.userid and dp.userid = du.userid and ap.patientprofileid = pp.patientprofileid and pp.email=? and ap.appointmentid=?",
			new Object[]{email,appointmentid}, BeanPropertyRowMapper.newInstance(AppointmentBean.class));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return appointmentBean;
	}
	
	public void updateRescheduleAppointment(AppointmentBean appointmentBean) {
	
		stmt.update("update appointment set statusreason=? where appointmentid=?", appointmentBean.getStatusReason(), appointmentBean.getAppointmentId());
	}
	
	public void updateRejectAppointment(AppointmentBean appointmentBean) {
	
		stmt.update("update appointment set statusreason=? where appointmentid=?", appointmentBean.getStatusReason(), appointmentBean.getAppointmentId());
	}
	
	public List<AppointmentBean> doneAppointmentForAllDoctor() {
		
		List<AppointmentBean> appointmentBean = stmt.query("select ap.*,u.*,dp.* from doctorprofile as dp,users as u,appointment as ap where u.userid = ap.doctorprofileid and ap.doctorprofileid = dp.userid and ap.appointmentstatusid = 6",
				BeanPropertyRowMapper.newInstance(AppointmentBean.class));
		
		return appointmentBean;
	}
	
	public List<AppointmentBean> todayAppointment(int userId) {
		
		List<AppointmentBean> appointmentBean = stmt.query("select ap.*,u.*,dp.* from doctorprofile as dp,users as u,appointment as ap where u.userid = ap.doctorprofileid and ap.doctorprofileid = dp.userid and DATE(ap.appointmentcreatedate) = current_date and u.userid = ?",
				new Object[] {userId} ,BeanPropertyRowMapper.newInstance(AppointmentBean.class));
		
		return appointmentBean;
	}

	public List<AppointmentBean> waitForAcceptAppointment(int userId) {
		
		List<AppointmentBean> appointmentBean = stmt.query("select ap.*,u.*,dp.* from doctorprofile as dp,users as u,appointment as ap where u.userid = ap.doctorprofileid and ap.doctorprofileid = dp.userid and ap.appointmentstatusid = 4 and u.userid = ?",
				new Object[] {userId} ,BeanPropertyRowMapper.newInstance(AppointmentBean.class));
	
		return appointmentBean;
	}

	public List<AppointmentBean> acceptAppointment(int userId) {
		
		List<AppointmentBean> appointmentBean = stmt.query("select ap.*,u.*,dp.* from doctorprofile as dp,users as u,appointment as ap where u.userid = ap.doctorprofileid and ap.doctorprofileid = dp.userid and ap.appointmentstatusid = 1 and u.userid = ?",
				new Object[] {userId} ,BeanPropertyRowMapper.newInstance(AppointmentBean.class));
		
		return appointmentBean;
	}

	public List<AppointmentBean> rescheduleAppointment(int userId) {
		
		List<AppointmentBean> appointmentBean = stmt.query("select ap.*,u.*,dp.* from doctorprofile as dp,users as u,appointment as ap where u.userid = ap.doctorprofileid and ap.doctorprofileid = dp.userid and ap.appointmentstatusid = 2 and u.userid = ?"
				, new Object[] {userId} ,BeanPropertyRowMapper.newInstance(AppointmentBean.class));
	
		return appointmentBean;
	}

	public List<AppointmentBean> doneAppointment(int userId) {
		
		List<AppointmentBean> appointmentBean = stmt.query("select ap.*,u.*,dp.* from doctorprofile as dp,users as u,appointment as ap where u.userid = ap.doctorprofileid and ap.doctorprofileid = dp.userid and ap.appointmentstatusid = 6 and u.userid = ?", 
				new Object[] {userId} ,BeanPropertyRowMapper.newInstance(AppointmentBean.class));
		
		return appointmentBean;
	}
}
