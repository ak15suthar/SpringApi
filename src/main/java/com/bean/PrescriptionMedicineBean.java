package com.bean;

public class PrescriptionMedicineBean {
	int prescriptionMedicineId;
	int prescriptionId;
	int medicineId;
	String frequency;
	String duration;
	String instructions;

	public int getPrescriptionMedicineId() {
		return prescriptionMedicineId;
	}

	public void setPrescriptionMedicineId(int prescriptionMedicineId) {
		this.prescriptionMedicineId = prescriptionMedicineId;
	}

	public int getPrescriptionId() {
		return prescriptionId;
	}

	public void setPrescriptionId(int prescriptionId) {
		this.prescriptionId = prescriptionId;
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
}
