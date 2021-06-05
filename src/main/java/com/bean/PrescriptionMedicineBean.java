package com.bean;

import java.util.ArrayList;

public class PrescriptionMedicineBean extends PrescriptionBean {
	int prescriptionMedicineId;
	int prescriptionId;
	ArrayList<MedBean> med;
	
	public ArrayList<MedBean> getMed() {
		return med;
	}

	public void setMed(ArrayList<MedBean> med) {
		this.med = med;
	}

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

  }
