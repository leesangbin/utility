package com.jungang.common.utility;

/**
 * <pre>
 * @author Administrator
 *1. A simple text email (받는사람, 참조, 숨은참조, MAIL제목, MAIL내용)
 *2. Sending emails with attachments
 *3. Sending HTML formatted email
 *4. Sending HTML formatted email with embedded images
 *</pre>
 *
 *
 */
public interface UtilMail {
	public void sendEmail(String[] toAddrs, String[] toCc, String[] toBcc, String mailSubject, String mailMsg);

	public void sendEmail(String imageFilePath, String[] toAddrs, String[] toCc, String[] toBcc, String mailSubject, String mailMsg);

	public void sendEmail(String htmlFilePath, String imageFilePath, String[] toAddrs, String[] toCc, String[] toBcc, String mailSubject, String mailMsg);

	public void sendEmail(String urlString, String htmlFilePath, String imageFilePath, String[] toAddrs, String[] toCc, String[] toBcc, String mailSubject, String mailMsg);
}
