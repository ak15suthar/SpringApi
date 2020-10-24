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
	OtpService OtpService;
	@Autowired
	MailerService mailerService;

	@PostMapping("/login")
	public ResponseBean<UserBean> login(@RequestBody LoginBean loginBean) {

		ResponseBean<UserBean> responseBean = new ResponseBean<>();

		UserBean signupBean = sessionDao.login(loginBean.getEmail(), loginBean.getPassword());

		responseBean.setData(signupBean);
		responseBean.setMsg("Login Successfully!!");
		responseBean.setStatus(200);

		return responseBean;
	}

	@PostMapping("/signup")
	public ResponseBean<UserBean> signup(@RequestBody UserBean userBean) {

		userBean.setOtp(OtpService.generateOtp());
		mailerService.sendOtpForUserVerification(userBean);
		sessionDao.insertUser(userBean);

		ResponseBean<UserBean> responseBean = new ResponseBean<>();

		responseBean.setData(userBean);
		responseBean.setMsg("user successfully signup!!");
		responseBean.setStatus(200);

		return responseBean;
	}

	@PostMapping("/doctorsignup")
	public ResponseBean<UserBean> doctorSignup(@RequestBody DoctorProfileBean doctorProfileBean) {

		doctorProfileBean.setStatus(UserBean.KYC_DOCTOR);
		doctorProfileBean.setStatusReason("Your KYS is pending Our Team Will Contact You Soon..");	
		mailerService.sendDoctorRegisterMail(doctorProfileBean);
		
		
		ResponseBean<UserBean> responseBean = new ResponseBean<>();

		responseBean.setData(doctorProfileBean);
		responseBean.setMsg("user successfully signup!!");
		responseBean.setStatus(200);

		return responseBean;
	}

	@GetMapping("/listSignup")
	public ResponseBean<List<UserBean>> listSignup() {

		List<UserBean> signupBean = sessionDao.listSignup();

		ResponseBean<List<UserBean>> responseBean = new ResponseBean<>();

		responseBean.setData(signupBean);
		responseBean.setMsg("Signup List!!");
		responseBean.setStatus(200);

		return responseBean;
	}

	@PutMapping("updateSignup")
	public ResponseBean<UserBean> updateSignup(UserBean signupBean) {

		sessionDao.updateSignup(signupBean);

		ResponseBean<UserBean> responseBean = new ResponseBean<>();
		responseBean.setData(signupBean);
		responseBean.setMsg("Signup Updated!!");
		responseBean.setStatus(201);

		return responseBean;
	}

	@DeleteMapping("deleteSignup/{userId}")
	public ResponseBean<UserBean> deleteSignup(@PathVariable("userId") int userId) {

		sessionDao.deleteSignup(userId);

		ResponseBean<UserBean> responseBean = new ResponseBean<>();

		responseBean.setMsg("Signup Deleted!!");
		responseBean.setStatus(201);

		return responseBean;

	}
}
