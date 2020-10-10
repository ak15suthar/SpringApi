package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.LoginBean;
import com.bean.ResponseBean;
import com.bean.UserBean;
import com.dao.SignupDao;

@RestController
public class SignupController {
	
	@Autowired
	SignupDao signupDao;
	
	
	@PostMapping("/login")
	public ResponseBean<UserBean> login(@RequestBody LoginBean loginBean){
		
		ResponseBean<UserBean> responseBean = new ResponseBean<>();
			
		UserBean signupBean = signupDao.login(loginBean.getEmail(),loginBean.getPassword());
		
		responseBean.setData(signupBean);
		responseBean.setMsg("Login Successfully!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@PostMapping("/addSignup")
	public ResponseBean<UserBean> addSignup(@RequestBody UserBean signupBean){
		
		signupDao.insertSignup(signupBean);
		
		ResponseBean<UserBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(signupBean);
		responseBean.setMsg("Signup Inserted!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@GetMapping("/listSignup")
	public ResponseBean<List<UserBean>> listSignup(){
		
		List<UserBean> signupBean = signupDao.listSignup();
		
		ResponseBean<List<UserBean>> responseBean = new ResponseBean<>();
		
		responseBean.setData(signupBean);
		responseBean.setMsg("Signup List!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@PutMapping("updateSignup")
	public ResponseBean<UserBean> updateSignup(UserBean signupBean){
		
		signupDao.updateSignup(signupBean);
		
		ResponseBean<UserBean> responseBean = new ResponseBean<>();
		responseBean.setData(signupBean);
		responseBean.setMsg("Signup Updated!!");
		responseBean.setStatus(201);
		
		return responseBean;
	}
	
	@DeleteMapping("deleteSignup/{userId}")
	public ResponseBean<UserBean> deleteSignup(@PathVariable("userId") int userId){
		
		signupDao.deleteSignup(userId);
		
		ResponseBean<UserBean> responseBean = new ResponseBean<>();
		
		responseBean.setMsg("Signup Deleted!!");
		responseBean.setStatus(201);
		
		return responseBean;
		
	}
}
