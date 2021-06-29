package com.bean;

import org.springframework.web.multipart.MultipartFile;

public class PatientProfileBean extends UserBean {

	int patientProfileId;
	String patientName;
	String gender;
	String phoneNo;
	String email;
	int age;
	String profilePic;
	int cityId;
	int pincode;
	int userId;
	MultipartFile profile_pic;

	public MultipartFile getProfile_pic() {
		return profile_pic;
	}

	public void setProfile_pic(MultipartFile profile_pic) {
		this.profile_pic = profile_pic;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPatientProfileId() {
		return patientProfileId;
	}

	public void setPatientProfileId(int patientProfileId) {
		this.patientProfileId = patientProfileId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getGender() {
		return gender;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
}
