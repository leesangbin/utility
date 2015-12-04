package com.jungang.common.utility;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailUtil {

	public static void main(String[] args) throws EmailException {
		// TODO Auto-generated method stub

		System.out.println("utility wrapping ... ");

		String mailHostName = "smtp.googlemail.com";
		int mailSmtpPort = 465;
		String username = "sangbinlee999@gmail.com";
		String password = "dlrkdgndkQk";
		boolean mailSSLOn = true;
		String mailFrom = "sangbinlee9@gmail.com";
		String mailAddBcc = "lsv400@naver.com";
		String mailAddTo = "sangbinlee9@gmail.com";
		String mailMsg = "This is a test mail ... :-)";
		
		sendEmail(mailHostName, username, password, mailSSLOn, mailFrom, mailAddBcc, mailAddTo, mailMsg);

		

//		sendEmail();
		
	}

	private static void sendEmail(String mailHostName, String username, String password, boolean mailSSLOn,
			String mailFrom, String mailAddBcc, String mailAddTo, String mailMsg) throws EmailException {
	}

}
