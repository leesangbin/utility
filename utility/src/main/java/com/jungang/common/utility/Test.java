package com.jungang.common.utility;

import java.net.MalformedURLException;

import org.apache.commons.mail.EmailException;

public class Test {

	public static void main(String[] args) throws EmailException, MalformedURLException {

		/*
		 * TODO 0. https://commons.apache.org/proper/commons-email/
		 * 
		 * 1. A simple text email----------------------------ok 2. Sending
		 * emails with attachments 3. Sending HTML formatted email 4. Sending
		 * HTML formatted email with embedded images 5.
		 * 
		 */
		String mailFrom = "sangbinlee9@gmail.com";
		String mailAddBcc = "lsv400@naver.com";
		String mailAddTo = "sangbinlee9@gmail.com";
		String mailSubject = "This is a test mail subject... :-)";
		String mailMsg = "This is a test mail msg... :-)";
		String filePath = "";
		String fileUrl = "";

		// 받는 사람
		String[] recipients = { "receiver@gmail.com" };

		UtilImpl.sendEmail(mailFrom, mailAddBcc, mailAddTo, mailSubject, mailMsg);
		// UtilImpl.sendEmailWithFile(filePath, mailHostName, username,
		// password, mailSSLOn, mailFrom, mailAddBcc,
		// mailAddTo, mailSubject, mailMsg);
		// UtilImpl.sendEmailWithUrl(fileUrl, mailHostName, username, password,
		// mailSSLOn, mailFrom, mailAddBcc, mailAddTo,
		// mailSubject, mailMsg);
	}

}
