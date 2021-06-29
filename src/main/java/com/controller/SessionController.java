package com.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bean.DoctorProfileBean;
import com.bean.LoginBean;
import com.bean.PatientProfileBean;
import com.bean.ResponseBean;
import com.bean.UserBean;
import com.dao.OtpDao;
import com.dao.SessionDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
		} 
		else if(signupBean.getStatus() == 2) {
			responseBean.setMsg(signupBean.getStatusReason());
			responseBean.setStatus(203);
		}
		else {
			responseBean.setData(signupBean);
			responseBean.setMsg("Login Successfully!!");
			responseBean.setStatus(200);
		}
		return responseBean;
	}

	
	@PostMapping("/signup")
	public ResponseBean<UserBean> signup(@RequestBody PatientProfileBean patientProfileBean) {

		ResponseBean<UserBean> responseBean = new ResponseBean<>();

		if (sessionDao.getUserByEmail(patientProfileBean.getEmail())!=null) {
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

	@PostMapping(value = "/doctorSignup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseBean<UserBean> doctorSignup(@RequestParam("user") String user,
			@RequestParam("profile") MultipartFile file) {

		System.out.println("us"+user);
		DoctorProfileBean doctorProfileBean = null;
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println("user ==> " + user);
			doctorProfileBean = mapper.readValue(user, DoctorProfileBean.class);
			System.out.println("no model exception ");
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			File t = new File("demo.txt");
			t.createNewFile();

			System.out.println("demo ==> " + t.getAbsolutePath());
			
			byte b[] = file.getBytes();
			System.out.println(file.getSize());
			System.out.println(file.getOriginalFilename());
			File dir = new File("src\\main\\resources\\static\\images\\" + doctorProfileBean.getUserId() + "\\");
			dir.mkdirs();
			File f = new File(dir, file.getOriginalFilename());

			FileOutputStream fos = new FileOutputStream(f);
			fos.write(b);
			fos.close();
			System.out.println("exists " + f.exists());
			System.out.println("profile path live => "+f.getAbsolutePath());
			System.out.println("get can path = > "+f.getCanonicalPath());
			doctorProfileBean.setProfilePic("\\images\\" + doctorProfileBean.getUserId() + "\\" + file.getOriginalFilename());
			System.out.println("profile path = > "+doctorProfileBean.getProfilePic());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Role " + doctorProfileBean.getRoleId());

		System.out.println("Name" + doctorProfileBean.getFirstName());

		doctorProfileBean.setStatus(UserBean.KYC_DOCTOR);
		doctorProfileBean.setStatusReason("Your KYC is pending Our Team Will Contact You Soon..");
		mailerService.sendDoctorRegisterMail(doctorProfileBean,doctorProfileBean);

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

		//userBean.setStatus(UserBean.PENDING);
		userBean.setStatusReason("Your KYC is pending Our Team Will Contact You Soon..");
		
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
		System.out.println("status"+signupBean.getStatus());
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

	@GetMapping("/verifyUser/{otp}/{email}")
	public ResponseBean<UserBean> verifyUser(@PathVariable("otp") String otp,
			@PathVariable("email") String email){
		
		UserBean dbUser = sessionDao.getUserByEmail(email);
		ResponseBean<UserBean> responseBean = new ResponseBean<>();

		if (dbUser == null) {
			responseBean.setMsg("User Not Found!!");
			responseBean.setStatus(201);
		} else {
			//dbUser.setPassword(password);
			if (dbUser.getOtp().equals(otp)) {
				otpDao.updateOtp(email, "");
				sessionDao.updateUserStatus(dbUser);
				mailerService.sendWelcomeMail(dbUser);
				responseBean.setMsg("Status Updated!!");
				responseBean.setStatus(200);
			} else {
				responseBean.setMsg("Invalid OTP!!");
				responseBean.setStatus(202);
			}
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
