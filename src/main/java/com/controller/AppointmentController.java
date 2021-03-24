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

import com.bean.AppointmentBean;
import com.bean.ResponseBean;
import com.dao.AppointmentDao;

@CrossOrigin
@RestController
public class AppointmentController {

	@Autowired
	AppointmentDao appointmentDao;
	
	@PostMapping("/addAppointment")
	public ResponseBean<AppointmentBean> addAppointment(@RequestBody AppointmentBean appointmentBean){
		
		appointmentDao.addAppointment(appointmentBean);
		
		ResponseBean<AppointmentBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(appointmentBean);
		responseBean.setMsg("Appointment Added!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@GetMapping("/listAppointment/{userId}")
	public ResponseBean<List<AppointmentBean>> listAppointment(@PathVariable("userId") int userId){
		
		List<AppointmentBean> appointmentBean = appointmentDao.listAppointment(userId);
		
		ResponseBean<List<AppointmentBean>> responseBean = new ResponseBean<>();
		
		responseBean.setData(appointmentBean);
		responseBean.setMsg("Appointment Listed!!");
		responseBean.setStatus(202);
		
		return responseBean;
	}
	
	@PutMapping("/updateAppointment")
	public ResponseBean<AppointmentBean> updateAppointment(@RequestBody AppointmentBean appointmentBean){
		
		appointmentDao.updateAppointment(appointmentBean);
		
		ResponseBean<AppointmentBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(appointmentBean);
		responseBean.setMsg("Appointment Updated!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@DeleteMapping("/deleteAppointment/{appointmentId}")
	public ResponseBean<AppointmentBean> deleteAppointment(@PathVariable("appointmentId") int appointmentId){
		
		appointmentDao.deleteAppointment(appointmentId);
		
		ResponseBean<AppointmentBean> responseBean = new ResponseBean<>();
		
		responseBean.setMsg("Appointment Deleted!!");
		responseBean.setStatus(202);
		
		return responseBean;
	}
	
	@PutMapping("/acceptRejectAppointment")
	public ResponseBean<AppointmentBean> acceptRejectAppointment(@RequestBody AppointmentBean appointmentBean){
		
		appointmentDao.acceptRejectAppointment(appointmentBean);
		
		ResponseBean<AppointmentBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(appointmentBean);
		responseBean.setMsg("Appointment Status Updated Successfully!!");
		responseBean.setStatus(202);
		
		return responseBean;
	}
	
	@PostMapping("/rescheduleAppointment")
	public ResponseBean<AppointmentBean> rescheduleAppointment(@RequestBody AppointmentBean appointmentBean){
		
		appointmentDao.rescheduleAppointment(appointmentBean);
		
		ResponseBean<AppointmentBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(appointmentBean);
		responseBean.setMsg("Appointment Reschedule Updated Successfully!!");
		responseBean.setStatus(202);
		
		return responseBean;
	}
	
}
