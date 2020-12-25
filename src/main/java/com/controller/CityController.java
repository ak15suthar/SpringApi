package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.CityBean;
import com.bean.ResponseBean;
import com.dao.CityDao;

@RestController
public class CityController {
	
	@Autowired
	CityDao cityDao;
	
	@PostMapping("/addCity")
	public ResponseBean<CityBean> addCity(CityBean cityBean){
		
		cityDao.insertCity(cityBean);
		
		ResponseBean<CityBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(cityBean);
		responseBean.setMsg("City Added!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}

}
