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
import com.bean.ResponseBean;
import com.dao.DoctorProfileDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin
@RestController
public class DoctorProfileController {

	@Autowired
	DoctorProfileDao doctorProfileDao;

	@GetMapping("/listDoctor")
	public ResponseBean<List<DoctorProfileBean>> listDoctor() {
		List<DoctorProfileBean> doctorProfileBean = doctorProfileDao.listDoctor();

		ResponseBean<List<DoctorProfileBean>> responseBean = new ResponseBean<>();

		responseBean.setData(doctorProfileBean);
		responseBean.setMsg("Doctor List!!");
		responseBean.setStatus(200);

		return responseBean;
	}

	@GetMapping("/getDoctorById/{userId}")
	public ResponseBean<DoctorProfileBean> getDoctorById(@RequestBody @PathVariable("userId") int userId) {

		DoctorProfileBean doctorProfileBean = doctorProfileDao.getDoctorById(userId);

		ResponseBean<DoctorProfileBean> responseBean = new ResponseBean<>();

		responseBean.setData(doctorProfileBean);
		responseBean.setMsg("Doctor By Id!!");
		responseBean.setStatus(200);

		return responseBean;
	}

	@PostMapping(value = "/updateDoctor", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseBean<DoctorProfileBean> updateDoctor(@RequestParam("user") String user,
			@RequestParam("profile") MultipartFile file) {

		DoctorProfileBean db = null;

		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println("user ==> " + user);
			db = mapper.readValue(user, DoctorProfileBean.class);
			System.out.println("no model exception ");
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			byte b[] = file.getBytes();
			System.out.println(file.getSize());
			System.out.println(file.getOriginalFilename());
			File dir = new File(
					"D:\\Spring Boot\\HealthAssist\\src\\main\\resources\\static\\images\\" + db.getUserId() + "\\");
			dir.mkdirs();
			File f = new File(dir, file.getOriginalFilename());
			
			FileOutputStream fos = new FileOutputStream(f);
			fos.write(b);
			fos.close();
			db.setProfilePic("\\images\\"+db.getUserId()+"\\"+file.getOriginalFilename());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// DoctorProfileBean doctorProfileBean = db.getDoctorProfileBean();

		System.out.println("fn : " + db.getFirstName());
		System.out.println("pic : " + db.getProfilePic());
		doctorProfileDao.updateDoctor(db);

		ResponseBean<DoctorProfileBean> responseBean = new ResponseBean<>();

		responseBean.setData(db);
		responseBean.setMsg("Doctor Updated!!");
		responseBean.setStatus(200);

		return responseBean;
	}

	@DeleteMapping("/deleteDoctor/{userId}")
	public ResponseBean<DoctorProfileBean> deleteDoctor(@PathVariable("userId") int userId) {
		doctorProfileDao.deleteDoctor(userId);

		ResponseBean<DoctorProfileBean> responseBean = new ResponseBean<>();

		responseBean.setMsg("Doctor Deleted!!");
		responseBean.setStatus(200);

		return responseBean;
	}

}
