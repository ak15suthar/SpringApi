package com.service;

import org.springframework.stereotype.Service;

@Service(value = "OtpService2")
public class OtpService {
	final static int otpLength = 5;
	
	public String generateOtp() {
		String AlphaNumericeString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		StringBuilder sb = new StringBuilder(otpLength);
		
		for(int i = 0; i < otpLength; i++) {
			int index = (int)(AlphaNumericeString.length() * Math.random());
			sb.append(AlphaNumericeString.charAt(index));
		}
		
		return sb.toString();
	}
	
}
