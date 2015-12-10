package com.jungang.common.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import org.apache.commons.mail.EmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MailTest implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(MailTest.class);

	public static void main(String[] args) throws EmailException, MalformedURLException {
		ApplicationContext ctx = SpringApplication.run(MailTest.class, args);

		System.out.println("Let's inspect the beans provided by Spring Boot:");
		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			System.out.println(beanName);
		}
		SpringApplication.run(MailTest.class);

	}

	/**
	 * 파일 저장 경로 가져오기 TODO XML PROPERTIES 환경설정파일에서 가져오기
	 * 
	 * @param fileName
	 * @return
	 */
	private static String getPathWithOS(String fileName) {

		// xml 설정파일에서 가져오기
		// String workingDirectory = "C:\\Users\\Public\\Pictures\\Sample
		// Pictures";
		String workingDirectory = "";

		// ApplicationContext context = new
		// ClassPathXmlApplicationContext("properties.xml");
		// Environment environment = context.getEnvironment();
		// workingDirectory =
		// environment.getProperty("window.workingDirectory");

		// Reading properties file in Java example
		Properties props = new Properties();
		try {
			FileInputStream fis;
			// 프로젝트 안의 xml 경로
			fis = new FileInputStream("C:/Users/Administrator/git/utility/utility/src/main/resources/properties.xml");
			// loading properites from properties file
			props.loadFromXML(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidPropertiesFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String absoluteFilePath = "";
		String your_os = System.getProperty("os.name").toLowerCase();

		if (your_os.indexOf("win") >= 0) {

			// reading proeprty
			workingDirectory = props.getProperty("window.workingDirectory");
			// if windows
			absoluteFilePath = workingDirectory + File.separator + fileName;

			Path path = Paths.get(absoluteFilePath);
			// absoluteFilePath = workingDirectory + "\\" + fileName;

			absoluteFilePath = path.toString();

		} else if (your_os.indexOf("nix") >= 0 || your_os.indexOf("nux") >= 0 || your_os.indexOf("mac") >= 0) {

			// reading proeprty
			workingDirectory = props.getProperty("linux.workingDirectory");

			// if unix or mac
			absoluteFilePath = workingDirectory + File.separator + fileName;
			// absoluteFilePath = workingDirectory + "/" + fileName;

		} else {

			// reading proeprty
			workingDirectory = props.getProperty("mac.workingDirectory");
			// unknow os?
			absoluteFilePath = workingDirectory + File.separator + fileName;
			// absoluteFilePath = workingDirectory + "/" + fileName;

		}
		return absoluteFilePath;
	}

	@Override
	public void run(String... arg0) throws Exception {

		// {{ set value [공통]

		/**
		 * TODO SET VALUE 1. 받는사람 2. 참조 3. 숨은참조 4. MAIL제목 5. MAIL내용 6. 첨부파일명
		 */

		// 받는사람
		String[] toAddrs = new String[] { "lsv400@naver.com" };
		// 참조
		String[] toCc = new String[] { "lsv400@naver.com", "sangbinlee999@gmail.com" };
		// 숨은참조
		String[] toBcc = new String[] { "sangbinlee9@gmail.com", "lsv400@daum.net" };
		// MAIL제목
		String mailSubject = "메일 제목 테스트  :-)";
		// MAIL내용
		String mailMsg = "메일 내용 테스트... :-)";
		// }}

		// {{ set value [개별]

		// 1. A simple text email (받는사람, 참조, 숨은참조, MAIL제목, MAIL내용)

		// 2. Sending emails with attachments
		// 첨부파일명
		String imageFileName = "Penguins.jpg";
		String filePath = getPathWithOS(imageFileName);
		// HTTPS TEST
		String fileUrl = "https://ssl.gstatic.com/ui/v1/icons/mail/images/favicon5.ico";
		// HTTP TEST
		fileUrl = "http://www.apache.org/images/asf_logo_wide.gif";

		// 3. Sending HTML formatted email
		String htmlFileName = "test.html";
		String htmlFilePath = getPathWithOS(htmlFileName);

		// 4. Sending HTML formatted email with embedded images
		String urlString = "http://pds.joins.com";

		// }}

		// 메일
		UtilImpl utilImpl = new UtilImpl();

		// {{ test send email

		// 1. A simple text email - ok
		utilImpl.sendEmail(toAddrs, toCc, toBcc, mailSubject, mailMsg);

		// 2. Sending emails with attachments
		// utilImpl.sendEmail(filePath, toAddrs, toCc, toBcc, mailSubject,
		// mailMsg);

		// 2. Sending emails with attachments
		// utilImpl.sendEmail(fileUrl, toAddrs, toCc, toBcc, mailSubject,
		// mailMsg);

		// 3. Sending HTML formatted email
		// utilImpl.sendEmail(htmlFilePath, filePath, toAddrs, toCc, toBcc,
		// mailSubject, mailMsg);

		// 4. Sending HTML formatted email with embedded images
		// utilImpl.sendEmail(urlString, htmlFilePath, filePath, toAddrs, toCc,
		// toBcc, mailSubject, mailMsg);

		// }}

	}

}
