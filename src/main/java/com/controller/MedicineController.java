package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.ls.LSInput;

import com.bean.MedicineBean;
import com.bean.ResponseBean;
import com.dao.MedicineDao;

@CrossOrigin
@RestController
public class MedicineController {

	@Autowired
	MedicineDao medicineDao;
	
	@PostMapping("/addMedicine")
	public ResponseBean<MedicineBean> addMedicine(@RequestBody MedicineBean medicineBean){
		
		medicineDao.addMedicine(medicineBean);
		
		ResponseBean<MedicineBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(medicineBean);
		responseBean.setMsg("Medicine Added!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@GetMapping("/listMedicine")
	public ResponseBean<List<MedicineBean>> listMedicine(){
		List<MedicineBean> medicineBean = medicineDao.listMedicine();
		
		ResponseBean<List<MedicineBean>> responseBean = new ResponseBean<>();
	
		responseBean.setData(medicineBean);	
		responseBean.setMsg("Medicine List!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@GetMapping("getMedicineById/{medicineId}")
	public ResponseBean<MedicineBean> getMedicineById(@RequestBody @PathVariable("medicineId") int medicineId,MedicineBean medicineBean){
		medicineBean = medicineDao.getMedicineById(medicineId);
		
		ResponseBean<MedicineBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(medicineBean);
		responseBean.setMsg("Medicine By Id!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@PutMapping("/updateMedicine")
	public ResponseBean<MedicineBean> updateMedicine(@RequestBody MedicineBean medicineBean){
		medicineDao.updateMedicine(medicineBean);
		
		ResponseBean<MedicineBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(medicineBean);
		responseBean.setMsg("Medicine Updated!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@DeleteMapping("/deleteMedicine/{medicineId}")
	public ResponseBean<MedicineBean> deleteMedicine(@PathVariable("medicineId") int medicineId){
		medicineDao.deleteMedicine(medicineId);
		
		ResponseBean<MedicineBean> responseBean = new ResponseBean<>();
		
		responseBean.setMsg("Medicine Deleted!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
}
