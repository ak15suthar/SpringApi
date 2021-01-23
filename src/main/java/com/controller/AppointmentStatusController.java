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

import com.bean.AppointmentStatusBean;
import com.bean.ResponseBean;
import com.dao.AppointmentStatusDao;

@CrossOrigin
@RestController
public class AppointmentStatusController {

	@Autowired
	AppointmentStatusDao appointmentStatusDao;
	
	@PostMapping("/addAppointmentStatus")
	public ResponseBean<AppointmentStatusBean> addAppointmentStatus(@RequestBody AppointmentStatusBean appointmentStatusBean){
		
		appointmentStatusDao.addAppointmentStatus(appointmentStatusBean);
		
		ResponseBean<AppointmentStatusBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(appointmentStatusBean);
		responseBean.setMsg("AppointmentStatus Added!!");
		responseBean.setStatus(200);
		
		return  responseBean;
	}
	
	@GetMapping("/listAppointmentStatus")
	public ResponseBean<List<AppointmentStatusBean>> listAppointmentStatus(){
		
		List<AppointmentStatusBean> appointmentStatusBean =  appointmentStatusDao.listAppointmentStatus();
		
		ResponseBean<List<AppointmentStatusBean>> responseBean = new ResponseBean<>();
		
		responseBean.setData(appointmentStatusBean);
		responseBean.setMsg("AppointmentStatus List!!");
		responseBean.setStatus(202);
		
		return responseBean;
	}
	
	@PutMapping("/updateAppointmentStatus")
	public ResponseBean<AppointmentStatusBean> updateAppointmentStatus(@RequestBody AppointmentStatusBean appointmentStatusBean){
		
		appointmentStatusDao.updateAppointmentStatus(appointmentStatusBean);
		
		ResponseBean<AppointmentStatusBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(appointmentStatusBean);
		responseBean.setMsg("AppointmentStatus Updated!!");
		responseBean.setStatus(201);
		
		return responseBean;
	}
	
	@DeleteMapping("/deleteAppointmentStatus/{appointmentStatusId}")
	public ResponseBean<AppointmentStatusBean> deleteAppointmentStatus(@PathVariable("appointmentStatusId") int appointmentStatusId){
		
		appointmentStatusDao.deleteAppointmentStatus(appointmentStatusId);
		
		ResponseBean<AppointmentStatusBean> responseBean = new ResponseBean<>();
		
		responseBean.setMsg("AppointmentStatus Deleted!!");
		responseBean.setStatus(202);
		
		return responseBean;
	}
}
