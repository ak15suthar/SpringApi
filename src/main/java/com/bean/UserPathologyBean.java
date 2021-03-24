package com.bean;

public class UserPathologyBean extends PathologyBean {
	int userId;
	int pathologyId;
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPathologyId() {
		return pathologyId;
	}

	public void setPathologyId(int pathologyId) {
		this.pathologyId = pathologyId;
	}
}
