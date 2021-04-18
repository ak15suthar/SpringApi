package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.UserDietBean;

@Repository
public class UserDietDao {

	@Autowired
	JdbcTemplate stmt;

	public void addDietUser(UserDietBean userDietBean) {
		
		stmt.update("insert into userdiet(dietid,userid) values(?,?)", userDietBean.getDietId(),
				userDietBean.getUserId());
	}

	public List<UserDietBean> listDietUser(int patientProfileId) {
		
		List<UserDietBean> dietUserBean = stmt.query(
				"select du.*,d.*,pp.* from userdiet as du,patientprofile as pp,diet as d where du.userid = pp.patientprofileid and du.dietid = d.dietid and pp.patientprofileid = ?",
				new Object[] { patientProfileId }, BeanPropertyRowMapper.newInstance(UserDietBean.class));
		return dietUserBean;
	}
}
