package service;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import model.EmailModel;
import status.Status;
import utils.EmailUtility;

public class EmailServiceImpl implements EmailService {

	@Override
	public Status sendMail(EmailModel emailModel) {
		return EmailUtility.sendEmail(emailModel);
	}

	@Override
	public Status sendMail(EmailModel emailModel, String msgFilePath) {
		Status status = new Status();
		String message = "";
		try {
			message = FileUtils.readFileToString(new File(msgFilePath));
			emailModel.setMessage(message);
			status = sendMail(emailModel);
		} catch (IOException e) {
			status.setStatus("Could not send message due to: \n" + e.getMessage());
		}
		return status;
	}

}
