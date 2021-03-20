package com.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.PrescriptionBean;

@Repository
public class PrescriptionDao {

	@Autowired
	JdbcTemplate stmt;

	public void addPrescription(PrescriptionBean prescriptionBean) {
		stmt.update(
				"insert into prescription(patientprofileid,doctorprofileid,appointmentid,description,prescriptiondate,generaladvice,followupcomment) values (?,?,?,?,?,?,?)",
				prescriptionBean.getPatientProfileId(), prescriptionBean.getDoctorProfileId(),
				prescriptionBean.getAppointmentId(), prescriptionBean.getDescription(),
				prescriptionBean.getPrescriptionDate(), prescriptionBean.getGeneralAdvice(),
				prescriptionBean.getFollowupComment());

	}

	public List<PrescriptionBean> listPrescription() {
		List<PrescriptionBean> prescriptionBean = stmt.query("select * from prescription",
				BeanPropertyRowMapper.newInstance(PrescriptionBean.class));
		return prescriptionBean;
	}

	public PrescriptionBean getPrescriptionById(int prescriptionId) {
		PrescriptionBean prescriptionBean = null;

		try {
			prescriptionBean = stmt.queryForObject("select * from prescription where prescriptionid = ?",
					new Object[prescriptionId], BeanPropertyRowMapper.newInstance(PrescriptionBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prescriptionBean;
	}

	public void updatePrescription(PrescriptionBean prescriptionBean) {
		stmt.update(
				"update prescription set patientprofileid = ?,doctorprofileid = ?,appointmentid = ?,description = ?,prescriptiondate = ?,generaladvice = ?,followupcomment = ? where prescriptionid = ?",
				prescriptionBean.getPatientProfileId(), prescriptionBean.getDoctorProfileId(),
				prescriptionBean.getAppointmentId(), prescriptionBean.getDescription(),
				prescriptionBean.getPrescriptionDate(), prescriptionBean.getGeneralAdvice(),
				prescriptionBean.getFollowupComment(), prescriptionBean.getPrescriptionId());

	}

	public void deletePrescription(int prescriptionId) {
		
		
	}

}
