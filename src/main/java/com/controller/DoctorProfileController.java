package com.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.DoctorProfileBean;
import com.bean.ResponseBean;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dao.DoctorProfileDao;

@CrossOrigin
@RestController
public class DoctorProfileController {

	@Autowired
	DoctorProfileDao doctorProfileDao;
	
	@GetMapping("/listDoctor")
	public ResponseBean<List<DoctorProfileBean>> listDoctor(){
		List<DoctorProfileBean> doctorProfileBean = doctorProfileDao.listDoctor();
		
		ResponseBean<List<DoctorProfileBean>> responseBean = new ResponseBean<>();
		
		responseBean.setData(doctorProfileBean);
		responseBean.setMsg("Doctor List!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@GetMapping("/getDoctorById/{userId}")
	public ResponseBean<DoctorProfileBean> getDoctorById(@RequestBody @PathVariable("userId") int userId){
		
		DoctorProfileBean doctorProfileBean = doctorProfileDao.getDoctorById(userId);
		
		ResponseBean<DoctorProfileBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(doctorProfileBean);
		responseBean.setMsg("Doctor By Id!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@PutMapping("/updateDoctor")
	public ResponseBean<DoctorProfileBean> updateDoctor(@RequestBody DoctorProfileBean doctorProfileBean){
		
//		String base64String = doctorProfileBean.getProfile_pic();
//		byte[] btDataFile = null;
//		try {
//			btDataFile = new sun.misc.BASE64Decoder().decodeBuffer(base64String);
//		} catch (IOException e) {
//		
//			e.printStackTrace();
//		}
//		File of = new File(doctorProfileBean.getProfile_pic());
//		FileOutputStream osf;
//		try {
//			osf = new FileOutputStream(of);
//			osf.write(btDataFile);
//			osf.flush();
//		} catch (FileNotFoundException e) {
//		
//			e.printStackTrace();
//		} catch (IOException e) {
//		
//			e.printStackTrace();
//		}
//		
//		// File file = new File("C:\\Users\\royal\\OneDrive\\Pictures\\1.jpg");
//
//		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", "dioefeznp", "api_key",
//				"714131446928359", "api_secret", "g4zLAJ7rtNj5LQq5QfmlGvXpfao"));
//
//		Map uploadResult;
//		try {
//			uploadResult = cloudinary.uploader().upload(of, ObjectUtils.emptyMap());
//			System.out.println(uploadResult);
//			System.out.println(uploadResult.get("url"));
//		} catch (IOException e) {
//
//			e.printStackTrace();
//		}

		
		System.out.println("pic : "+doctorProfileBean.getProfile_pic());
		doctorProfileDao.updateDoctor(doctorProfileBean);
		
		ResponseBean<DoctorProfileBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(doctorProfileBean);
		responseBean.setMsg("Doctor Updated!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@DeleteMapping("/deleteDoctor/{userId}")
	public ResponseBean<DoctorProfileBean> deleteDoctor(@PathVariable("userId") int userId){
		doctorProfileDao.deleteDoctor(userId);
		
		ResponseBean<DoctorProfileBean> responseBean = new ResponseBean<>();
		
		responseBean.setMsg("Doctor Deleted!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
}
	