package com.bean;

public class RoleBean {

	int roleId;
	String roleName;

	// 2 admin
	// 3 doctor
	// 4 patient
	// 5 pharmacy
	// 6 pathology
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
	