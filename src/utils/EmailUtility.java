package utils;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import model.EmailModel;
import status.Status;

public class EmailUtility {

	//TODO: use properties file or config values to get those smtp values and also encrypt the pswd for security.
	public static Status sendEmail(EmailModel emailModel) 
	{
		Status status = new Status();
		String statusMsg = "Message sent successfully";
        String from = ""; // TODO: from email address
        String host = ""; // TODO: smtp details
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        try {
        	Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() 
	            {
	            	// TODO: need to encrypt the pswd for the email
	                return new PasswordAuthentication(from, "");
	            }
        	});
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.addRecipients(Message.RecipientType.TO, emailModel.getTo());
            mimeMessage.addRecipients(Message.RecipientType.CC, emailModel.getCc());
            mimeMessage.setSubject(emailModel.getSubject());
            Multipart multipart = new MimeMultipart();
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(emailModel.getMessage());
            if(StringUtils.isNotBlank(emailModel.getAttachment()))
            {
            	messageBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(emailModel.getAttachment());
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(FilenameUtils.getName(emailModel.getAttachment()));
            }
            multipart.addBodyPart(messageBodyPart);
            mimeMessage.setContent(multipart);
            Transport.send(mimeMessage);
            status.setSuccess(true);
        } catch (MessagingException mex) {
            mex.printStackTrace();
            statusMsg = "Could not send mail due to :\n" + mex.getMessage();
        } catch (Exception e) {
        	statusMsg = "Could not send mail due to :\n" + e.getMessage();
		}
        status.setStatus(statusMsg);
        return status;
	}
}
