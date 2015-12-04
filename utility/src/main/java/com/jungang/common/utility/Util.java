package com.jungang.common.utility;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public interface Util {

	public void sendEmail(String mailHostName, String username, String password, boolean mailSSLOn,
			String mailFrom, String mailAddBcc, String mailAddTo, String mailMsg) throws EmailException;

}
