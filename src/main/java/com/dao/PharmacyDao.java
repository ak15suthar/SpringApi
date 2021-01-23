package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.PharmacyBean;

@Repository
public class PharmacyDao {

	@Autowired
	JdbcTemplate stmt;

	public void addPharmacy(PharmacyBean pharmacyBean) {
		stmt.update(
				"insert into pharmacy(pharmacyname,pharmacytimings,address,phoneno,rating,about,lat,log,cityid,pincode) values(?,?,?,?,?,?,?,?,?,?)",
				pharmacyBean.getPharmacyName(), pharmacyBean.getPharmacyTimings(), pharmacyBean.getAddress(),
				pharmacyBean.getPhoneNo(), pharmacyBean.getRating(), pharmacyBean.getAbout(), pharmacyBean.getLat(),
				pharmacyBean.getLog(), pharmacyBean.getCityId(), pharmacyBean.getPincode());

	}

	public List<PharmacyBean> listPharmacy() {

		List<PharmacyBean> pharmacyBean = stmt.query("select * from pharmacy",
				BeanPropertyRowMapper.newInstance(PharmacyBean.class));

		return pharmacyBean;
	}

	public void updatePharmacy(PharmacyBean pharmacyBean) {
		stmt.update(
				"update pharmacy set pharmacyname = ?,pharmacytimings = ?,address = ?,phoneno = ?,rating = ?,about = ?,lat = ?,log = ?,cityid = ?,pincode = ? where pharmacyid = ?",
				pharmacyBean.getPharmacyName(), pharmacyBean.getPharmacyTimings(), pharmacyBean.getAddress(),
				pharmacyBean.getPhoneNo(), pharmacyBean.getRating(), pharmacyBean.getAbout(), pharmacyBean.getLat(),
				pharmacyBean.getLog(), pharmacyBean.getCityId(), pharmacyBean.getPincode(),
				pharmacyBean.getPharmacyId());

	}

	public void deletePharmacy(int pharmacyId) {
		stmt.update("delete from pharmacy where pharmacyid = ? ",pharmacyId);
		
	}

}
