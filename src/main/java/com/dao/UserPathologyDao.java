package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.UserPathologyBean;

@Repository
public class UserPathologyDao {

	@Autowired
	JdbcTemplate stmt;

	public void addUserPathology(UserPathologyBean userPathologyBean) {

		stmt.update("insert into userpathology(userid,pathologyid) values(?,?)", userPathologyBean.getUserId(),
				userPathologyBean.getPathologyId());
	}

	public void updateUserPathology(UserPathologyBean userPathologyBean) {
		
		stmt.update("update userpathology set userid=?,pathologyid=? where userpathologyid=?",
				userPathologyBean.getUserId(), userPathologyBean.getPathologyId());
	}

	public List<UserPathologyBean> listUserPathology(int userId) {
		
		List<UserPathologyBean> userPathologyBean = stmt.query(
				"select u.*,p.*,up.* from users as u,pathology as p,user_pathology as up where up.userid = u.userid and up.pathologyid = p.pathologyid and up.userid = ? and up.isdeleted=0",
				new Object[] { userId }, BeanPropertyRowMapper.newInstance(UserPathologyBean.class));

		return userPathologyBean;
	}
}
