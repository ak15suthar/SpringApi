package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.ls.LSInput;

import com.bean.CityBean;
import com.bean.ResponseBean;
import com.dao.CityDao;

@CrossOrigin
@RestController
public class CityController {

	@Autowired
	CityDao cityDao;

	@PostMapping("/addCity")
	public ResponseBean<CityBean> addCity(CityBean cityBean) {

		cityDao.insertCity(cityBean);

		ResponseBean<CityBean> responseBean = new ResponseBean<>();

		responseBean.setData(cityBean);
		responseBean.setMsg("City Added!!");
		responseBean.setStatus(200);

		return responseBean;
	}

	@GetMapping("/listCity")
	public ResponseBean<List<CityBean>> listCity() {

		List<CityBean> cityBean = cityDao.listCity();

		ResponseBean<List<CityBean>> responseBean = new ResponseBean<>();
		
		responseBean.setData(cityBean);
		responseBean.setMsg("City List!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@GetMapping("/getCityById/{cityId}")
	public ResponseBean<CityBean> getCityById(@PathVariable("cityId") int cityId,CityBean cityBean){
		cityBean = cityDao.getCityById(cityId);
		
		ResponseBean<CityBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(cityBean);
		responseBean.setMsg("City By Id!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@PutMapping("/updateCity")
	public ResponseBean<CityBean> updateCity(@RequestBody CityBean cityBean){
		cityDao.updateCity(cityBean);
		
		ResponseBean<CityBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(cityBean);
		responseBean.setMsg("City Updated!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@DeleteMapping("/deleteCity/{cityId}")
	public ResponseBean<CityBean> deleteCity(@PathVariable("cityId") int cityId){
		cityDao.deleteCity(cityId);
		
		ResponseBean<CityBean> responseBean = new ResponseBean<>();
		
		responseBean.setMsg("City Deleted!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
}
