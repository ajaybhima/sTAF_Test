package com.careerit.web.utils;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class JavaMailUtils {
	
public static void main(String[] args) throws MessagingException {
		
		
		String mailSubject="CareerIT Selenium Session APR Batch 27th June - Java Mail API";
		String mailBody="Hi,<br><br>This is Test Mail from  CareerIT Selenum Training <br><br> Thanks,<br> CareerIT Team, April Batch 27th June";
		
		String smtpMailServer="smtp.gmail.com";
		String smtpUserName="ajay.selenium.online@gmail.com";
		String smtpPassword="<GmailPassword>";
		String smtpPort="587";
				
		String fromEmail="ajay.bhima@gmail.com";
		String recipientToEmails="ajay.bhima@gmail.com,ajay.bheema@gmail.com";
		String ccAddresses="ajay.bhima@gmail.com";
		
		String[] attachements=new String[]{"./build/test-output/emailable-report.html","./resources/TestDataSheet.xlsx"};
		
		
		try {
			System.out.println("Send Email --- Start");
			Session session = getMailSession(smtpMailServer,smtpUserName,smtpPassword,smtpPort);
			MimeMessage message = new MimeMessage(session);
			setMessageAttributes(message, mailSubject,fromEmail,recipientToEmails,ccAddresses);
			
			addAttachment(message,attachements,mailBody);
			
			System.out.println("Mail before Send");
			Transport.send(message);
			
			System.out.println("Email Sent Successfully");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	public static Session getMailSession(String mailServer,String smtpUserName,String smtpPassword,String smtpPort) {
		Properties props = new Properties();
		Session session = null;
		final String smtpUser=smtpUserName;
		final String smptPwd=smtpPassword;
		try{
			System.out.println("getMailSession -- Start");
			
			props.put("mail.smtp.auth", "true");
		    props.put("mail.smtp.starttls.enable", "true");
		    props.put("mail.smtp.host", mailServer);
		    props.put("mail.smtp.port", smtpPort);
		
		    session= Session.getInstance(props,
			      new javax.mail.Authenticator() {
			         protected PasswordAuthentication getPasswordAuthentication() {
			            return new PasswordAuthentication(smtpUser, smptPwd);
			         }
			      });
		    System.out.println("getMailSession -- End");
		}
		catch(Exception e){System.out.println("Sending Initial Job Trigger Email Failed");e.printStackTrace();}
        return session;
	}
	public static MimeMessage setMessageAttributes(MimeMessage message,String subjectMsg,String fromEamil,String recipientEmails,String ccAddress){
		
		try {
			System.out.println("setMessageAttributes -- Start");
			message.setFrom(new InternetAddress(fromEamil));
			
			message.setRecipients(Message.RecipientType.TO, recipientEmails);
			
           	if (ccAddress.length()!=0){
           		message.setRecipients(Message.RecipientType.CC,ccAddress);
           	}
           	
           	message.setSentDate(new Date(System.currentTimeMillis()));
           	
    	    message.setSubject(subjectMsg);
    	   /* BodyPart messageBodyPart = new MimeBodyPart();
    	    messageBodyPart.setContent(mailBody, "text/html");*/
    	    System.out.println("setMessageAttributes -- End");
		} catch (Exception e) {
			System.out.println("Sending Initial Job Trigger Email Failed");
			e.printStackTrace();
		}
		return message;
		
	}
	
	
	
	public static MimeMessage addAttachment(MimeMessage message,String[] filePaths,String mailBody) throws MessagingException {
		
		try{
		MimeMultipart multipart = new MimeMultipart("related");
		
	    BodyPart messageBodyPart = new MimeBodyPart();
	    
	    messageBodyPart.setContent(mailBody, "text/html");
	    
	    multipart.addBodyPart(messageBodyPart);
	    
	    
	    if(filePaths.length!=0){
	    	for (String filePath : filePaths) {
	    		messageBodyPart = new MimeBodyPart();
		        DataSource source = new FileDataSource(filePath);
		        messageBodyPart.setDataHandler(new DataHandler(source));
		        messageBodyPart.setFileName(filePath.subSequence(filePath.lastIndexOf("/")+1, filePath.length()).toString());
		        multipart.addBodyPart(messageBodyPart);
			}
	    }
	    message.setContent(multipart);
	    message.addHeader("X-Priority", "1");
		}
		catch(Exception e){System.out.println("Sending Initial Job Trigger Email Failed");e.printStackTrace();}
	    return message;
	}

}
