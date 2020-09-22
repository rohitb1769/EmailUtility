package service;

import model.EmailModel;
import status.Status;

public interface EmailService {

	Status sendMail(EmailModel emailModel);

	Status sendMail(EmailModel emailModel, String msgFilePath);
}
