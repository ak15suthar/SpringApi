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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bean.DoctorProfileBean;
import com.bean.LoginBean;
import com.bean.PatientProfileBean;
import com.bean.ResponseBean;
import com.bean.UserBean;
import com.dao.OtpDao;
import com.dao.SessionDao;
import com.services.MailerService;
import com.services.OtpService;

@CrossOrigin
@RestController
public class SessionController {

	@Autowired
	SessionDao sessionDao;

	@Autowired
	OtpService OtpService;

	@Autowired
	MailerService mailerService;

	@Autowired
	OtpDao otpDao;

	@PostMapping("/login")
	public ResponseBean<UserBean> login(@RequestBody LoginBean loginBean) {

		ResponseBean<UserBean> responseBean = new ResponseBean<>();

		UserBean signupBean = sessionDao.login(loginBean.getEmail(), loginBean.getPassword());

		if (signupBean == null) {
			responseBean.setMsg("Invalid Credentials");
			responseBean.setStatus(201);
		} else {
			responseBean.setData(signupBean);
			responseBean.setMsg("Login Successfully!!");
			responseBean.setStatus(200);
		}
		return responseBean;
	}

	@PostMapping("/signup")
	public ResponseBean<UserBean> signup(@RequestBody PatientProfileBean patientProfileBean) {

		ResponseBean<UserBean> responseBean = new ResponseBean<>();

		if (sessionDao.getUserByEmail(patientProfileBean.getEmail()) != null) {
			responseBean.setMsg("Email Already Exist!!");
			responseBean.setStatus(201);
		} else {

			patientProfileBean.setOtp(OtpService.generateOtp());
			mailerService.sendOtpForUserVerification(patientProfileBean);
			// sessionDao.insertUser(patientProfileBean);

			patientProfileBean.setUserId(patientProfileBean.getUserId());
			patientProfileBean.setPatientName(patientProfileBean.getFirstName());
//			System.out.println("UserId : "+patientProfileBean.getUserId());

			sessionDao.addPatientProfile(patientProfileBean);
			responseBean.setData(patientProfileBean);
			responseBean.setMsg("User Successfully Signup!!");
			responseBean.setStatus(200);

		}

		return responseBean;
	}

	@PostMapping("/doctorSignup")
	public ResponseBean<UserBean> doctorSignup(@RequestBody DoctorProfileBean doctorProfileBean) {
		System.out.println("Role " + doctorProfileBean.getRoleId());

		System.out.println("Name" + doctorProfileBean.getFirstName());

		doctorProfileBean.setStatus(UserBean.KYC_DOCTOR);
		doctorProfileBean.setStatusReason("Your KYC is pending Our Team Will Contact You Soon..");
//		mailerService.sendDoctorRegisterMail(doctorProfileBean,userBean);

		System.out.println(doctorProfileBean.getEmail());
		System.out.println(doctorProfileBean.getFirstName());
		ResponseBean<UserBean> responseBean = new ResponseBean<>();

		if (sessionDao.getUserByEmail(doctorProfileBean.getEmail()) != null) {
			responseBean.setMsg("Email Already Exist!!");
			responseBean.setStatus(201);
		} else {
			sessionDao.addDoctorProfile(doctorProfileBean);
			responseBean.setData(doctorProfileBean);
			responseBean.setMsg("user successfully signup!!");
			responseBean.setStatus(200);
		}
		return responseBean;
	}

	@PostMapping("/adminAddPatientProfile")
	public ResponseBean<PatientProfileBean> adminAddPatientProfile(@RequestBody PatientProfileBean patientProfileBean) {

		sessionDao.adminAddPatientProfile(patientProfileBean);

		ResponseBean<PatientProfileBean> responseBean = new ResponseBean<>();

		responseBean.setData(patientProfileBean);
		responseBean.setMsg("Patient Profile Added!!");
		responseBean.setStatus(200);

		return responseBean;
	}

	@PostMapping("/adminAddUsers")
	public ResponseBean<UserBean> adminAddUsers(@RequestBody UserBean userBean) {

		ResponseBean<UserBean> responseBean = new ResponseBean<>();

		if (sessionDao.getUserByEmail(userBean.getEmail()) != null) {
			responseBean.setMsg("Email Already Exist!!");
			responseBean.setStatus(201);
		} else {
			sessionDao.adminAddUsers(userBean);
			responseBean.setData(userBean);
			responseBean.setMsg("Admin Add Users!!");
			responseBean.setStatus(200);

		}
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

	@GetMapping("/getUserById/{userId}")
	public ResponseBean<UserBean> getUser(@PathVariable("userId") int userId, UserBean userBean) {
		userBean = sessionDao.getUserById(userId);

		ResponseBean<UserBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(userBean);
		responseBean.setMsg("Single User Return");
		responseBean.setStatus(200);

		return responseBean;
	}

	@PutMapping("/updateSignup")
	public ResponseBean<UserBean> updateSignup(@RequestBody UserBean signupBean) {

		sessionDao.updateSignup(signupBean);

		ResponseBean<UserBean> responseBean = new ResponseBean<>();
		responseBean.setData(signupBean);
		responseBean.setMsg("Signup Updated!!");
		responseBean.setStatus(201);

		return responseBean;
	}

	@DeleteMapping("/deleteSignup/{userId}")
	public ResponseBean<UserBean> deleteSignup(@PathVariable("userId") int userId) {

		sessionDao.deleteSignup(userId);

		ResponseBean<UserBean> responseBean = new ResponseBean<>();

		responseBean.setMsg("Signup Deleted!!");
		responseBean.setStatus(201);

		return responseBean;

	}

	@GetMapping("/resetPassword/{email}")
	public ResponseBean<UserBean> sendOtpForRestPassword(@RequestBody @PathVariable("email") String email) {

		System.out.println("reset call...");
		UserBean userBean = sessionDao.getUserByEmail(email);
		ResponseBean<UserBean> responseBean = new ResponseBean<>();

		if (userBean == null) {
			responseBean.setMsg("Invalid Email Address!!");
			responseBean.setStatus(201);
		} else {
			String otp = OtpService.generateOtp();
			userBean.setOtp(otp);
			otpDao.updateOtp(email, otp);
			mailerService.sendOtpForForgetPassword(userBean);

			responseBean.setMsg("Please Check Email For OTP!!");
			responseBean.setStatus(200);
		}

		return responseBean;
	}

	@GetMapping("/setNewPassword/{otp}/{password}/{email}")
	public ResponseBean<UserBean> setNewPasswordUsingOtp(@PathVariable("otp") String otp,
			@PathVariable("password") String password, @PathVariable("email") String email) {

		System.out.println("setnewpassword...");
		System.out.println(otp);
		System.out.println(password);
		System.out.println(email);

		UserBean dbUser = sessionDao.getUserByEmail(email);
		ResponseBean<UserBean> responseBean = new ResponseBean<>();

		if (dbUser == null) {
			responseBean.setMsg("User Not Found!!");
			responseBean.setStatus(201);
		} else {
			dbUser.setPassword(password);
			if (dbUser.getOtp().equals(otp)) {
				otpDao.updateOtp(email, "");
				sessionDao.updatePassword(dbUser);
				mailerService.sendMailForPasswordUpdate(dbUser);
				responseBean.setMsg("Password Updated!!");
				responseBean.setStatus(200);
			} else {
				responseBean.setMsg("Invalid OTP!!");
				responseBean.setStatus(201);
			}
		}

		return responseBean;
	}
}
