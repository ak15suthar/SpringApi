package com.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;

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

import com.bean.PatientProfileBean;
import com.bean.ResponseBean;
import com.dao.PatientProfileDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin
@RestController
public class PatientProfileController {

	@Autowired
	PatientProfileDao patientProfileDao;

	@PostMapping("/addFamilyMember")
	public ResponseBean<PatientProfileBean> addFamilyMember(@RequestBody PatientProfileBean patientProfileBean) {

		patientProfileDao.addFamilyMember(patientProfileBean);

		ResponseBean<PatientProfileBean> responseBean = new ResponseBean<>();

		responseBean.setData(patientProfileBean);
		responseBean.setMsg("PatientProfile Added!!");
		responseBean.setStatus(200);

		return responseBean;
	}

	@GetMapping("/listPatientProfile")
	public ResponseBean<List<PatientProfileBean>> listPatientProfile() {

		List<PatientProfileBean> patientProfileBean = patientProfileDao.listPatientProfile();

		ResponseBean<List<PatientProfileBean>> responseBean = new ResponseBean<>();

		responseBean.setData(patientProfileBean);
		responseBean.setMsg("PatientProfile List!!");
		responseBean.setStatus(200);

		return responseBean;
	}

	@GetMapping("/listUserPatient/{userId}")
	public ResponseBean<List<PatientProfileBean>> listUserPatient(@PathVariable("userId") int userId) {
		ResponseBean<List<PatientProfileBean>> responseBean = new ResponseBean<>();
		System.out.println("===>" + userId);

		List<PatientProfileBean> userPatientBean = patientProfileDao.listUserPatient(userId);

		responseBean.setData(userPatientBean);
		responseBean.setMsg("User Patient Display..!!!!");
		responseBean.setStatus(201);

		return responseBean;
	}

	@GetMapping("/getEditUserPatient/{patientid}")
	public ResponseBean<PatientProfileBean> getEditUserPatient(@PathVariable("patientid") int patientid, PatientProfileBean bean) {
		ResponseBean<PatientProfileBean> responseBean = new ResponseBean<>();
		bean = patientProfileDao.getEditUserPatient(patientid);
		responseBean.setData(bean);
		responseBean.setMsg("Single User Return");
		responseBean.setStatus(200);
		return responseBean;
	}
    
	
	@GetMapping("/getPatientProfile/{userId}")
	public ResponseBean<PatientProfileBean> getProfileProfile(@PathVariable("userId") int userId,
			PatientProfileBean patientProfileBean) {

		patientProfileBean = patientProfileDao.getPatientProfileById(userId);
		
		ResponseBean<PatientProfileBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(patientProfileBean);
		responseBean.setMsg("Single User Return");
		responseBean.setStatus(200);

		return responseBean;
	}

	
	
	@PutMapping(value = "/updateUserProfile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseBean<PatientProfileBean> updateUserProfile(@RequestParam("user") String user,
			@RequestParam("profile") Optional<MultipartFile> filex) {
		System.out.println("init...."+filex);
		PatientProfileBean patientBean = null;
		MultipartFile file = null;

		if (filex != null && filex.isPresent()) {
			file = filex.get();
		}
		
//		System.out.println("image : "+file);
//		System.out.println("img : "+file.getOriginalFilename());
		
		ObjectMapper mapper = new ObjectMapper();
		
		
		try {
			System.out.println("user ==> " + user);
			patientBean = mapper.readValue(user, PatientProfileBean.class);
			System.out.println("no model exception ");
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (file != null) {
			try {
				File t = new File("demo.txt");
				t.createNewFile();
	
				System.out.println("demo ==> " + t.getAbsolutePath());
				
				byte b[] = file.getBytes();
				System.out.println(file.getSize());
				System.out.println(file.getOriginalFilename());
				File dir = new File("src\\main\\resources\\static\\images\\" + patientBean.getUserId() + "\\");
				dir.mkdirs();
				File f = new File(dir, file.getOriginalFilename());
	
				FileOutputStream fos = new FileOutputStream(f);
				fos.write(b);
				fos.close();
				System.out.println("exists " + f.exists());
				System.out.println("profile path live => "+f.getAbsolutePath());
				System.out.println("get can path = > "+f.getCanonicalPath());
				patientBean.setProfilePic("\\images\\" + patientBean.getUserId() + "\\" + file.getOriginalFilename());
				System.out.println("profile path = > "+patientBean.getProfilePic());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
		//	
		}
		
		System.out.println("User Edit Profile => "+patientBean.getLastName());
		
		patientProfileDao.updateUserProfile(patientBean);
		
		ResponseBean<PatientProfileBean> response = new ResponseBean<>();
		
		response.setData(patientBean);
		response.setMsg("Patient Updated Successfully..!!");
	
		return response;
	}
	
	@GetMapping("/getFamilyMember/{patientProfileId}")
	public ResponseBean<PatientProfileBean> getFamilyMember(@PathVariable("patientProfileId") int patientProfileId, PatientProfileBean bean) {

		ResponseBean<PatientProfileBean> responseBean = new ResponseBean<>();
		bean = patientProfileDao.getFamilyMember(patientProfileId);
		responseBean.setData(bean);
		responseBean.setMsg("Single User Return");
		responseBean.setStatus(200);

		return responseBean;
	}
    
    @PutMapping("/updateFamilyMember")
    public ResponseBean<PatientProfileBean> updateFamilyMember(@RequestBody PatientProfileBean patientBean) {
    	patientProfileDao.updateFamilyMember(patientBean);
        ResponseBean<PatientProfileBean> response = new ResponseBean<>();
        response.setData(patientBean);
        response.setMsg("Patient Updated Successfully..!!");
        return response;
    }

	
	@DeleteMapping("/deletePatientProfile/{patientProfileId}")
	public ResponseBean<PatientProfileBean> deletePatientProfile(
			@PathVariable("patientProfileId") int patientProfileId) {

		patientProfileDao.deletePatientProfile(patientProfileId);

		ResponseBean<PatientProfileBean> responseBean = new ResponseBean<>();

		responseBean.setMsg("PatientProfile Deleted!!");
		responseBean.setStatus(202);

		return responseBean;
	}
}
