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
import org.springframework.web.bind.annotation.RestController;

import com.bean.ResponseBean;
import com.bean.RoleBean;
import com.dao.RoleDao;

@CrossOrigin
@RestController
public class RoleController {

	@Autowired
	RoleDao roleDao;
	
	@PostMapping("/addRole")
	public ResponseBean<RoleBean> addRole(@RequestBody RoleBean roleBean){
		roleDao.insertRole(roleBean);
		
		ResponseBean<RoleBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(roleBean);
		responseBean.setMsg("Role Inserted!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@GetMapping("/listRole")
	public ResponseBean<List<RoleBean>> listRole(){
		
		List<RoleBean> roleBean = roleDao.listRole();
		
		ResponseBean<List<RoleBean>> responseBean = new ResponseBean<>();
		responseBean.setData(roleBean);
		responseBean.setMsg("Role List!!");
		responseBean.setStatus(201);
		return responseBean;
	}
	
	@PutMapping("/updateRole")
	public ResponseBean<RoleBean> updateRole(RoleBean roleBean){
		
		roleDao.updateRole(roleBean);
		
		ResponseBean<RoleBean> responseBean = new ResponseBean<>();
		responseBean.setData(roleBean);
		responseBean.setMsg("Role Updated!!");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@DeleteMapping("/deleteRole/{roleid}")
	public ResponseBean<RoleBean> deleteRole(@PathVariable("roleid") int roleid){
		
		roleDao.deleteRole(roleid);
		
		ResponseBean<RoleBean> responseBean = new ResponseBean<>();
		responseBean.setMsg("Role Deleted!!");
		responseBean.setStatus(201);
		return responseBean;
	}
}
