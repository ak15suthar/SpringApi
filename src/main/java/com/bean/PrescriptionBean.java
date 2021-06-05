package com.bean;

import java.sql.Date;
import java.util.ArrayList;

public class PrescriptionBean {

	int prescriptionId;
	int patientProfileId;
	int doctorProfileId;
	int appointmentId;
	String description;
	Date prescriptionDate;
	String generalAdvice;
	String followupComment;
	ArrayList<MedBean> med;
	
	public ArrayList<MedBean> getMed() {
		return med;
	}

	public void setMed(ArrayList<MedBean> med) {
		this.med = med;
	}

	public int getPrescriptionId() {
		return prescriptionId;
	}

	public void setPrescriptionId(int prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	public int getPatientProfileId() {
		return patientProfileId;
	}

	public void setPatientProfileId(int patientProfileId) {
		this.patientProfileId = patientProfileId;
	}

	public int getDoctorProfileId() {
		return doctorProfileId;
	}

	public void setDoctorProfileId(int doctorProfileId) {
		this.doctorProfileId = doctorProfileId;
	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPrescriptionDate() {
		return prescriptionDate;
	}

	public void setPrescriptionDate(Date prescriptionDate) {
		this.prescriptionDate = prescriptionDate;
	}

	public String getGeneralAdvice() {
		return generalAdvice;
	}

	public void setGeneralAdvice(String generalAdvice) {
		this.generalAdvice = generalAdvice;
	}

	public String getFollowupComment() {
		return followupComment;
	}

	public void setFollowupComment(String followupComment) {
		this.followupComment = followupComment;
	}

}
