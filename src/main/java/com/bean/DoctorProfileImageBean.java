package com.bean;

import org.springframework.web.multipart.MultipartFile;

public class DoctorProfileImageBean {

	
	DoctorProfileBean doctorProfileBean;
	MultipartFile img64;
	
	public DoctorProfileImageBean() {
		
	}
	
	public DoctorProfileBean getDoctorProfileBean() {
		return doctorProfileBean;
	}
	public void setDoctorProfileBean(DoctorProfileBean doctorProfileBean) {
		this.doctorProfileBean = doctorProfileBean;
	}
	public MultipartFile getImg64() {
		return img64;
	}
	public void setImg64(MultipartFile img64) {
		this.img64 = img64;
	}
	
}
