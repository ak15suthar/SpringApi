package com.bean;

import java.sql.Date;

public class PrescriptionBean {

	int prescriptionId;
	int patientProfileId;
	int doctorProfileId;
	int appointmentId;
	String description;
	Date prescriptionDate;
	String generalAdvice;
	String followupComment;
	int medicineId;
	String frequency;
	String duration;
	String instructions;

	public int getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(int medicineId) {
		this.medicineId = medicineId;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
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
