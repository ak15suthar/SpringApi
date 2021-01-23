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

import com.bean.ClinicBean;
import com.bean.ResponseBean;
import com.dao.ClinicDao;

@CrossOrigin
@RestController
public class ClinicController {
	
	@Autowired
	ClinicDao clinicDao;
	
	@PostMapping("/addClinic")
	public ResponseBean<ClinicBean> addClinic(@RequestBody ClinicBean clinicBean){
		
		clinicDao.addClinic(clinicBean);
		
		ResponseBean<ClinicBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(clinicBean);
		responseBean.setMsg("Clinic Added!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}

	@GetMapping("/listClinic")
	public ResponseBean<List<ClinicBean>> listClinic(){
		
		List<ClinicBean> clinicBean = clinicDao.listClinic();
		
		ResponseBean<List<ClinicBean>> responseBean = new ResponseBean<>();
		
		responseBean.setData(clinicBean);
		responseBean.setMsg("Clinc List!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@PutMapping("/updateClinic")
	public ResponseBean<ClinicBean> updateClinic(@RequestBody ClinicBean clinicBean){
		
		clinicDao.updateClinic(clinicBean);
		
		ResponseBean<ClinicBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(clinicBean);
		responseBean.setMsg("Clinic Updated!!");
		responseBean.setStatus(201);
		
		return responseBean;
	}
	
	@DeleteMapping("/deleteClinic/{clinicId}")
	public ResponseBean<ClinicBean> deleteClinic(@PathVariable("clinicId") int clinicId){
		
		clinicDao.deleteClinic(clinicId);
		
		ResponseBean<ClinicBean> responseBean = new ResponseBean<>();
		
		responseBean.setMsg("Clinic Deleted!!");
		responseBean.setStatus(201);
		
		return responseBean;
	}
}
