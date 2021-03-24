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

import com.bean.PathologyBean;
import com.bean.UserBean;

@Repository
public class PathologyDao {

	@Autowired
	JdbcTemplate stmt;

	public int addPathology(PathologyBean pathologyBean) {
//		stmt.update(
//				"insert into pathology(pathologyname,pathologytimings,address,phoneno,rating,about,lat,log,cityid,pincode) values(?,?,?,?,?,?,?,?,?,?)",
//				pathologyBean.getPathologyName(), pathologyBean.getPathologyTimings(), pathologyBean.getAddress(),
//				pathologyBean.getPhoneNo(), pathologyBean.getRating(), pathologyBean.getAbout(), pathologyBean.getLat(),
//				pathologyBean.getLog(), pathologyBean.getCityId(), pathologyBean.getPincode());

		KeyHolder keyHolder = new GeneratedKeyHolder();
		String insertSql = "insert into pathology(pathologyname,pathologytimings,address,phoneno,rating,about,lat,log,cityid,pincode) values(?,?,?,?,?,?,?,?,?,?)";

		stmt.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

				PreparedStatement pstmt = con.prepareStatement(insertSql, java.sql.Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, pathologyBean.getPathologyName());
				pstmt.setString(2, pathologyBean.getPathologyTimings());
				pstmt.setString(3, pathologyBean.getAddress());
				pstmt.setString(4, pathologyBean.getPhoneNo());
				pstmt.setDouble(5, pathologyBean.getRating());
				pstmt.setString(6, pathologyBean.getAbout());
				pstmt.setDouble(7, pathologyBean.getLat());
				pstmt.setDouble(8, pathologyBean.getLog());
				pstmt.setInt(9, pathologyBean.getCityId());
				pstmt.setInt(10, pathologyBean.getPincode());
				return pstmt;
			}
		}, keyHolder);
		int pathologyId = (Integer) keyHolder.getKeys().get("pathologyId");
		pathologyBean.setPathologyId(pathologyId);
		return pathologyBean.getPathologyId();

	}

	public void addAssignUserPathology(PathologyBean pathologyBean) {
		// TODO Auto-generated method stub
		int pathologyId = addPathology(pathologyBean);

		pathologyBean.setPathologyId(pathologyId);

		stmt.update("insert into userpathology(userid,pathologyid) values(?,?)", pathologyBean.getUserId(),
				pathologyBean.getPathologyId());
	}

	public List<PathologyBean> listPathology() {

		List<PathologyBean> pathologyBean = stmt.query("select * from pathology where isdeleted = 0",
				BeanPropertyRowMapper.newInstance(PathologyBean.class));

		return pathologyBean;
	}

	public List<UserBean> listAssignUserPathology() {

		List<UserBean> pathologyBean = stmt.query("select * from users where roleid = 6",
				BeanPropertyRowMapper.newInstance(UserBean.class));
		return pathologyBean;
	}

	public void updatePathology(PathologyBean pathologyBean) {
		stmt.update(
				"update pathology set pathologyname = ?,pathologytimings = ?,address = ?,phoneno = ?,rating = ?,about = ?,lat = ?,log = ?,cityid = ?,pincode = ? where pathologyid = ?",
				pathologyBean.getPathologyName(), pathologyBean.getPathologyTimings(), pathologyBean.getAddress(),
				pathologyBean.getPhoneNo(), pathologyBean.getRating(), pathologyBean.getAbout(), pathologyBean.getLat(),
				pathologyBean.getLog(), pathologyBean.getCityId(), pathologyBean.getPincode(),
				pathologyBean.getPathologyId());
	}

	public void deletePathology(int pathologyId) {
		stmt.update("update pathology set isdeleted = 1 where pathologyid = ?", pathologyId);

	}

	public PathologyBean getPathologyById(int pathologyId) {
		PathologyBean pathologyBean = null;
		try {
			pathologyBean = stmt.queryForObject("select * from pathology where pathologyid = ?",
					new Object[] { pathologyId }, BeanPropertyRowMapper.newInstance(PathologyBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pathologyBean;
	}

}
