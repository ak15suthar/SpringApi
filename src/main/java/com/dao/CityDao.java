package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.CityBean;

@Repository
public class CityDao {

	@Autowired
	JdbcTemplate stmt;

	public void insertCity(CityBean cityBean) {
		stmt.update("insert into city(cityname,stateid) values(?,?)", cityBean.getCityName(), cityBean.getStateId());

	}

	public List<CityBean> listCity() {
		List<CityBean> cityBean = stmt.query("select * from city where isdeleted = 0", BeanPropertyRowMapper.newInstance(CityBean.class));
		return cityBean;
	}

	public CityBean getCityById(int cityId) {
		CityBean cityBean = null;

		try {
			cityBean = stmt.queryForObject("select * from city where cityid = ?", new Object[] { cityId },
					BeanPropertyRowMapper.newInstance(CityBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cityBean;
	}

	public void updateCity(CityBean cityBean) {
		stmt.update("update city set cityname = ?,stateid = ? where cityid = ?", cityBean.getCityName(),
				cityBean.getStateId(), cityBean.getCityId());

	}

	public void deleteCity(int cityId) {
		stmt.update("update city set isdeleted = 1 where cityid = ?",cityId);
		
	}

}
