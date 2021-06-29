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

import com.bean.AppointmentBean;
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

			String url = "<a href='https://healthassistangular.herokuapp.com/verify-user'>Click Here to Verify</a>";

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

	
	public void sendRescheduleReason(AppointmentBean appointmentBean) {
		String to = appointmentBean.getEmail();// change accordingly

		System.out.println("in mailer....");
		// Sender's email ID needs to be mentioned
		String from = "ak15suthar@gmail.com";// change accordingly
		final String username = "ak15suthar@gmail.com";// change accordingly
		final String password = "aknk ztra deel ouwa";// change accordingly

		// Assuming you are sending email through relay.jangosmtp.net
		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// Get the Session object.
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
		}
		});

		try {
		// Create a default MimeMessage object.
		Message message = new MimeMessage(session);

		// Set From: header field of the header.
		message.setFrom(new InternetAddress(from));

		// Set To: header field of the header.
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

		// Set Subject: header field
		message.setSubject("Please, Reschedule Your Appointment");

		String url = "<a href='https://healthassistangular.herokuapp.com'>Reschedule Your Appointment Here</a>";
		// Now set the actual message
		message.setContent("Hello " + appointmentBean.getPatientName() + ","+ "<br>" + "Sorry for your inconvience, your appointment with Dr. "+ appointmentBean.getFirstName() + " " + appointmentBean.getLastName() +" has been reschedule due to following reason : " + "<br>" + "<b> Reason For Reschedule Appointment : </b>" + appointmentBean.getStatusReason()
		+"<br><br>"+ "Please Click on the below line reschedule your appointment"+"<br>"+ url +"<br><br><br><br>"+"Yours Faithful, "+ "<br>"+"Health Assist" , "text/html");

		// Send message
		Transport.send(message);

		System.out.println("Sent Reschedule message successfully....");

		} catch (MessagingException e) {
		e.printStackTrace();
		System.out.println("sending fail....");
		}
	}

	
	public void sendRejectedReason(AppointmentBean appointmentBean) {
		String to = appointmentBean.getEmail();// change accordingly

		// Sender's email ID needs to be mentioned
		String from = "ak15suthar@gmail.com";// change accordingly
		final String username = "ak15suthar@gmail.com";// change accordingly
		final String password = "aknk ztra deel ouwa";// change accordingly

		// Assuming you are sending email through relay.jangosmtp.net
		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// Get the Session object.
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
		}
		});

		try {
		// Create a default MimeMessage object.
		Message message = new MimeMessage(session);

		// Set From: header field of the header.
		message.setFrom(new InternetAddress(from));

		// Set To: header field of the header.
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

		// Set Subject: header field
		message.setSubject("Your Appointment Has Been Rejected");

		String url = "<a href='https://healthassistangular.herokuapp.com'>Book Your Appointment Here</a>";
		// Now set the actual message
		message.setContent("Hello " + appointmentBean.getPatientName() + ","+ "<br>" + "Sorry for your inconvience, your appointment with Dr. "+ appointmentBean.getFirstName() + " " + appointmentBean.getLastName() +" has been rejected due to following reason : " + "<br>" + "<b> Reason For Rejected Appointment : </b>" + appointmentBean.getStatusReason()
		+"<br><br>"+ "Please click on the below line for book your new appointment"+"<br>"+ url +"<br><br><br><br>"+"Yours Faithful, "+ "<br>"+"Health Assist" , "text/html");

		// Send message
		Transport.send(message);

		System.out.println("Sent Rejected message successfully....");

		} catch (MessagingException e) {
		e.printStackTrace();
		}
	}
		
	public void sendWelcomeMail(UserBean userBean) {
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

			message.setSubject("HealthAssist Successfully Verified!!");

			message.setContent("<h3>Hello, " + userBean.getFirstName() + " " + userBean.getLastName() + "<h3><br>"
					+ "</b> Your account successfully verified!! <br> You can now access HealthAssist" ,
					"text/html");

			Transport.send(message);

			System.out.println("Sent message successfully...");

		} catch (MessagingException e) {
			e.printStackTrace();
		}

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
					+ "</b> Your KYC is pending our Team will contact you soon!!" ,
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
