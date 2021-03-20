package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.DietBean;
import com.bean.ResponseBean;
import com.dao.DietDao;

@CrossOrigin
@RestController
public class DietController {

	@Autowired
	DietDao dietDao;

	@PostMapping("/addDiet")
	public ResponseBean<DietBean> addDiet(@RequestBody DietBean dietBean) {
		dietDao.addDiet(dietBean);

		ResponseBean<DietBean> responseBean = new ResponseBean<>();

		responseBean.setData(dietBean);
		responseBean.setMsg("Diet Added!!");
		responseBean.setStatus(200);

		return responseBean;
	}

	@GetMapping("/listDiet")
	public ResponseBean<List<DietBean>> listDiet() {
		List<DietBean> dietBean = dietDao.listDiet();

		ResponseBean<List<DietBean>> responseBean = new ResponseBean<>();

		responseBean.setData(dietBean);
		responseBean.setMsg("Diet List!!");
		responseBean.setStatus(200);

		return responseBean;
	}

	@GetMapping("/getDietById/{dietId}")
	public ResponseBean<DietBean> getDietById(@RequestBody @PathVariable("dietId") int dietId, DietBean dietBean) {
		dietBean = dietDao.getDietById(dietId);
		
		ResponseBean<DietBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(dietBean);
		responseBean.setMsg("Diet By Id!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}

	@PutMapping("/updateDiet")
	public ResponseBean<DietBean> updateDiet(@RequestBody DietBean dietBean) {
		dietDao.update(dietBean);

		ResponseBean<DietBean> responseBean = new ResponseBean<>();

		responseBean.setData(dietBean);
		responseBean.setMsg("Diet Updated!!");
		responseBean.setStatus(200);

		return responseBean;
	}

	@DeleteMapping("/deleteDiet/{dietId}")
	public ResponseBean<DietBean> deleteDiet(@PathVariable("dietId") int dietId) {
		dietDao.deleteDiet(dietId);

		ResponseBean<DietBean> responseBean = new ResponseBean<>();

		responseBean.setMsg("Diet Deleted!!");
		responseBean.setStatus(200);

		return responseBean;
	}
}
