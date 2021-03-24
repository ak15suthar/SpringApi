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

import com.bean.PathologyBean;
import com.bean.ResponseBean;
import com.bean.UserBean;
import com.dao.PathologyDao;

@CrossOrigin
@RestController
public class PathologyController {

	@Autowired
	PathologyDao pathologyDao;

	@PostMapping("/addPathology")
	public ResponseBean<PathologyBean> addPathology(@RequestBody PathologyBean pathologyBean) {

		pathologyDao.addPathology(pathologyBean);

		ResponseBean<PathologyBean> responseBean = new ResponseBean<>();

		responseBean.setData(pathologyBean);
		responseBean.setMsg("Pathology Added!!");
		responseBean.setStatus(200);

		return responseBean;
	}

	@PostMapping("/addAssignUserPathology")
	public ResponseBean<PathologyBean> addAssignUserPathology(@RequestBody PathologyBean pathologyBean) {

		pathologyDao.addAssignUserPathology(pathologyBean);

		ResponseBean<PathologyBean> responseBean = new ResponseBean<>();

		responseBean.setData(pathologyBean);
		responseBean.setMsg("User Pathology Added Successfully...!!");
		responseBean.setStatus(200);

		return responseBean;
	}

	@GetMapping("/listPathology")
	public ResponseBean<List<PathologyBean>> listPathology() {

		List<PathologyBean> pathologyBean = pathologyDao.listPathology();

		ResponseBean<List<PathologyBean>> responseBean = new ResponseBean<>();

		responseBean.setData(pathologyBean);
		responseBean.setMsg("Pathology List!!");
		responseBean.setStatus(200);

		return responseBean;
	}

	@GetMapping("/listAssignUserPathology")
	public ResponseBean<List<UserBean>> listAssignUserPathology() {
	
		List<UserBean> pathologyBean = pathologyDao.listAssignUserPathology();
		
		ResponseBean<List<UserBean>> responseBean = new ResponseBean<>();

		responseBean.setData(pathologyBean);
		responseBean.setMsg("User Pathology List Display..!!!!");
		responseBean.setStatus(201);
		
		return responseBean;
	}

	@GetMapping("/getPathologyById/{pathologyId}")
	public ResponseBean<PathologyBean> getPathologyById(@RequestBody @PathVariable("pathologyId") int pathologyId,
			PathologyBean pathologyBean) {
		pathologyBean = pathologyDao.getPathologyById(pathologyId);

		ResponseBean<PathologyBean> responseBean = new ResponseBean<>();

		responseBean.setData(pathologyBean);
		responseBean.setMsg("Pathology By ID!!");
		responseBean.setStatus(200);

		return responseBean;
	}

	@PutMapping("/updatePathology")
	public ResponseBean<PathologyBean> updatePathology(@RequestBody PathologyBean pathologyBean) {

		pathologyDao.updatePathology(pathologyBean);

		ResponseBean<PathologyBean> responseBean = new ResponseBean<>();

		responseBean.setData(pathologyBean);
		responseBean.setMsg("Pathology Updated!!");
		responseBean.setStatus(202);

		return responseBean;
	}

	@DeleteMapping("/deletePathology/{pathologyId}")
	public ResponseBean<PathologyBean> deletePathology(@PathVariable("pathologyId") int pathologyId) {

		pathologyDao.deletePathology(pathologyId);

		ResponseBean<PathologyBean> responseBean = new ResponseBean<>();

		responseBean.setMsg("Pathology Deleted!!");
		responseBean.setStatus(202);

		return responseBean;
	}
}
