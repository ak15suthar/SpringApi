package com.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
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
import org.springframework.web.multipart.MultipartFile;

import com.bean.DoctorProfileBean;
import com.bean.DoctorProfileImageBean;
import com.bean.ResponseBean;
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
	     
	@PostMapping("/updateDoctor")
	public ResponseBean<DoctorProfileBean> updateDoctor(@RequestBody DoctorProfileImageBean db,@RequestParam("profile") MultipartFile file){
	//		System.out.println(db.getImg64().getSize());
//		
//		String base64String = "";
//		byte[] btDataFile = null;
		
				
		try {     
			byte b[] = file.getBytes();
			System.out.println(file.getSize());
			System.out.println(file.getOriginalFilename());
			File f = new File("D:\\Spring Boot\\HealthAssist\\src\\main\\resources\\static\\images\\"
					+ file.getOriginalFilename());

			FileOutputStream fos = new FileOutputStream(f);   
			fos.write(b);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		System.out.println(db.getImg64().getOriginalFilename());

		DoctorProfileBean doctorProfileBean = db.getDoctorProfileBean();
//		
//		btDataFile = Base64.getDecoder().decode(base64String);
//		
//		try {
//			FileUtils.writeByteArrayToFile(new File("profile.jpg"), btDataFile);
//		} catch (IOException e) {
//			
//			e.printStackTrace();
//		}
//		
//		System.out.println("done..................");
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

		System.out.println("fn : "+doctorProfileBean.getFirstName());
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
	