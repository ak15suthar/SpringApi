package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.UserPharmacyBean;

@Repository
public class UserPharmacyDao {

	@Autowired
	JdbcTemplate stmt;

	public void addUserPharmacy(UserPharmacyBean userPharmacyBean) {

		stmt.update("insert into userpharmacy(userid,pharmacyid) values(?,?)", userPharmacyBean.getUserId(),
				userPharmacyBean.getPharmacyId());

	}

	public void updateUserPharmacy(UserPharmacyBean userPharmacyBean) {
		
		stmt.update("update userpharmacy set userid=?,pharmacyid=? where userpharmacyid=?",
				userPharmacyBean.getUserId(), userPharmacyBean.getPharmacyId());
	}

	public List<UserPharmacyBean> listUserPharmacy(int pharmacyId) {
		
		java.util.List<UserPharmacyBean> userPharmacyBean = stmt.query(
				"select u.*,p.*,up.* from users as u,pharmacy as p,userpharmacy as up where up.userid = u.userid and up.pharmacyid = p.pharmacyid and up.userid = ? and up.isdeleted=0",
				new Object[] { pharmacyId }, BeanPropertyRowMapper.newInstance(UserPharmacyBean.class));
		
		return userPharmacyBean;
	}

	public UserPharmacyBean getPharmacyById(int pharmacyId) {

		UserPharmacyBean userPharmacyBean = null;
		try {
			userPharmacyBean = stmt.queryForObject("select * from userpharmacy where userpharmacyid=?",
					new Object[] { pharmacyId }, BeanPropertyRowMapper.newInstance(UserPharmacyBean.class));
		} catch (Exception e) {

			e.printStackTrace();
		}
		return userPharmacyBean;
	}

	public void deleteUserPharmacy(int pharmacyId) {

		stmt.update("update userpharmacy set isdeleted = 1 where userpharmacyid = ?", pharmacyId);
	}
}
