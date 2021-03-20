package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.PathologyBean;

@Repository
public class PathologyDao {

	@Autowired
	JdbcTemplate stmt;

	public void addPathology(PathologyBean pathologyBean) {
		stmt.update(
				"insert into pathology(pathologyname,pathologytimings,address,phoneno,rating,about,lat,log,cityid,pincode) values(?,?,?,?,?,?,?,?,?,?)",
				pathologyBean.getPathologyName(), pathologyBean.getPathologyTimings(), pathologyBean.getAddress(),
				pathologyBean.getPhoneNo(), pathologyBean.getRating(), pathologyBean.getAbout(), pathologyBean.getLat(),
				pathologyBean.getLog(), pathologyBean.getCityId(), pathologyBean.getPincode());
	}

	public List<PathologyBean> listPathology() {

		List<PathologyBean> pathologyBean = stmt.query("select * from pathology where isdeleted = 0",
				BeanPropertyRowMapper.newInstance(PathologyBean.class));

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
			pathologyBean = stmt.queryForObject("select * from pathology where pathologyid = ?", new Object[] { pathologyId },
					BeanPropertyRowMapper.newInstance(PathologyBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pathologyBean;
	}

}
