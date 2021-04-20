package com.controller;

import java.util.Date;
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
import com.services.MailerService;

@CrossOrigin
@RestController
public class AppointmentController {

	@Autowired
	AppointmentDao appointmentDao;
	
	@Autowired
	MailerService mailerService;


	@PostMapping("/addAppointment")
	public ResponseBean<AppointmentBean> addAppointment(@RequestBody AppointmentBean appointmentBean) {

		appointmentBean.setAppointmentCreateDate(new Date());
		appointmentDao.addAppointment(appointmentBean);

		ResponseBean<AppointmentBean> responseBean = new ResponseBean<>();

		responseBean.setData(appointmentBean);
		responseBean.setMsg("Appointment Added!!");
		responseBean.setStatus(200);

		return responseBean;
	}

	@GetMapping("/getAppointmentId/{appointmentId}")
	public ResponseBean<AppointmentBean> getUser(@PathVariable("appointmentId") int appointmentId,
			AppointmentBean appointmentBean) {

		ResponseBean<AppointmentBean> responseBean = new ResponseBean<>();

		appointmentBean = appointmentDao.getAppointmentById(appointmentId);
		responseBean.setData(appointmentBean);
		responseBean.setMsg("Single Appointment Return");
		responseBean.setStatus(200);

		return responseBean;
	}

	@GetMapping("/listAllAppointment")
	public ResponseBean<List<AppointmentBean>> listAllAppointment() {
		ResponseBean<List<AppointmentBean>> response = new ResponseBean<>();
		
		List<AppointmentBean> appointmentBean = appointmentDao.listAllAppointment();
		
		response.setData(appointmentBean);
		response.setMsg("Appointment List Display..!!!!");
		response.setStatus(201);
	
		return response;
	}
	
	@GetMapping("/listAppointment/{userId}")
	public ResponseBean<List<AppointmentBean>> listAppointment(@PathVariable("userId") int userId) {

		List<AppointmentBean> appointmentBean = appointmentDao.listAppointment(userId);

		ResponseBean<List<AppointmentBean>> responseBean = new ResponseBean<>();

		responseBean.setData(appointmentBean);
		responseBean.setMsg("Appointment Listed!!");
		responseBean.setStatus(202);

		return responseBean;
	}

	@GetMapping("/listAppointmentForDoctor/{userId}")
	public ResponseBean<List<AppointmentBean>> listAppointmentForDoctor(@PathVariable("userId") int userId) {
		
		ResponseBean<List<AppointmentBean>> responseBean = new ResponseBean<>();
		
		System.out.println("Appointment ID " + userId);
		
		List<AppointmentBean> appointmentBean = appointmentDao.listAppointmentForDoctor(userId);
		
		responseBean.setData(appointmentBean);
		responseBean.setMsg("Appointment List Display..!!!!");
		responseBean.setStatus(201);
		
		return responseBean;
	}

	@PutMapping("/updateAppointment")
	public ResponseBean<AppointmentBean> updateAppointment(@RequestBody AppointmentBean appointmentBean) {

		appointmentDao.updateAppointment(appointmentBean);

		ResponseBean<AppointmentBean> responseBean = new ResponseBean<>();

		responseBean.setData(appointmentBean);
		responseBean.setMsg("Appointment Updated!!");
		responseBean.setStatus(200);

		return responseBean;
	}

	@DeleteMapping("/deleteAppointment/{appointmentId}")
	public ResponseBean<AppointmentBean> deleteAppointment(@PathVariable("appointmentId") int appointmentId) {

		appointmentDao.deleteAppointment(appointmentId);

		ResponseBean<AppointmentBean> responseBean = new ResponseBean<>();

		responseBean.setMsg("Appointment Deleted!!");
		responseBean.setStatus(202);

		return responseBean;
	}

	@PutMapping("/acceptRejectAppointment")
	public ResponseBean<AppointmentBean> acceptRejectAppointment(@RequestBody AppointmentBean appointmentBean) {

		appointmentDao.acceptRejectAppointment(appointmentBean);

		ResponseBean<AppointmentBean> responseBean = new ResponseBean<>();

		responseBean.setData(appointmentBean);
		responseBean.setMsg("Appointment Status Updated Successfully!!");
		responseBean.setStatus(202);

		return responseBean;
	}

//	@PostMapping("/rescheduleAppointment")
//	public ResponseBean<AppointmentBean> rescheduleAppointment(@RequestBody AppointmentBean appointmentBean) {
//
//		appointmentDao.rescheduleAppointment(appointmentBean);
//
//		ResponseBean<AppointmentBean> responseBean = new ResponseBean<>();
//
//		responseBean.setData(appointmentBean);
//		responseBean.setMsg("Appointment Reschedule Updated Successfully!!");
//		responseBean.setStatus(202);
//
//		return responseBean;
//	}

	@GetMapping("/rescheduleReason/{email}/{appointmentId}")
	public ResponseBean<AppointmentBean> sendRescheduleReason(@PathVariable("email") String email,@PathVariable("appointmentId") int appointmentId) {
		System.out.println("Reschedule Reason call...");
	
		AppointmentBean bean = appointmentDao.getRescheduleReasonByEmail(email,appointmentId);
	
		ResponseBean<AppointmentBean> responseBean = new ResponseBean<>();
	
		responseBean.setData(bean);
	
		if (bean == null) {
			responseBean.setMsg("Invalid Email Address");
			responseBean.setStatus(201);
		} else {
		mailerService.sendRescheduleReason(bean);
			responseBean.setMsg("Email sent for Reschedule");
			responseBean.setStatus(200);
		}
		return responseBean;
	}


	@GetMapping("/rejectReason/{email}/{appointmentId}")
	public ResponseBean<AppointmentBean> sendRejectedReason(@PathVariable("email") String email,@PathVariable("appointmentId") int appointmentId) {
		System.out.println("Rejected Reason call...");
	
		AppointmentBean bean = appointmentDao.getRejectReasonByEmail(email,appointmentId);
	
		ResponseBean<AppointmentBean> responseBean = new ResponseBean<>();
	
		responseBean.setData(bean);
	
		if (bean == null) {
			responseBean.setMsg("Invalid Email Address");
			responseBean.setStatus(201);
		} else {
		mailerService.sendRejectedReason(bean);
			responseBean.setMsg("Email sent for Rejected");
			responseBean.setStatus(200);
		}	
		return responseBean;
	}	
	
	@PutMapping("/updateRescheduleAppointment")
	public ResponseBean<AppointmentBean> updateRescheduleAppointment(@RequestBody AppointmentBean appointmentBean) {
		appointmentDao.updateRescheduleAppointment(appointmentBean);
	
		ResponseBean<AppointmentBean> response = new ResponseBean<>();
		
		response.setData(appointmentBean);
		response.setMsg("Appointment Reschedule Successfully..!!");
		
		return response;
	}

	@PutMapping("/updateRejectAppointment")
	public ResponseBean<AppointmentBean> updateRejectAppointment(@RequestBody AppointmentBean appointmentBean) {
		System.out.println("s"+appointmentBean.getStatusReason());
		
		appointmentDao.updateRejectAppointment(appointmentBean);
		
		ResponseBean<AppointmentBean> response = new ResponseBean<>();
		
		response.setData(appointmentBean);
		response.setMsg("Appointment Reject Successfully..!!");
	
		return response;
	}
	
	@GetMapping("/viewPatientAppointment/{userId}")
	public ResponseBean<List<AppointmentBean>> viewPatientAppointment(@PathVariable("userId") int userId) {
		ResponseBean<List<AppointmentBean>> response = new ResponseBean<>();

		List<AppointmentBean> appointmentBean = appointmentDao.viewPatientAppointment(userId);
		
		response.setData(appointmentBean);
		response.setMsg("Appointment List Display..!!!!");
		response.setStatus(201);
		
		return response;
	}
	
	@GetMapping("/pastAppointmentList/{patientProfileId}")
	public ResponseBean<List<AppointmentBean>> pastAppointmentList(@PathVariable("patientid") int patientid){
		List<AppointmentBean> Bean = appointmentDao.pastAppointmentList(patientid);
	
		ResponseBean<List<AppointmentBean>> responseBean = new ResponseBean<>();
	
		responseBean.setData(Bean);
		responseBean.setMsg("User Diet List!!");
		responseBean.setStatus(200);
	
		return responseBean;
	}
	
	@PutMapping("/doneAppointment")
	public ResponseBean<AppointmentBean> doneAppointment(@RequestBody AppointmentBean appointmentBean) {
		appointmentDao.doneAppointment(appointmentBean);
		
		ResponseBean<AppointmentBean> response = new ResponseBean<>();
		
		response.setData(appointmentBean);
		response.setMsg("Details Submited Successfully..!!");
		
		return response;
	}
	
	@GetMapping("/getPatientDetails/{appointmentId}")
	public ResponseBean<AppointmentBean> getPatientDetails(@PathVariable("appointmentId") int appointmentId) {
		
		AppointmentBean appointmentBean = null;
		
		//System.out.println("ap"+appointmentBean.getAppointmentDate());
		appointmentBean = appointmentDao.getPatientDetailsById(appointmentId);
		
		ResponseBean<AppointmentBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(appointmentBean);
		responseBean.setMsg("Single Patient Details Return");
		responseBean.setStatus(200);

		return responseBean;
	}
	
	@GetMapping("/doneAppointmentForAllDoctor")
	public ResponseBean<List<AppointmentBean>> doneAppointmentForAllDoctor() {
		ResponseBean<List<AppointmentBean>> response = new ResponseBean<>();
		
		List<AppointmentBean> appointmentBean = appointmentDao.doneAppointmentForAllDoctor();
		
		response.setData(appointmentBean);
		response.setMsg("Done Appointment Display..!!!!");
		response.setStatus(201);
		
		return response;
	}
	
	@GetMapping("/todayAppointment/{userId}")
	public ResponseBean<List<AppointmentBean>> todayAppointment(@PathVariable("userId") int userId) {
	ResponseBean<List<AppointmentBean>> response = new ResponseBean<>();
		List<AppointmentBean> appointmentBean = appointmentDao.todayAppointment(userId);
		
		response.setData(appointmentBean);
		response.setMsg(" Today Appointment Display..!!!!");
		response.setStatus(201);
		
		return response;
	}

	@GetMapping("/waitForAcceptAppointment/{userId}")
	public ResponseBean<List<AppointmentBean>> waitForAcceptAppointment(@PathVariable("userId") int userId) {
	ResponseBean<List<AppointmentBean>> response = new ResponseBean<>();
		List<AppointmentBean> appointmentBean = appointmentDao.waitForAcceptAppointment(userId);
		
		response.setData(appointmentBean);
		response.setMsg(" Wait For Accept Appointment Display..!!!!");
		response.setStatus(201);
		
		return response;
	}

	@GetMapping("/acceptAppointment/{userId}")
	public ResponseBean<List<AppointmentBean>> acceptAppointment(@PathVariable("userId") int userId) {
		ResponseBean<List<AppointmentBean>> response = new ResponseBean<>();
		
		List<AppointmentBean> appointmentBean = appointmentDao.acceptAppointment(userId);
		
		response.setData(appointmentBean);
		response.setMsg("Accept Appointment Display..!!!!");
		response.setStatus(201);
		
		return response;
	}

	@GetMapping("/rescheduleAppointment/{userId}")
	public ResponseBean<List<AppointmentBean>> rescheduleAppointment(@PathVariable("userId") int userId) {
		ResponseBean<List<AppointmentBean>> response = new ResponseBean<>();
		
		List<AppointmentBean> appointmentBean = appointmentDao.rescheduleAppointment(userId);
		
		response.setData(appointmentBean);
		response.setMsg("Reschedule Appointment Display..!!!!");
		response.setStatus(201);
		
		return response;
	}

	@GetMapping("/doneAppointmentByUserId/{userId}")
	public ResponseBean<List<AppointmentBean>> doneAppointmentByuserId(@PathVariable("userId") int userId) {
		ResponseBean<List<AppointmentBean>> response = new ResponseBean<>();
		
		List<AppointmentBean> appointmentBean = appointmentDao.doneAppointment(userId);
		
		response.setData(appointmentBean);
		response.setMsg("Done Appointment Display..!!!!");
		response.setStatus(201);
	
		return response;
	}
}
