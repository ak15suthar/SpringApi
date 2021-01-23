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

			String url = "<a href='http://localhost:9595/verifyemail'>Verify</a>";

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

			message.setContent(
					"<h3>Hello, " + userBean.getFirstName() + " " + userBean.getLastName() + "<h3><br>"
							+ userBean.getOtp()
							+ "</b> is your OTP to verify your email<br>please click below to verify your account<br>",
					"text/html");
			
			Transport.send(message);
			
			System.out.println("Send successfully!!");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void sendWelcomeMail(UserBean userBean) {

	}

	public void sendDoctorRegisterMail(DoctorProfileBean doctorProfileBean) {

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
