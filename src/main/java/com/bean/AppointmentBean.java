package com.bean;

import java.util.Date;

public class AppointmentBean extends PatientProfileBean {

	int appointmentId;
	int doctorProfileId;
	int patientProfileId;
	int clinicId;
	int appointmentStatusId;
	Date appointmentCreateDate;
	Date appointmentDate;
	String appointmentTime;
	String comment;
	String reference;
	String complain;
	String clinicName;
	String statusName;
	String description;
	Date prescriptionDate;
	String generalAdvice;
	String followupComment;
	int medicineId;
	String frequency;
	String duration;
	String instructions;
	String statusReason;
	String medicineName;
	String email; 
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public String getStatusReason() {
		return statusReason;
	}

	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
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

	public String getClinicName() {
		return clinicName;
	}

	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public int getDoctorProfileId() {
		return doctorProfileId;
	}

	public void setDoctorProfileId(int doctorProfileId) {
		this.doctorProfileId = doctorProfileId;
	}

	public int getPatientProfileId() {
		return patientProfileId;
	}

	public void setPatientProfileId(int patientProfileId) {
		this.patientProfileId = patientProfileId;
	}

	public int getClinicId() {
		return clinicId;
	}

	public void setClinicId(int clinicId) {
		this.clinicId = clinicId;
	}

	public int getAppointmentStatusId() {
		return appointmentStatusId;
	}

	public void setAppointmentStatusId(int appointmentStatusId) {
		this.appointmentStatusId = appointmentStatusId;
	}

	public Date getAppointmentCreateDate() {
		return appointmentCreateDate;
	}

	public void setAppointmentCreateDate(Date appointmentCreateDate) {
		this.appointmentCreateDate = appointmentCreateDate;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getComplain() {
		return complain;
	}

	public void setComplain(String complain) {
		this.complain = complain;
	}

}