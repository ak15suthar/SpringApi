package com.bean;

public class AppointmentStatusBean {

	int appointmentStatusId;
	String appointmentStatusName;

	//1.accepted
	//2.rejected
	//3.hold
	//4.wait for accept
	//5.reschedule 
	
	public int getAppointmentStatusId() {
		return appointmentStatusId;
	}

	public void setAppointmentStatusId(int appointmentStatusId) {
		this.appointmentStatusId = appointmentStatusId;
	}

	public String getAppointmentStatusName() {
		return appointmentStatusName;
	}

	public void setAppointmentStatusName(String appointmentStatusName) {
		this.appointmentStatusName = appointmentStatusName;
	}
}
