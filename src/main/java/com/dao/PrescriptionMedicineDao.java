package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.PrescriptionMedicineBean;

@Repository
public class PrescriptionMedicineDao {

	@Autowired
	JdbcTemplate stmt;

	public void addPrescriptionMedicine(PrescriptionMedicineBean prescriptionMedicineBean) {
		stmt.update(
				"insert into prescriptionmedicine(prescriptionid,medicineid,frequency,duration,instructions) values(?,?,?,?,?)",
				prescriptionMedicineBean.getPrescriptionId(), prescriptionMedicineBean.getMedicineId(),
				prescriptionMedicineBean.getFrequency(), prescriptionMedicineBean.getDuration(),
				prescriptionMedicineBean.getInstructions());
	}

	public List<PrescriptionMedicineBean> listPrescriptionMedicine() {
		List<PrescriptionMedicineBean> prescriptionMedicineBean = stmt.query("select * from prescriptionmedicine",
				BeanPropertyRowMapper.newInstance(PrescriptionMedicineBean.class));
		return prescriptionMedicineBean;
	}

	public PrescriptionMedicineBean getPrescriptionMedicineById(int prescriptionMedicineId) {
		PrescriptionMedicineBean prescriptionMedicineBean = null;

		try {
			prescriptionMedicineBean = stmt.queryForObject(
					"select * from prescriptionmedicine where prescriptionmedicine = ?",
					new Object[] { prescriptionMedicineId },
					BeanPropertyRowMapper.newInstance(PrescriptionMedicineBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prescriptionMedicineBean;
	}

	public void updatePrescriptionMedicine(PrescriptionMedicineBean prescriptionMedicineBean) {
		stmt.update(
				"update prescriptionmedicine set prescriptionid = ?,medicineid = ?,frequency = ?,duration = ?,instructions = ? where prescriptionmedicineid = ?",
				prescriptionMedicineBean.getPrescriptionId(), prescriptionMedicineBean.getMedicineId(),
				prescriptionMedicineBean.getFrequency(), prescriptionMedicineBean.getDuration(),
				prescriptionMedicineBean.getInstructions(),prescriptionMedicineBean.getPrescriptionMedicineId());
	
		
	}

	public void deletePrescriptionMedicine(int prescriptionMedicineId) {
		stmt.update("");
		
	}

}
