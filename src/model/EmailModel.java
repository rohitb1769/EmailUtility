package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EmailModel {
	private String to;
	private String cc;
	private String subject;
	private String message;
	private String attachment;

	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
}
