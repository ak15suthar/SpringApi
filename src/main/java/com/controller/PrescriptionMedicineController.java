package com.controller;

import org.springframework.web.bind.annotation.RestController;

import com.bean.PrescriptionMedicineBean;
import com.bean.ResponseBean;
import com.dao.PrescriptionMedicineDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@RestController
public class PrescriptionMedicineController {

	@Autowired
	PrescriptionMedicineDao prescriptionMedicineDao;
	
	@PostMapping("/addPrescriptionMedicine")
	public ResponseBean<PrescriptionMedicineBean> addPrescriptionMedicine(@RequestBody PrescriptionMedicineBean prescriptionMedicineBean){
		
		prescriptionMedicineDao.addPrescriptionMedicine(prescriptionMedicineBean);
		
		ResponseBean<PrescriptionMedicineBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(prescriptionMedicineBean);
		responseBean.setMsg("PrescriptionMedicine Added!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@GetMapping("/listPrescriptionMedicine")
	public ResponseBean<List<PrescriptionMedicineBean>> listPrescriptionMedicine(){
		
		List<PrescriptionMedicineBean> prescriptionMedicineBean = prescriptionMedicineDao.listPrescriptionMedicine();
		
		ResponseBean<List<PrescriptionMedicineBean>> responseBean = new ResponseBean<>();
		
		responseBean.setData(prescriptionMedicineBean);	
		responseBean.setMsg("Prescription Medicine List!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@GetMapping("/getPrescriptionMedicineById/{prescriptionMedicineId}")
	public ResponseBean<PrescriptionMedicineBean> getPrescriptionMedicineById(@PathVariable("prescriptionMedicineId") int prescriptionMedicineId){
		PrescriptionMedicineBean prescriptionMedicineBean = prescriptionMedicineDao.getPrescriptionMedicineById(prescriptionMedicineId);
		
		ResponseBean<PrescriptionMedicineBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(prescriptionMedicineBean);
		responseBean.setMsg("Prescription Medicine By Id!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@PutMapping("/updatePrescriptionMedicine")
	public ResponseBean<PrescriptionMedicineBean> updatePrescriptionMedicine(@RequestBody PrescriptionMedicineBean prescriptionMedicineBean){
		
		prescriptionMedicineDao.updatePrescriptionMedicine(prescriptionMedicineBean);
		
		ResponseBean<PrescriptionMedicineBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(prescriptionMedicineBean);
		responseBean.setMsg("Prescription Medicine Updated!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@DeleteMapping("deletePrescriptionMedicine/{prescriptionMedicineId}")
	public ResponseBean<PrescriptionMedicineBean> deletePrescriptionMedicine(@PathVariable("prescriptionMedicineId") int prescriptionMedicineId){
		prescriptionMedicineDao.deletePrescriptionMedicine(prescriptionMedicineId);
		
		ResponseBean<PrescriptionMedicineBean> responseBean = new ResponseBean<>();
		
		responseBean.setMsg("Prescription Medicine Deleted!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
}
