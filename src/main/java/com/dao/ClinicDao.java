package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.ClinicBean;
import com.bean.UserBean;

@Repository
public class ClinicDao {

	@Autowired
	JdbcTemplate stmt;

	public void addClinic(ClinicBean clinicBean) {
		stmt.update(
				"insert into clinic(clinicname,clinictiming,address,phoneno,rating,about,lat,log,cityid,pincode) values(?,?,?,?,?,?,?,?,?,?)",
				clinicBean.getClinicName(), clinicBean.getClinicTiming(), clinicBean.getAddress(),
				clinicBean.getPhoneNo(), clinicBean.getRating(), clinicBean.getAbout(), clinicBean.getLat(),
				clinicBean.getLog(), clinicBean.getCityId(), clinicBean.getPincode());
	}

	public List<ClinicBean> listClinic() {

		List<ClinicBean> clinicBean = stmt.query("select * from clinic",
				BeanPropertyRowMapper.newInstance(ClinicBean.class));

		return clinicBean;
	}

	public void updateClinic(ClinicBean clinicBean) {

		stmt.update(
				"update clinic set clinicname = ?, clinictiming = ?, address = ?, phoneno = ?, rating = ?, about = ?, lat = ?, log = ?, cityid = ?, pincode = ? where clinicid = ?",
				clinicBean.getClinicName(), clinicBean.getClinicTiming(), clinicBean.getAddress(),
				clinicBean.getPhoneNo(), clinicBean.getRating(), clinicBean.getAbout(), clinicBean.getLat(),
				clinicBean.getLog(), clinicBean.getCityId(), clinicBean.getPincode(),clinicBean.getClinicId());

	}

	public void deleteClinic(int clinicId) {
		stmt.update("delete from clinic where clinicid = ?",clinicId);
		
	}
	
	

}
