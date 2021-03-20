package com.bean;

import java.util.Date;

public class AppointmentBean {

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