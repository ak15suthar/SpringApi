package com.bean;

public class RoleBean {

	int roleId;
	String roleName;

	// 1 admin
	// 2 patient
	// 3 doctor
	// 4 pharmacy
	// 5 pathology
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
