package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ResponseBean;
import com.bean.UserDietBean;
import com.dao.UserDietDao;

@CrossOrigin
@RestController
public class UserDietController {

	@Autowired
	UserDietDao userDietDao;

	@PostMapping("/addUserDiet")
	public ResponseBean<UserDietBean> addDietUser(@RequestBody UserDietBean dietUserBean) {

		userDietDao.addDietUser(dietUserBean);

		ResponseBean<UserDietBean> responseBean = new ResponseBean<>();

		responseBean.setData(dietUserBean);
		responseBean.setMsg("User Diet Added!!");
		responseBean.setStatus(200);

		return responseBean;
	}

	@GetMapping("/listUserDiet/{patientProfileId}")
	public ResponseBean<List<UserDietBean>> listDietUser(@PathVariable("patientId") int patientId) {
		List<UserDietBean> dietUserBean = userDietDao.listDietUser(patientId);

		ResponseBean<List<UserDietBean>> responseBean = new ResponseBean<>();

		responseBean.setData(dietUserBean);
		responseBean.setMsg("User Diet List!!");
		responseBean.setStatus(200);

		return responseBean;
	}
}
