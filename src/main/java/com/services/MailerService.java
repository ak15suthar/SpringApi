package com.services;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.bean.DoctorProfileBean;
import com.bean.UserBean;

@Service
public class MailerService {

	public void sendOtpForUserVerification(UserBean userBean) {

		String to = userBean.getEmail();// Change Accordingly

		// Sender's email ID
		String from = "ak15suthar@gmail.com";
		final String username = "ak15suthar@gmail.com";
		final String password = "aknk ztra deel ouwa";

		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// Get the session object
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(from));

			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			message.setSubject("HealthAssist Email Verification OTP!!");

			String url = "<a href='https://healthassistangular.herokuapp.com'>Click Here to Verify</a>";

			message.setContent("<h3>Hello, " + userBean.getFirstName() + " " + userBean.getLastName() + "<h3><br>"
					+ userBean.getOtp()
					+ "</b> is your OTP to verify your email<br>please click below to verify your account<br>" + url,
					"text/html");

			Transport.send(message);

			System.out.println("Sent message successfully...");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void sendOtpForForgetPassword(UserBean userBean) {

		String to = userBean.getEmail();

		String from = "ak15suthar@gmail.com";
		final String username = "ak15suthar@gmail.com";
		final String password = "aknk ztra deel ouwa";

		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// Get the session object
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(from));

			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			message.setSubject("HealthAssist Password Reset OTP!!");

			String url = "<a href='https://healthassistangular.herokuapp.com/otp-verify'>Click Here to Verify</a>";
			
			message.setContent(
					"<h3>Hello, " + userBean.getFirstName() + " " + userBean.getLastName() + "<h3><br>"
							+ userBean.getOtp()
							+ "</b> is your OTP to verify your email<br>please click below to verify your account<br>"+ url,
					"text/html");
			
			Transport.send(message);
			
			System.out.println("Send successfully!!");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void sendWelcomeMail(UserBean userBean) {

	}

	public void sendDoctorRegisterMail(DoctorProfileBean doctorProfileBean,UserBean userBean) {
		String to = userBean.getEmail();// Change Accordingly

		System.out.println("Mail"+to);
		// Sender's email ID
		String from = "ak15suthar@gmail.com";
		final String username = "ak15suthar@gmail.com";
		final String password = "aknk ztra deel ouwa";

		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// Get the session object
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(from));

			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			message.setSubject("HealthAssist KYC Pending!!");

			message.setContent("<h3>Hello, " + doctorProfileBean.getFirstName() + " " + doctorProfileBean.getLastName() + "<h3><br>"
					+ doctorProfileBean.getStatus()
					+ "</b> your KYC is pending our Team will contact you soon!!" ,
					"text/html");

			Transport.send(message);

			System.out.println("Sent message successfully...");

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	public void sendDoctorWelcomeMail(DoctorProfileBean doctorProfileBean) {

	}

	public void sendMailForPasswordUpdate(UserBean userBean) {

		String to = userBean.getEmail();

		String from = "ak15suthar@gmail.com";
		final String username = "ak15suthar@gmail.com";
		final String password = "aknk ztra deel ouwa";

		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// Get the session object
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(from));

			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			message.setSubject("HealthAssist Password Updated!!");

			message.setContent("Hello ," + userBean.getFirstName() + ", <br>Your password successfully updated..",
					"text/html");
			Transport.send(message);

			System.out.println("Sent message successfully..");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
