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

import com.bean.AppointmentDiseasePatientBean;
import com.bean.ResponseBean;
import com.dao.AppointmentDiseasePatientDao;

@CrossOrigin
@RestController
public class AppointmentDiseasePatientController {

	@Autowired
	AppointmentDiseasePatientDao appointmentDiseasePatientDao;

	@PostMapping("/addAppointmentDiseasePatient")
	public ResponseBean<AppointmentDiseasePatientBean> addAppointmentDiseasePatient(
			@RequestBody AppointmentDiseasePatientBean appointmentDiseasePatientBean) {

		appointmentDiseasePatientDao.addAppointmentDiseasePatient(appointmentDiseasePatientBean);

		ResponseBean<AppointmentDiseasePatientBean> responseBean = new ResponseBean<>();

		responseBean.setData(appointmentDiseasePatientBean);
		responseBean.setMsg("Appointment Disease Patient Added!!");
		responseBean.setStatus(200);

		return responseBean;
	}

	@GetMapping("/listAppointmentDiseasePatient/{appointmentId}")
	public ResponseBean<List<AppointmentDiseasePatientBean>> listAppointmentDiseasePatient(@PathVariable("appointmentId") int appointmentId) {
		List<AppointmentDiseasePatientBean> appointmentDiseasePatientBean = appointmentDiseasePatientDao
				.listAppointmentDiseasePatient(appointmentId);

		ResponseBean<List<AppointmentDiseasePatientBean>> responseBean = new ResponseBean<>();

		responseBean.setData(appointmentDiseasePatientBean);
		responseBean.setMsg("Appointment Disease Patient List!!");
		responseBean.setStatus(200);

		return responseBean;
	}

	
	@GetMapping("/getAppointmentDiseasePatient/{appointmentDiseasePatientId}")
	public ResponseBean<AppointmentDiseasePatientBean> getAppointmentDisease(
			@PathVariable("appointmentDiseasePatientId") int appointmentDiseasePatientId,
			AppointmentDiseasePatientBean appointmentDiseasePatientBean) {
		appointmentDiseasePatientBean = appointmentDiseasePatientDao
				.getAppointmentDiseasePatientById(appointmentDiseasePatientId);

		ResponseBean<AppointmentDiseasePatientBean> responseBean = new ResponseBean<>();

		responseBean.setData(appointmentDiseasePatientBean);
		responseBean.setMsg("Single Appointment Disease Return");
		responseBean.setStatus(200);

		return responseBean;
	}

	@PutMapping("/updateAppointmentDiseasePatient")
	public ResponseBean<AppointmentDiseasePatientBean> updateAppointmentDiseasePatient(
			@RequestBody AppointmentDiseasePatientBean appointmentDiseasePatientBean) {
		appointmentDiseasePatientDao.updateAppointmentDiseasePatient(appointmentDiseasePatientBean);

		ResponseBean<AppointmentDiseasePatientBean> responseBean = new ResponseBean<>();

		responseBean.setData(appointmentDiseasePatientBean);
		responseBean.setMsg("Appointment Disease Patient Updated!!");
		responseBean.setStatus(200);

		return responseBean;
	}

	@DeleteMapping("/deleteAppointmentDiseasePatient/{appointmentDiseasePatientId}")
	public ResponseBean<AppointmentDiseasePatientBean> deleteAppointmentDiseasePatient(
			@PathVariable("appointmentDiseasePatientId") int appointmentDiseasePatientId) {
		appointmentDiseasePatientDao.deleteAppointmentDisease(appointmentDiseasePatientId);

		ResponseBean<AppointmentDiseasePatientBean> responseBean = new ResponseBean<>();

		responseBean.setMsg("Appointment Disease Patient Deleted!!");
		responseBean.setStatus(200);

		return responseBean;
	}
}
