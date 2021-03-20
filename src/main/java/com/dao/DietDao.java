package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.ls.LSInput;

import com.bean.DietBean;

@Repository
public class DietDao {

	@Autowired
	JdbcTemplate stmt;

	public void addDiet(DietBean dietBean) {
		stmt.update("insert into diet(diettype,dietcontent,agegroup) values(?,?,?)", dietBean.getDietType(),
				dietBean.getDietContent(), dietBean.getAgeGroup());
	}

	public List<DietBean> listDiet() {
		List<DietBean> dietBean = stmt.query("select * from diet where isdeleted = 0",
				BeanPropertyRowMapper.newInstance(DietBean.class));
		return dietBean;
	}

	public void update(DietBean dietBean) {
		stmt.update("update diet set diettype = ?, dietcontent = ?, agegroup = ? where dietid = ?",
				dietBean.getDietType(), dietBean.getDietContent(), dietBean.getAgeGroup(), dietBean.getDietId());

	}

	public void deleteDiet(int dietId) {
		stmt.update("update diet set isdeleted = 1 where dietid = ?", dietId);

	}

	public DietBean getDietById(int dietId) {
		DietBean dietBean = null;

		try {
			dietBean = stmt.queryForObject("select * from diet where dietid = ?", new Object[] { dietId },
					BeanPropertyRowMapper.newInstance(DietBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dietBean;
	}

}
