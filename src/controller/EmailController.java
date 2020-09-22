package controller;

import javax.ws.rs.FormParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import model.EmailModel;
import service.EmailService;
import service.EmailServiceImpl;
import status.Status;

public class EmailController {
	
	@PostMapping("/rest/email")
	public Status sendDirectMail(@FormParam("to") String to, @FormParam("subject") String subject,
			@FormParam("message") String message)
	{
		EmailService service = new EmailServiceImpl();
		EmailModel emailModel = new EmailModel();
		emailModel.setTo(to);
		emailModel.setSubject(subject);
		emailModel.setMessage(message);
		return service.sendMail(emailModel);
	}
	
	@PostMapping("/rest/sendsimpleemail")
	public Status sendSimpleMail(@FormParam("to") String to, @FormParam("cc") String cc, 
			 @FormParam("subject") String subject, @FormParam("message") String message) 
	{
		EmailService service = new EmailServiceImpl();
		EmailModel emailModel = new EmailModel();
		emailModel.setTo(to);
		emailModel.setCc(cc);
		emailModel.setSubject(subject);
		emailModel.setMessage(message);
		return service.sendMail(emailModel);
	}
	
	@PostMapping("/rest/sendbigemail")
	public Status sendBigMail(@FormParam("to") String to, @FormParam("cc") String cc, 
			@FormParam("subject") String subject, @FormParam("msgPath") String msgFilePath) 
	{
		EmailService service = new EmailServiceImpl();
		EmailModel emailModel = new EmailModel();
		emailModel.setTo(to);
		emailModel.setCc(cc);
		emailModel.setSubject(subject);
		return service.sendMail(emailModel,msgFilePath);
	}
	
	@PostMapping("/rest/sendattachemail")
	public Status sendAttachMail(@FormParam("to") String to, @FormParam("cc") String cc, 
			@FormParam("subject") String subject,  @FormParam("message") String message,
			@FormParam("attachment") String attachment) 
	{
		EmailService service = new EmailServiceImpl();
		EmailModel emailModel = new EmailModel();
		emailModel.setTo(to);
		emailModel.setCc(cc);
		emailModel.setSubject(subject);
		emailModel.setMessage(message);
		emailModel.setAttachment(attachment);
		return service.sendMail(emailModel);
	}
	
	@PostMapping("/rest/senddirectemail")	
	public Status sendAttachMail(@RequestBody EmailModel emailModel) 
	{
		EmailService service = new EmailServiceImpl();
		return service.sendMail(emailModel);
	}
	
}
