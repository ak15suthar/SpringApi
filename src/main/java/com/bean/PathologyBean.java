package com.bean;

public class PathologyBean {

	int pathologyId;
	String pathologyName;
	String pathologyTimings;
	String address;
	String phoneNo;
	float rating;
	String about;
	float lat;
	float log;
	int cityId;
	int pincode;
	int userId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPathologyId() {
		return pathologyId;
	}

	public void setPathologyId(int pathologyId) {
		this.pathologyId = pathologyId;
	}

	public String getPathologyName() {
		return pathologyName;
	}

	public void setPathologyName(String pathologyName) {
		this.pathologyName = pathologyName;
	}

	public String getPathologyTimings() {
		return pathologyTimings;
	}

	public void setPathologyTimings(String pathologyTimings) {
		this.pathologyTimings = pathologyTimings;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public float getLog() {
		return log;
	}

	public void setLog(float log) {
		this.log = log;
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
