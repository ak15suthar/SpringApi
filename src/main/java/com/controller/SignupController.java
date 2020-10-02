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
import com.bean.SignupBean;
import com.dao.SignupDao;

@RestController
public class SignupController {
	
	@Autowired
	SignupDao signupDao;
	
	
	@PostMapping("/login")
	public ResponseBean<SignupBean> login(@RequestBody LoginBean loginBean){
		
		ResponseBean<SignupBean> responseBean = new ResponseBean<>();
			
		SignupBean signupBean = signupDao.login(loginBean.getEmail(),loginBean.getPassword());
		
		responseBean.setData(signupBean);
		responseBean.setMsg("Login Successfully!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@PostMapping("/addSignup")
	public ResponseBean<SignupBean> addSignup(@RequestBody SignupBean signupBean){
		
		signupDao.insertSignup(signupBean);
		
		ResponseBean<SignupBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(signupBean);
		responseBean.setMsg("Signup Inserted!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@GetMapping("/listSignup")
	public ResponseBean<List<SignupBean>> listSignup(){
		
		List<SignupBean> signupBean = signupDao.listSignup();
		
		ResponseBean<List<SignupBean>> responseBean = new ResponseBean<>();
		
		responseBean.setData(signupBean);
		responseBean.setMsg("Signup List!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@PutMapping("updateSignup")
	public ResponseBean<SignupBean> updateSignup(SignupBean signupBean){
		
		signupDao.updateSignup(signupBean);
		
		ResponseBean<SignupBean> responseBean = new ResponseBean<>();
		responseBean.setData(signupBean);
		responseBean.setMsg("Signup Updated!!");
		responseBean.setStatus(201);
		
		return responseBean;
	}
	
	@DeleteMapping("deleteSignup/{userId}")
	public ResponseBean<SignupBean> deleteSignup(@PathVariable("userId") int userId){
		
		signupDao.deleteSignup(userId);
		
		ResponseBean<SignupBean> responseBean = new ResponseBean<>();
		
		responseBean.setMsg("Signup Deleted!!");
		responseBean.setStatus(201);
		
		return responseBean;
		
	}
}
