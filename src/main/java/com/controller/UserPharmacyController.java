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

import com.bean.ResponseBean;
import com.bean.UserPharmacyBean;
import com.dao.UserPharmacyDao;

@CrossOrigin
@RestController
public class UserPharmacyController {

	@Autowired
	UserPharmacyDao userPharmacyDao;

	@PostMapping("/addUserPharmacy")
	public ResponseBean<UserPharmacyBean> addUserPharmacy(@RequestBody UserPharmacyBean userPharmacyBean) {
		userPharmacyDao.addUserPharmacy(userPharmacyBean);
		
		ResponseBean<UserPharmacyBean> response = new ResponseBean<>();
		
		response.setData(userPharmacyBean);
		response.setMsg("User Pharmacy Added Successfully...!!");
		response.setStatus(200);
		
		return response;
	}

	@GetMapping("/listUserPharmacy/{userId}")
	public ResponseBean<List<UserPharmacyBean>> listuserPharmacy(@PathVariable("userId") int userId) {
		
		List<UserPharmacyBean> userPharmacyBean = userPharmacyDao.listUserPharmacy(userId);
		
		ResponseBean<List<UserPharmacyBean>> response = new ResponseBean<>();
		
		response.setData(userPharmacyBean);
		response.setMsg("List Display..!!!!");
		response.setStatus(201);
		
		return response;
	}

	@DeleteMapping("/deleteUserPharmacy/{pharmacyId}")
	public ResponseBean<UserPharmacyBean> deleteUserPharmacy(@PathVariable("pharmacyId") int pharmacyId) {

		userPharmacyDao.deleteUserPharmacy(pharmacyId);
		
		ResponseBean<UserPharmacyBean> response = new ResponseBean<>();
		
		response.setMsg("User Pharmacy Deleted Successfully..!!");
		response.setStatus(200);
		
		return response;
	}

	@GetMapping("/getUserPharmacy/{pharmacyId}")
	public ResponseBean<UserPharmacyBean> getUser(@PathVariable("pharmacyId") int pharmacyId, UserPharmacyBean userPharmacyBean) {

		userPharmacyBean = userPharmacyDao.getPharmacyById(pharmacyId);
		
		ResponseBean<UserPharmacyBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(userPharmacyBean);
		responseBean.setMsg("Single User Pharmacy Return");
		responseBean.setStatus(200);

		return responseBean;
	}

	@PutMapping("/updateUserPharmacy")
	public ResponseBean<UserPharmacyBean> updateUserPharmacy(@RequestBody UserPharmacyBean userPharmacyBean) {
		userPharmacyDao.updateUserPharmacy(userPharmacyBean);
		
		ResponseBean<UserPharmacyBean> response = new ResponseBean<>();
		
		response.setData(userPharmacyBean);
		response.setMsg("User Pharmacy Updated Successfully..!!");
		
		return response;
	}
}
