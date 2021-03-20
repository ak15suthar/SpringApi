package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.DiseaseBean;

@Repository
public class DiseaseDao {

	@Autowired
	JdbcTemplate stmt;

	public void addDisease(DiseaseBean diseaseBean) {
		stmt.update("insert into disease(diseasename) values(?)", diseaseBean.getDiseaseName());

	}

	public List<DiseaseBean> listDisease() {
		List<DiseaseBean> diseaseBean = stmt.query("select * from disease where isdeleted = 0",
				BeanPropertyRowMapper.newInstance(DiseaseBean.class));
		return diseaseBean;
	}

	public void updateDisease(DiseaseBean diseaseBean) {
		stmt.update("update disease set diseasename = ? where diseaseid = ?", diseaseBean.getDiseaseName(),
				diseaseBean.getDiseaseId());
	}

	public void deleteDisease(int diseaseId) {
		stmt.update("update disease set isdeleted = 1 where diseaseid = ?", diseaseId);

	}

	public DiseaseBean getDiseaseById(int diseaseId) {
		DiseaseBean diseaseBean = null;

		try {
			diseaseBean = stmt.queryForObject("select * from disease where diseaseid = ?", new Object[] { diseaseId },
					BeanPropertyRowMapper.newInstance(DiseaseBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return diseaseBean;
	}

}
