package com.bean;

import org.springframework.web.multipart.MultipartFile;

public class DoctorProfileBean extends UserBean {


	int doctorProfileId;
	String qualification;
	String specialization;
	int experience;
	MultipartFile profile_pic;
	String about;
	String registrationNo;
	String profilePic;
	
	
	
	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}
	
	public int getDoctorProfileId() {
		return doctorProfileId;
	}
	public void setDoctorProfileId(int doctorProfileId) {
		this.doctorProfileId = doctorProfileId;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public MultipartFile getProfile_pic() {
		return profile_pic;
	}
	public void setProfile_pic(MultipartFile profile_pic) {
		this.profile_pic = profile_pic;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getRegistrationNo() {
		return registrationNo;
	}
	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}
}
