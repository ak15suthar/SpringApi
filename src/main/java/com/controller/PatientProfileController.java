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

import com.bean.PatientProfileBean;
import com.bean.ResponseBean;
import com.dao.PatientProfileDao;

@CrossOrigin
@RestController
public class PatientProfileController {

	@Autowired
	PatientProfileDao patientProfileDao;

	@PostMapping("/addPatientProfile")
	public ResponseBean<PatientProfileBean> addPatientProfile(@RequestBody PatientProfileBean patientProfileBean) {

		patientProfileDao.addPatientProfile(patientProfileBean);

		ResponseBean<PatientProfileBean> responseBean = new ResponseBean<>();

		responseBean.setData(patientProfileBean);
		responseBean.setMsg("PatientProfile Added!!");
		responseBean.setStatus(200);

		return responseBean;
	}

	@GetMapping("/listPatientProfile")
	public ResponseBean<List<PatientProfileBean>> listPatientProfile() {

		List<PatientProfileBean> patientProfileBean = patientProfileDao.listPatientProfile();
		
		ResponseBean<List<PatientProfileBean>> responseBean = new ResponseBean<>();
		
		responseBean.setData(patientProfileBean);
		responseBean.setMsg("PatientProfile List!!");
		responseBean.setStatus(200);

		return responseBean;
	}

	@PutMapping("/updatePatientProfile")
	public ResponseBean<PatientProfileBean> updatePatientProfile(@RequestBody PatientProfileBean patientProfileBean) {

		patientProfileDao.updatePatientProfile(patientProfileBean);

		ResponseBean<PatientProfileBean> responseBean = new ResponseBean<>();

		responseBean.setData(patientProfileBean);
		responseBean.setMsg("PatientProfile Updated!!");
		responseBean.setStatus(202);

		return responseBean;
	}

	@DeleteMapping("/deletePatientProfile/{patientProfileId}")
	public ResponseBean<PatientProfileBean> deletePatientProfile(
			@PathVariable("patientProfileId") int patientProfileId) {

		patientProfileDao.deletePatientProfile(patientProfileId);

		ResponseBean<PatientProfileBean> responseBean = new ResponseBean<>();

		responseBean.setMsg("PatientProfile Deleted!!");
		responseBean.setStatus(202);

		return responseBean;
	}
}
