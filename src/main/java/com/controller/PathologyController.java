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

import com.bean.PathologyBean;
import com.bean.ResponseBean;
import com.dao.PathologyDao;

@RestController
public class PathologyController {

	@Autowired
	PathologyDao pathologyDao;
	
	@PostMapping("/addPathology")
	public ResponseBean<PathologyBean> addPathology(@RequestBody PathologyBean pathologyBean){
		
		pathologyDao.addPathology(pathologyBean);
		
		ResponseBean<PathologyBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(pathologyBean);
		responseBean.setMsg("Pathology Added!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@GetMapping("/listPathology")
	public ResponseBean<List<PathologyBean>> listPathology(){
		
		List<PathologyBean> pathologyBean = pathologyDao.listPathology();
		
		ResponseBean<List<PathologyBean>> responseBean = new ResponseBean<>();
		
		responseBean.setData(pathologyBean);
		responseBean.setMsg("Pathology List!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@PutMapping("/updatePathology")
	public ResponseBean<PathologyBean> updatePathology(@RequestBody PathologyBean pathologyBean){
		
		pathologyDao.updatePathology(pathologyBean);
		
		ResponseBean<PathologyBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(pathologyBean);
		responseBean.setMsg("Pathology Updated!!");
		responseBean.setStatus(202);
		
		return responseBean;
	}
	
	@DeleteMapping("/deletePathology/{pathologyId}")
	public ResponseBean<PathologyBean> deletePathology(@PathVariable("pathologyId") int pathologyId){
		
		pathologyDao.deletePathology(pathologyId);
		
		ResponseBean<PathologyBean> responseBean = new ResponseBean<>();
		
		responseBean.setMsg("Pathology Deleted!!");
		responseBean.setStatus(202);
		
		return responseBean;
	}
}
