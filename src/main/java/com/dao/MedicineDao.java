package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.MedicineBean;

@Repository
public class MedicineDao {

	@Autowired
	JdbcTemplate stmt;

	public void addMedicine(MedicineBean medicineBean) {
		stmt.update("insert into medicine(medicinename,type) values(?,?)", medicineBean.getMedicineName(),
				medicineBean.getType());

	}

	public List<MedicineBean> listMedicine() {
		List<MedicineBean> medicineBean = stmt.query("select * from medicine where isdeleted = 0",
				BeanPropertyRowMapper.newInstance(MedicineBean.class));
		return medicineBean;
	}

	public void updateMedicine(MedicineBean medicineBean) {
		stmt.update("update medicine set medicinename = ?, type = ? where medicineid = ?",
				medicineBean.getMedicineName(), medicineBean.getType(), medicineBean.getMedicineId());

	}

	public void deleteMedicine(int medicineId) {
		stmt.update("update medicine set isdeleted = 1 where medicineid = ?", medicineId);

	}

	public MedicineBean getMedicineById(int medicineId) {
		MedicineBean medicineBean = null;
		try {
			medicineBean = stmt.queryForObject("select * from medicine where medicineid = ?",
					new Object[] { medicineId }, BeanPropertyRowMapper.newInstance(MedicineBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return medicineBean;
	}

}
