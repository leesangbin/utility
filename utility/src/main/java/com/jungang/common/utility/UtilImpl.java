package com.jungang.common.utility;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
@Configuration
//@PropertySource("classpath:properties.xml")
@ComponentScan(basePackages = { "com.jungang.*" })
@PropertySource(value = { "classpath:application.properties" })
@Service("emailService")
public class UtilImpl implements UtilMail{

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
    @Autowired
    private Environment environment;
    
    
    @Value("${SMTP_HOST_NAME}")
	private String SMTP_HOST_NAME 	;
    @Value("${USERNAME}")
	private String USERNAME 		;
    @Value("${PASSWORD}")
	private String PASSWORD 		;
    @Value("${MAIL_SSL_ON}")
	private boolean MAIL_SSL_ON 	;  
    @Value("${MAIL_TLS_ON}")
	private boolean MAIL_TLS_ON 	;  
    @Value("${SMTP_PORT}")
	private int SMTP_PORT 			;   
	
//	private String SMTP_HOST_NAME 		= environment.getProperty("SMTP_HOST_NAME");   
//	private String USERNAME 			= environment.getProperty("USERNAME");         
//	private String PASSWORD 			= environment.getProperty("PASSWORD");         
//	private boolean MAIL_SSL_ON 		= Boolean.valueOf(environment.getProperty("MAIL_SSL_ON"));      
//	private boolean MAIL_TLS_ON 		= Boolean.valueOf(environment.getProperty("MAIL_TLS_ON"));      
//	private int SMTP_PORT 				= Integer.parseInt(environment.getProperty("SMTP_PORT"));        

	//	private static final String SMTP_HOST_NAME = "smtp.gmail.com";
//	private static final String USERNAME = "sangbinlee999@gmail.com";
//	private static final String PASSWORD = "dlrkdgndkQK";
//	
//	private static final boolean MAIL_SSL_ON = true;
//	private static final boolean MAIL_TLS_ON = true;
//	private static final int SMTP_PORT = 465;

	/**
	 * 현재년월일 시분초 밀리세컨드 문자열 구하기
	 * 
	 * @return
	 */
	public static String getDateTime() {
		String today = "";
		try {
			String format = "yyyy-MM-dd HH:mm:ss, SSS";
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format);
			today = formatter.format(new java.util.Date());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return today;
	}

	/**
	 * 1. A simple text email
	 * 
	 * @param toAddrs
	 * @param toCc
	 * @param toBcc
	 * @param mailSubject
	 * @param mailMsg
	 * @throws EmailException
	 */
	public void sendEmail(String[] toAddrs, String[] toCc, String[] toBcc, String mailSubject, String mailMsg) {

		String SMTP_HOST_NAME 		= environment.getProperty("SMTP_HOST_NAME");   
		System.out.println(SMTP_HOST_NAME);
		
		
		
		try {
			String dateTime = getDateTime();
			mailSubject += dateTime;
			Email email = new SimpleEmail();
			email.setHostName(SMTP_HOST_NAME);
			email.setSmtpPort(SMTP_PORT);
			email.setAuthenticator(new DefaultAuthenticator(USERNAME, PASSWORD));
			email.setSSLOnConnect(MAIL_SSL_ON);
			email.setFrom(USERNAME);
			email.setStartTLSEnabled(MAIL_TLS_ON);

			email.addTo(toAddrs);
			email.addCc(toCc);
			email.addBcc(toBcc);
			email.setSubject(mailSubject);
			email.setMsg(mailMsg);

			email.send();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 2. Sending emails with attachments
	 * 
	 * @param imageFilePath
	 * @param mailFrom
	 * @param mailAddBcc
	 * @param mailAddTo
	 * @param mailSubject
	 * @param mailMsg
	 * @throws EmailException
	 */
	public void sendEmail(String imageFilePath, String[] toAddrs, String[] toCc, String[] toBcc, String mailSubject, String mailMsg) {

		try {
			String dateTime = getDateTime();
			mailSubject += dateTime;

			// Create the attachment
			EmailAttachment attachment = new EmailAttachment();
			attachment.setDisposition(EmailAttachment.ATTACHMENT);
			attachment.setDescription("Picture of John");

			String attachFileName = "";
			String[] schemes = { "http", "https" };
			UrlValidator urlValidator = new UrlValidator(schemes);
			if (urlValidator.isValid(imageFilePath)) {
				attachment.setURL(new URL(imageFilePath));
				attachFileName = "url";
			} else {
				attachment.setPath(imageFilePath);
				Path path = Paths.get(imageFilePath);
				attachFileName = path.getFileName().toString();
			}
			attachment.setName(attachFileName);

			// Create the email message
			MultiPartEmail email = new MultiPartEmail();
			email.setHostName(SMTP_HOST_NAME);
			email.setAuthentication(USERNAME, PASSWORD);
			email.setStartTLSEnabled(MAIL_TLS_ON);
			email.setFrom(USERNAME);
			email.addTo(toAddrs);
			email.addCc(toCc);
			email.addBcc(toBcc);
			email.setSubject(mailSubject);
			email.setMsg(mailMsg);
			// add the attachment
			email.attach(attachment);

			// send the email
			email.send();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 3. Sending HTML formatted email
	 * 
	 * @param htmlFilePath
	 * @param imageFilePath
	 * @param toAddrs
	 * @param toCc
	 * @param toBcc
	 * @param mailSubject
	 * @param mailMsg
	 */
	public void sendEmail(String htmlFilePath, String imageFilePath, String[] toAddrs, String[] toCc, String[] toBcc, String mailSubject, String mailMsg) {
		try {

			String dateTime = getDateTime();
			mailSubject += dateTime;
			String htmlFileString = new String(Files.readAllBytes(Paths.get(htmlFilePath)));

			// System.out.println(htmlFileString);

			// Create the email message
			HtmlEmail email = new HtmlEmail();
			email.setHostName(SMTP_HOST_NAME);
			email.setAuthentication(USERNAME, PASSWORD);
			email.setStartTLSEnabled(MAIL_TLS_ON);
			email.setFrom(USERNAME);
			email.addTo(toAddrs);
			email.addCc(toCc);
			email.addBcc(toBcc);
			email.setSubject(mailSubject);

			// embed the image and get the content id
			URL url = new URL("http://www.apache.org/images/asf_logo_wide.gif");
			String cid = email.embed(url, "Apache logo");

			// set the html message
			email.setHtmlMsg(htmlFileString + "<div><img src=\"cid:" + cid + "\"></div>");

			// set the alternative message
			email.setTextMsg("Your email client does not support HTML messages");

			// send the email
			email.send();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 4. Sending HTML formatted email with embedded images
	 * @param urlString
	 * @param htmlFilePath
	 * @param imageFilePath
	 * @param toAddrs
	 * @param toCc
	 * @param toBcc
	 * @param mailSubject
	 * @param mailMsg
	 */
	public void sendEmail(String urlString, String htmlFilePath, String imageFilePath, String[] toAddrs, String[] toCc, String[] toBcc, String mailSubject, String mailMsg) {

		try {

			// define you base URL to resolve relative resource locations
			URL url = new URL(urlString);
			// create the email message
			ImageHtmlEmail email = new ImageHtmlEmail();
			email.setDataSourceResolver(new DataSourceUrlResolver(url));

			// img tag 포함된 html
			// load your HTML email template
			String htmlEmailTemplate = new String(Files.readAllBytes(Paths.get(htmlFilePath)));

			email.setHostName(SMTP_HOST_NAME);
			email.setAuthentication(USERNAME, PASSWORD);
			email.setStartTLSEnabled(MAIL_TLS_ON);
			email.setFrom(USERNAME);
			email.addTo(toAddrs);
			email.addCc(toCc);
			email.addBcc(toBcc);

			String dateTime = getDateTime();
			mailSubject += dateTime + " with embedded images";

			email.setSubject(mailSubject);

			// set the html message
			email.setHtmlMsg(htmlEmailTemplate);

			// set the alternative message
			email.setTextMsg("Your email client does not support HTML messages");

			// send the email
			email.send();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}