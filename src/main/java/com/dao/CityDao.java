package com.dao;

import org.springframework.beans.factory.annotation.Autowired;
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

	
	
}
