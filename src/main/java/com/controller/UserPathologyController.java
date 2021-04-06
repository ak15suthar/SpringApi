package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ResponseBean;
import com.bean.UserPathologyBean;
import com.dao.UserPathologyDao;

@CrossOrigin
@RestController
public class UserPathologyController {

	@Autowired
	UserPathologyDao userPathologyDao;

	@PostMapping("/addUserPathology")
	public ResponseBean<UserPathologyBean> addUserPathology(@RequestBody UserPathologyBean userPathologyBean) {
		System.out.println(userPathologyBean.getPathologyId());

		userPathologyDao.addUserPathology(userPathologyBean);

		ResponseBean<UserPathologyBean> response = new ResponseBean<>();

		response.setData(userPathologyBean);
		response.setMsg("User Pathology Added Successfully...!!");
		response.setStatus(200);

		return response;
	}

	@GetMapping("/listUserPathology/{userId}")
	public ResponseBean<List<UserPathologyBean>> listUserPathology(@PathVariable("userId") int userId) {

		List<UserPathologyBean> userPathologyBean = userPathologyDao.listUserPathology(userId);

		ResponseBean<List<UserPathologyBean>> responseBean = new ResponseBean<>();

		responseBean.setData(userPathologyBean);
		responseBean.setMsg("List Display..!!!!");
		responseBean.setStatus(201);

		return responseBean;
	}

	
	
	@PutMapping("/updateUserPathology")
	public ResponseBean<UserPathologyBean> updateUserPathology(@RequestBody UserPathologyBean userPathologyBean) {
		
		userPathologyDao.updateUserPathology(userPathologyBean);
		
		ResponseBean<UserPathologyBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(userPathologyBean);
		responseBean.setMsg("User Pathology Updated Successfully..!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
}