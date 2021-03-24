package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.bean.DoctorProfileBean;
import com.bean.PatientProfileBean;
import com.bean.UserBean;

@Repository
public class SessionDao {

	@Autowired
	JdbcTemplate stmt;

	public int insertUser(UserBean userBean) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String insertSql = "insert into users (email,password,firstname,lastname,gender,roleid,status,statusreason,otp) values(?,?,?,?,?,?,?,?,?)";

		stmt.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);

				pstmt.setString(1, userBean.getEmail());
				pstmt.setString(2, userBean.getPassword());
				pstmt.setString(3, userBean.getFirstName());
				pstmt.setString(4, userBean.getLastName());
				pstmt.setString(5, userBean.getGender());
				pstmt.setInt(6, userBean.getRoleId());
				pstmt.setInt(7, userBean.getStatus());
				pstmt.setString(8, userBean.getStatusReason());
				pstmt.setString(9, userBean.getOtp());

				return pstmt;
			}
		}, keyHolder);

		int userId = (Integer) keyHolder.getKeys().get("userid");
		userBean.setUserId(userId);
		return userBean.getUserId();

	}

	public void addDoctorProfile(DoctorProfileBean doctorProfileBean) {
		UserBean userBean = new UserBean();

//		userBean.setRoleId(3);
		userBean.setEmail(doctorProfileBean.getEmail());
		userBean.setPassword(doctorProfileBean.getPassword());
		userBean.setFirstName(doctorProfileBean.getFirstName());
		userBean.setLastName(doctorProfileBean.getLastName());
		userBean.setGender(doctorProfileBean.getGender());
		userBean.setRoleId(doctorProfileBean.getRoleId());
		userBean.setStatus(doctorProfileBean.getStatus());
		userBean.setStatusReason(doctorProfileBean.getStatusReason());

		int userId = insertUser(userBean);
		System.out.println("Userid : " + userId);
		doctorProfileBean.setUserId(userId);

		stmt.update(
				"insert into doctorprofile(userid,qualification,specialization,experience,profilepic,about,registrationno) values (?,?,?,?,?,?,?)",
				doctorProfileBean.getUserId(), doctorProfileBean.getQualification(),
				doctorProfileBean.getSpecialization(), doctorProfileBean.getExperience_in_year(),
				doctorProfileBean.getProfile_pic(), doctorProfileBean.getAbout(),
				doctorProfileBean.getRegistrationNo());
	}

	public List<UserBean> listSignup() {

		List<UserBean> userBean = stmt.query("select * from users where isdeleted = 0",
				BeanPropertyRowMapper.newInstance(UserBean.class));

		return userBean;
	}

	public void updateSignup(UserBean userBean) {
		stmt.update(
				"update users set email = ?,password = ?,firstname = ?,lastname = ?,gender = ?,roleid = ? where userid = ?",
				userBean.getEmail(), userBean.getPassword(), userBean.getFirstName(), userBean.getLastName(),
				userBean.getGender(), userBean.getRoleId(), userBean.getUserId());

	}

	public void deleteSignup(int userId) {
		stmt.update("update users set isdeleted = 1 where userid = ?", userId);
	}

	public UserBean login(String email, String password) {

		UserBean userBean = null;
		try {
			userBean = stmt.queryForObject("select * from users where email=? and password=?",
					new Object[] { email, password }, BeanPropertyRowMapper.newInstance(UserBean.class));

		} catch (Exception e) {
			System.out.println("User not found");

		}
		return userBean;
	}

	public UserBean getUserByEmail(String email) {
		UserBean userBean = null;

		try {
			userBean = stmt.queryForObject("select * from users where email = ?", new Object[] { email },
					BeanPropertyRowMapper.newInstance(UserBean.class));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return userBean;
	}

	public void updatePassword(UserBean userBean) {
		stmt.update("update users set password = ? where email = ?", userBean.getPassword(), userBean.getEmail());

	}

	public void addPatientProfile(PatientProfileBean patientProfileBean) {
		UserBean userBean = new UserBean();
		userBean.setRoleId(4);

		int userId = insertUser(patientProfileBean);
		patientProfileBean.setCityId(0);

		stmt.update(
				"insert into patientprofile(patientname,gender,phoneno,email,age,profilepic,pincode,userid) values(?,?,?,?,?,?,?,?)",
				patientProfileBean.getPatientName(), patientProfileBean.getGender(), patientProfileBean.getPhoneNo(),
				patientProfileBean.getEmail(), patientProfileBean.getAge(), patientProfileBean.getProfilePic(),
				patientProfileBean.getPincode(), patientProfileBean.getUserId());

	}

	public void adminAddUsers(UserBean userBean) {
		stmt.update("INSERT INTO users(email, password, firstname, lastname, gender, roleid) VALUES (?, ?, ?, ?, ?, ?)",
				userBean.getEmail(), userBean.getPassword(), userBean.getFirstName(), userBean.getLastName(),
				userBean.getGender(), userBean.getRoleId());

	}

	public void adminAddPatientProfile(PatientProfileBean patientProfileBean) {
		UserBean userBean = new UserBean();
		userBean.setRoleId(4);

		int userId = insertUser(patientProfileBean);
		patientProfileBean.setUserId(userId);
		patientProfileBean.setPatientName(patientProfileBean.getFirstName());

		stmt.update(
				"insert into patientprofile(patientname,gender,phoneno,email,age,profilepic,cityid,pincode,userid) values(?,?,?,?,?,?,?,?,?)",
				patientProfileBean.getPatientName(), patientProfileBean.getGender(), patientProfileBean.getPhoneNo(),
				patientProfileBean.getEmail(), patientProfileBean.getAge(), patientProfileBean.getProfilePic(),
				patientProfileBean.getCityId(), patientProfileBean.getPincode(), patientProfileBean.getUserId());

		System.out.println(patientProfileBean.getUserId());
	}

	public UserBean getUserById(int userId) {
		
		UserBean userBean = null;
		try {
			userBean = stmt.queryForObject("select * from users where userid=?", new Object[] { userId },
					BeanPropertyRowMapper.newInstance(UserBean.class));
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return userBean;
	}
}