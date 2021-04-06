package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.bean.PharmacyBean;
import com.bean.UserBean;

@Repository
public class PharmacyDao {

	@Autowired
	JdbcTemplate stmt;

	public int addPharmacy(PharmacyBean pharmacyBean) {
//		stmt.update(
//				"insert into pharmacy(pharmacyname,pharmacytimings,address,phoneno,rating,about,lat,log,cityid,pincode) values(?,?,?,?,?,?,?,?,?,?)",
//				pharmacyBean.getPharmacyName(), pharmacyBean.getPharmacyTimings(), pharmacyBean.getAddress(),
//				pharmacyBean.getPhoneNo(), pharmacyBean.getRating(), pharmacyBean.getAbout(), pharmacyBean.getLat(),
//				pharmacyBean.getLog(), pharmacyBean.getCityId(), pharmacyBean.getPincode());

		KeyHolder keyHolder = new GeneratedKeyHolder();
		String insertSql = "insert into pharmacy(pharmacyname,pharmacytimings,address,phoneno,rating,about,lat,log,cityid,pincode) values(?,?,?,?,?,?,?,?,?,?)";

		stmt.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

				PreparedStatement pstmt = con.prepareStatement(insertSql, java.sql.Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, pharmacyBean.getPharmacyName());
				pstmt.setString(2, pharmacyBean.getPharmacyTimings());
				pstmt.setString(3, pharmacyBean.getAddress());
				pstmt.setString(4, pharmacyBean.getPhoneNo());
				pstmt.setDouble(5, pharmacyBean.getRating());
				pstmt.setString(6, pharmacyBean.getAbout());
				pstmt.setDouble(7, pharmacyBean.getLat());
				pstmt.setDouble(8, pharmacyBean.getLog());
				pstmt.setInt(9, pharmacyBean.getCityId());
				pstmt.setInt(10, pharmacyBean.getPincode());
				return pstmt;
			}
		}, keyHolder);
		int pharmacyId = (Integer) keyHolder.getKeys().get("pharmacyId");
		pharmacyBean.setPharmacyId(pharmacyId);
		return pharmacyBean.getPharmacyId();
	}

	public void addAssignUserPharmacy(PharmacyBean pharmacyBean) {

		int pharmacyId = addPharmacy(pharmacyBean);

		pharmacyBean.setPharmacyId(pharmacyId);
		stmt.update("insert into userpharmacy(userid,pharmacyid) values(?,?)", pharmacyBean.getUserId(),
				pharmacyBean.getPharmacyId());
	}

	public List<PharmacyBean> listPharmacy() {

		List<PharmacyBean> pharmacyBean = stmt.query("select * from pharmacy where isdeleted = 0",
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
		stmt.update("update pharmacy set isdeleted = 1 where pharmacyid = ? ", pharmacyId);

	}

	public PharmacyBean getPharmacyById(int pharmacyId) {
		PharmacyBean pharmacyBean = null;

		try {
			pharmacyBean = stmt.queryForObject("select * from pharmacy where pharmacyid = ?",
					new Object[] { pharmacyId }, BeanPropertyRowMapper.newInstance(PharmacyBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pharmacyBean;
	}

	public List<UserBean> listAssignUserPharmacy() {
		List<UserBean> pharmacyBean = stmt.query("select * from users where roleid = 5",
				BeanPropertyRowMapper.newInstance(UserBean.class));
		return pharmacyBean;
	
	}

}
