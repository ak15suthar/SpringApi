package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.DoctorProfileBean;
import com.bean.ResponseBean;
import com.dao.DoctorProfileDao;

@CrossOrigin
@RestController
public class DoctorProfileController {

	@Autowired
	DoctorProfileDao doctorProfileDao;
	
	@GetMapping("/listDoctor")
	public ResponseBean<List<DoctorProfileBean>> listDoctor(){
		List<DoctorProfileBean> doctorProfileBean = doctorProfileDao.listDoctor();
		
		ResponseBean<List<DoctorProfileBean>> responseBean = new ResponseBean<>();
		
		responseBean.setData(doctorProfileBean);
		responseBean.setMsg("Doctor List!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
}
