package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.MedBean;
import com.bean.PrescriptionBean;
import com.bean.PrescriptionMedicineBean;

@Repository
public class PrescriptionMedicineDao {

	@Autowired
	JdbcTemplate stmt;

	public void addPrescriptionMedicine(PrescriptionMedicineBean prescriptionMedicineBean) {
//		stmt.update(
//				"insert into prescriptionmedicine(prescriptionid,medicineid,frequency,duration,instructions) values(?,?,?,?,?)",
//				prescriptionMedicineBean.getPrescriptionId(), prescriptionMedicineBean.getMedicineId(),
//				prescriptionMedicineBean.getFrequency(), prescriptionMedicineBean.getDuration(),
//				prescriptionMedicineBean.getInstructions());
	}

	public List<PrescriptionMedicineBean> listPrescriptionMedicine(int appointmentId) {
		
		List<PrescriptionMedicineBean> prescriptionMedicineBean = stmt.query("select pm.*,m.*,pres.* from prescriptionmedicine as pm,medicine as m,prescription as pres where pm.medicineid = m.medicineid and pm.prescriptionid = pres.prescriptionid and pres.appointmentid = ?",
		new Object[] {appointmentId},BeanPropertyRowMapper.newInstance(PrescriptionMedicineBean.class));
		
		List<MedBean> med =  stmt.query("select pm.*,me.* from prescriptionmedicine as pm,medicine as me where pm.prescriptionid =  ? and pm.medicineid = me.medicineid", new Object[] {prescriptionMedicineBean.get(0).getPrescriptionId()},BeanPropertyRowMapper.newInstance(MedBean.class));
	
		prescriptionMedicineBean.get(0).setMed((ArrayList<MedBean>)med);
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
//		stmt.update(
//				"update prescriptionmedicine set prescriptionid = ?,medicineid = ?,frequency = ?,duration = ?,instructions = ? where prescriptionmedicineid = ?",
//				prescriptionMedicineBean.getPrescriptionId(), prescriptionMedicineBean.getMedicineId(),
//				prescriptionMedicineBean.getFrequency(), prescriptionMedicineBean.getDuration(),
//				prescriptionMedicineBean.getInstructions(),prescriptionMedicineBean.getPrescriptionMedicineId());
//	
//		
	}

	public void deletePrescriptionMedicine(int prescriptionMedicineId) {
		stmt.update("");
		
	}

}
