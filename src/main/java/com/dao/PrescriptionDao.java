package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.bean.PrescriptionBean;

@Repository
public class PrescriptionDao {

	@Autowired
	JdbcTemplate stmt;

	public int addPrescription(PrescriptionBean prescriptionBean) {
//		stmt.update(
//				"insert into prescription(patientprofileid,doctorprofileid,appointmentid,description,prescriptiondate,generaladvice,followupcomment) values (?,?,?,?,?,?,?)",
//				prescriptionBean.getPatientProfileId(), prescriptionBean.getDoctorProfileId(),
//				prescriptionBean.getAppointmentId(), prescriptionBean.getDescription(),
//				prescriptionBean.getPrescriptionDate(), prescriptionBean.getGeneralAdvice(),
//				prescriptionBean.getFollowupComment());

		KeyHolder keyHolder = new GeneratedKeyHolder();
		String insertSql = "insert into prescription(patientprofileid,doctorprofileid,appointmentid,description,prescriptiondate,generaladvice,followupcomment) values (?,?,?,?,?,?,?)";

		stmt.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

				PreparedStatement pstmt = con.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1, prescriptionBean.getPatientProfileId());
				pstmt.setInt(2, prescriptionBean.getDoctorProfileId());
				pstmt.setInt(3, prescriptionBean.getAppointmentId());
				pstmt.setString(4, prescriptionBean.getDescription());
				pstmt.setDate(5, prescriptionBean.getPrescriptionDate());
				pstmt.setString(6, prescriptionBean.getGeneralAdvice());
				pstmt.setString(7, prescriptionBean.getFollowupComment());

				return pstmt;
			}
		}, keyHolder);
		int prescriptionId = (Integer) keyHolder.getKeys().get("prescriptionid");
		prescriptionBean.setPrescriptionId(prescriptionId);
		return prescriptionBean.getPrescriptionId();

	}

	public void addPrescriptionMedicine(PrescriptionBean prescriptionBean) {

		int prescriptionId = addPrescription(prescriptionBean);

		prescriptionBean.setPrescriptionId(prescriptionId);
		stmt.update(
				"insert into prescriptionmedicine(prescriptionid,medicineid,frequency,duration,instructions) values(?,?,?,?,?)",
				prescriptionBean.getPrescriptionId(), prescriptionBean.getMedicineId(), prescriptionBean.getFrequency(),
				prescriptionBean.getDuration(), prescriptionBean.getInstructions());

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
