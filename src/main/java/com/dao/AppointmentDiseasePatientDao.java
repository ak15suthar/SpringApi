package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.AppointmentDiseasePatientBean;

@Repository
public class AppointmentDiseasePatientDao {

	@Autowired
	JdbcTemplate stmt;

	public void addAppointmentDiseasePatient(AppointmentDiseasePatientBean appointmentDiseasePatientBean) {
		stmt.update("insert into appointmentdiseasepatient(appointmentid,diseaseid,patientprofileid) values(?,?,?)",
				appointmentDiseasePatientBean.getAppointmentId(), appointmentDiseasePatientBean.getDiseaseId(),
				appointmentDiseasePatientBean.getPatientProfileId());
	}

	public List<AppointmentDiseasePatientBean> listAppointmentDiseasePatient(int appointmentId) {
		List<AppointmentDiseasePatientBean> appointmentDiseasePatientBean = stmt.query(
				"select ap.*,ad.*,d.* from appointmentdiseasepatient as ad,appointment as ap,disease as d join disease using(diseaseid) where ad.diseaseid = d.diseaseid and ad.appointmentid = ap.appointmentid and ap.appointmentid = ?",
				new Object[] { appointmentId }, BeanPropertyRowMapper.newInstance(AppointmentDiseasePatientBean.class));
		return appointmentDiseasePatientBean;
	}

	public AppointmentDiseasePatientBean getAppointmentDiseasePatientById(int appointmentDiseasePatientId) {

		AppointmentDiseasePatientBean appointmentDiseasePatientBean = null;
		try {
			appointmentDiseasePatientBean = stmt.queryForObject(
					"select * from appointmentdisease where appointmentdiseaseid=?",
					new Object[] { appointmentDiseasePatientId },
					BeanPropertyRowMapper.newInstance(AppointmentDiseasePatientBean.class));
		} catch (Exception e) {

			e.printStackTrace();
		}
		return appointmentDiseasePatientBean;

	}

	public void updateAppointmentDiseasePatient(AppointmentDiseasePatientBean appointmentDiseasePatientBean) {
		stmt.update(
				"update appointmentdiseasepatient set appointmentid = ?, diseaseid = ?, patientprofileid =? where appointmentdiseasepatientid = ?",
				appointmentDiseasePatientBean.getAppointmentId(), appointmentDiseasePatientBean.getDiseaseId(),
				appointmentDiseasePatientBean.getPatientProfileId(),
				appointmentDiseasePatientBean.getAppointmentDiseasePatientId());
	}

	public void deleteAppointmentDisease(int appointmentDiseasePatientId) {
		stmt.update("update appointmentdisease set isdeleted=1 where appointmentdiseaseid = ?",
				appointmentDiseasePatientId);
	}

}
