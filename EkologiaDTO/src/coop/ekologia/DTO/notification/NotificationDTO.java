package coop.ekologia.DTO.notification;

public class NotificationDTO {

	private Integer id;
	private String message;
	private String acceptLabel;
	private String rejectLabel;
	private Boolean isValide;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAcceptLabel() {
		return acceptLabel;
	}

	public void setAcceptLabel(String acceptLabel) {
		this.acceptLabel = acceptLabel;
	}

	public String getRejectLabel() {
		return rejectLabel;
	}

	public void setRejectLabel(String rejectLabel) {
		this.rejectLabel = rejectLabel;
	}

	public Boolean getIsValide() {
		return isValide;
	}

	public void setIsValide(Boolean isValide) {
		this.isValide = isValide;
	}

}
