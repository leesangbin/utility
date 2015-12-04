package com.jungang.common.utility;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

public class UtilImpl {

	private static final String SMTP_HOST_NAME = "smtp.gmail.com";
	private static final String USERNAME = "sangbinlee999@gmail.com";
	private static final String PASSWORD = "dlrkdgndkQK";
	
	private static final boolean MAIL_SSL_ON = true;
	private static final int SMTP_PORT = 465;
	private static final String[] sendTo = null;

	public static void sendEmail(String mailFrom, String mailAddBcc, String mailAddTo, String mailSubject,
			String mailMsg) throws EmailException {
		// TODO Auto-generated method stub

		Email email = new SimpleEmail();
		email.setHostName(SMTP_HOST_NAME);
		email.setSmtpPort(SMTP_PORT);
		email.setAuthenticator(new DefaultAuthenticator(USERNAME, PASSWORD));
		email.setSSLOnConnect(MAIL_SSL_ON);
		email.setFrom(mailFrom);
		email.addTo(mailAddTo);
		email.addBcc(mailAddBcc);
		email.setSubject(mailSubject);
		email.setMsg(mailMsg);

		email.send();

	}

	public static void sendEmailWithFile(String filePath, String mailFrom, String mailAddBcc, String mailAddTo,
			String mailSubject, String mailMsg) throws EmailException {

		// Create the attachment
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath(filePath);
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("Picture of John");
		attachment.setName("John");

		// Create the email message
		MultiPartEmail email = new MultiPartEmail();
		email.setHostName(SMTP_HOST_NAME);
		email.setFrom(mailFrom);
		email.addTo(mailAddTo);
		email.addBcc(mailAddBcc);
		email.setSubject(mailSubject);
		email.setMsg(mailMsg);

		// add the attachment
		email.attach(attachment);

		// send the email
		email.send();

	}

	public static void sendEmailWithUrl(String fileUrl, String mailFrom, String mailAddBcc, String mailAddTo,
			String mailSubject, String mailMsg) throws EmailException, MalformedURLException {

		// Create the attachment
		EmailAttachment attachment = new EmailAttachment();
		attachment.setURL(new URL(fileUrl));
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("Picture of John");
		attachment.setName("John");

		// Create the email message
		MultiPartEmail email = new MultiPartEmail();
		email.setHostName(SMTP_HOST_NAME);
		email.setFrom(mailFrom);
		email.addTo(mailAddTo);
		email.addBcc(mailAddBcc);
		email.setSubject(mailSubject);
		email.setMsg(mailMsg);

		// add the attachment
		email.attach(attachment);

		// send the email
		email.send();

	}
}