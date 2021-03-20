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

import com.bean.PharmacyBean;
import com.bean.ResponseBean;
import com.dao.PharmacyDao;

@CrossOrigin
@RestController
public class PharmacyController {

	@Autowired
	PharmacyDao pharmacyDao;
	
	@PostMapping("/addPharmacy")
	public ResponseBean<PharmacyBean> addPharmacy(@RequestBody PharmacyBean pharmacyBean){
		pharmacyDao.addPharmacy(pharmacyBean);
		
		ResponseBean<PharmacyBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(pharmacyBean);
		responseBean.setMsg("Pharmacy Added!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@GetMapping("/listPharmacy")
	public ResponseBean<List<PharmacyBean>> listPharmacy(){
		
		List<PharmacyBean> pharmacyBean = pharmacyDao.listPharmacy();
		
		ResponseBean<List<PharmacyBean>> responseBean = new ResponseBean<>();
		
		responseBean.setData(pharmacyBean);
		responseBean.setMsg("Pharmacy List!!");
		responseBean.setStatus(201);
		
		return responseBean;
	}
	
	@GetMapping("/getPharmacyById/{pharmacyId}")
	public ResponseBean<PharmacyBean> getPharmacyById(@RequestBody @PathVariable("pharmacyId") int pharmacyId,PharmacyBean pharmacyBean){
		pharmacyBean = pharmacyDao.getPharmacyById(pharmacyId);
		
		ResponseBean<PharmacyBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(pharmacyBean);
		responseBean.setMsg("Pharmacy By ID!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@PutMapping("/updatePharmacy")
	public ResponseBean<PharmacyBean> updatePharmacy(@RequestBody PharmacyBean pharmacyBean){
		
		pharmacyDao.updatePharmacy(pharmacyBean);
		
		ResponseBean<PharmacyBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(pharmacyBean);
		responseBean.setMsg("Pharmacy Updated!!");
		responseBean.setStatus(202);
		return responseBean;
	}
	
	@DeleteMapping("/deletePharmacy/{pharmacyId}")
	public ResponseBean<PharmacyBean> deletePharmacy(@PathVariable("pharmacyId") int pharmacyId){
		
		pharmacyDao.deletePharmacy(pharmacyId);
		
		ResponseBean<PharmacyBean> responseBean = new ResponseBean<>();
		
		responseBean.setMsg("Pharmacy Deleted!!");
		responseBean.setStatus(202);
		
		return responseBean;
	}
}
