package status;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Status {
	
	private boolean isSuccess = false;
	private String status;

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
