package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bean.DoctorProfileBean;
import com.bean.LoginBean;
import com.bean.ResponseBean;
import com.bean.UserBean;
import com.dao.OtpDao;
import com.dao.SessionDao;
import com.dao.UserDao;
import com.services.MailerService;
import com.services.OtpService;

@RestController
public class SessionController {

	@Autowired
	SessionDao sessionDao;

	@Autowired
	OtpService OtpService;
	@Autowired
	MailerService mailerService;

	@Autowired
	UserDao userDao;

	@Autowired
	OtpDao otpDao;

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
		mailerService.sendOtpForEmailVerification(userBean);
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

	@PostMapping("resetpassword")
	public ResponseBean<UserBean> sendOtpForResetPassword(@RequestParam("email") String email) {

		UserBean userBean = userDao.getUserByEmail(email);
		ResponseBean<UserBean> responseBean = new ResponseBean<>();

		if (userBean == null) {

			responseBean.setMsg("Invalid Email Address");
			responseBean.setStatus(201);

		} else {
			String otp = OtpService.generateOtp();
			userBean.setOtp(otp);
			otpDao.updateOtp(email, otp);
			mailerService.sendOtpForForgetPassword(userBean);

			responseBean.setMsg("Please Check Email for OTP");
			responseBean.setStatus(201);

		}

		return responseBean;
	}

	@PostMapping("setnewpassword")
	public ResponseBean<UserBean> setNewPasswordUsingOtp(UserBean userBean) {

		UserBean dbUser = userDao.getUserByEmail(userBean.getEmail());
		
		ResponseBean<UserBean> responseBean = new ResponseBean<>();

		if (dbUser == null) {

			responseBean.setMsg("User not found");
			responseBean.setStatus(201);

		} else {
			
			
			if(dbUser.getOtp().equals(userBean.getOtp())) {	
				otpDao.updateOtp(userBean.getEmail(), "");
				userDao.updatePassword(userBean);
				mailerService.sendMailForPasswordUpdate(dbUser);
				responseBean.setMsg("Password Update...");
				responseBean.setStatus(201);

			}else {
				responseBean.setMsg("Invalid Otp....");
				responseBean.setStatus(201);

			}
			
			

		}

		return responseBean;
	}

}
