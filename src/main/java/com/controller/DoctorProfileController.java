package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping("/getDoctorById/{userId}")
	public ResponseBean<DoctorProfileBean> getDoctorById(@RequestBody @PathVariable("userId") int userId){
		
		DoctorProfileBean doctorProfileBean = doctorProfileDao.getDoctorById(userId);
		
		ResponseBean<DoctorProfileBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(doctorProfileBean);
		responseBean.setMsg("Doctor By Id!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@PutMapping("/updateDoctor")
	public ResponseBean<DoctorProfileBean> updateDoctor(@RequestBody DoctorProfileBean doctorProfileBean){
		
		doctorProfileDao.updateDoctor(doctorProfileBean);
		
		ResponseBean<DoctorProfileBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(doctorProfileBean);
		responseBean.setMsg("Doctor Updated!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@DeleteMapping("/deleteDoctor/{userId}")
	public ResponseBean<DoctorProfileBean> deleteDoctor(@PathVariable("userId") int userId){
		doctorProfileDao.deleteDoctor(userId);
		
		ResponseBean<DoctorProfileBean> responseBean = new ResponseBean<>();
		
		responseBean.setMsg("Doctor Deleted!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
}
	