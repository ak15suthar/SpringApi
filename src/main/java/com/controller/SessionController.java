package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.DoctorProfileBean;
import com.bean.LoginBean;
import com.bean.ResponseBean;
import com.bean.UserBean;
import com.dao.SessionDao;
import com.service.MailerService;
import com.service.OtpService;

@RestController
public class SessionController {
	
	@Autowired
	SessionDao sessionDao;
	
	@Autowired
	OtpService otpService;
	
	@Autowired
	MailerService mailService;
	
	@PostMapping("/login")
	public ResponseBean<UserBean> login(@RequestBody LoginBean loginBean){
		
		ResponseBean<UserBean> responseBean = new ResponseBean<>();
			
		UserBean userBean = sessionDao.login(loginBean.getEmail(),loginBean.getPassword());
		
		responseBean.setData(userBean);
		responseBean.setMsg("Login Successfully!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@PostMapping("/signup")
	public ResponseBean<UserBean> signup(@RequestBody UserBean userBean){
		
		userBean.setOtp(otpService.generateOtp());
		mailService.sendOtpForUserVerification(userBean);
		sessionDao.insertUser(userBean);
		
		ResponseBean<UserBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(userBean);
		responseBean.setMsg("Signup Inserted!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@PostMapping("/doctorSignup")
	public ResponseBean<UserBean> doctorSignup(@RequestBody DoctorProfileBean doctorProfileBean){
		
		doctorProfileBean.setStatus(UserBean.KYC_DOCTOR);
		doctorProfileBean.setStatusReason("Your KYC is pending Our Team Will Contact You Soon...");
		mailService.sendDoctorRegisterMail(doctorProfileBean);
		
		ResponseBean<UserBean> responseBean = new ResponseBean<>();
		
		sessionDao.addDoctorProfile(doctorProfileBean);
		
		responseBean.setData(doctorProfileBean);
		responseBean.setMsg("Doctor Inserted Successfully!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@GetMapping("/listSignup")
	public ResponseBean<List<UserBean>> listSignup(){
		
		List<UserBean> userBean = sessionDao.listSignup();
		
		ResponseBean<List<UserBean>> responseBean = new ResponseBean<>();
		
		responseBean.setData(userBean);
		responseBean.setMsg("Signup List!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@PutMapping("/updateSignup")
	public ResponseBean<UserBean> updateSignup(UserBean userBean){
		
		sessionDao.updateSignup(userBean);
		
		ResponseBean<UserBean> responseBean = new ResponseBean<>();
		responseBean.setData(userBean);
		responseBean.setMsg("Signup Updated!!");
		responseBean.setStatus(201);
		
		return responseBean;
	}
	
	@DeleteMapping("/deleteSignup/{userId}")
	public ResponseBean<UserBean> deleteSignup(@PathVariable("userId") int userId){
		
		sessionDao.deleteSignup(userId);
		
		ResponseBean<UserBean> responseBean = new ResponseBean<>();
		
		responseBean.setMsg("Signup Deleted!!");
		responseBean.setStatus(201);
		
		return responseBean;
		
	}
}
