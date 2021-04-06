package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.DoctorClinicBean;
import com.bean.ResponseBean;
import com.dao.DoctorClinicDao;

@CrossOrigin
@RestController
public class DoctorClinicController {

	@Autowired
	DoctorClinicDao doctorClinicDao;

	@PostMapping("/addDoctorClinic")
	public ResponseBean<DoctorClinicBean> adddoctclinic(@RequestBody DoctorClinicBean doctorClinicBean) {
//		System.out.println("id"+doctorClinicBean.getDoctorProfileId());
		
		doctorClinicDao.addDoctorClinic(doctorClinicBean);
		ResponseBean<DoctorClinicBean> response = new ResponseBean<>();
		
		response.setData(doctorClinicBean);
		response.setMsg("Doctor Clinic Added successfully...!!");
		response.setStatus(200);

		return response;
	}

	@GetMapping("/listDoctorClinic/{userId}")
	public ResponseBean<List<DoctorClinicBean>> listDoctClinic(@PathVariable("userId") int userId) {
		ResponseBean<List<DoctorClinicBean>> responseBean = new ResponseBean<>();
		// userid docprofileid
		
		List<DoctorClinicBean> doctorClinicBean = doctorClinicDao.listDoctorCLinic(userId);

		responseBean.setData(doctorClinicBean);
		responseBean.setMsg("Doctor Clinic Display..!!!!");
		responseBean.setStatus(200);

		return responseBean;
	}
}
