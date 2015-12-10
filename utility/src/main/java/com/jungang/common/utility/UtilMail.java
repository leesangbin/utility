package com.jungang.common.utility;

public interface UtilMail {
	public void sendEmail(String[] toAddrs, String[] toCc, String[] toBcc, String mailSubject, String mailMsg);
	public void sendEmail(String imageFilePath, String[] toAddrs, String[] toCc, String[] toBcc, String mailSubject, String mailMsg);
	public void sendEmail(String htmlFilePath, String imageFilePath, String[] toAddrs, String[] toCc, String[] toBcc, String mailSubject, String mailMsg);
	public void sendEmail(String urlString, String htmlFilePath, String imageFilePath, String[] toAddrs, String[] toCc, String[] toBcc, String mailSubject, String mailMsg);
}
