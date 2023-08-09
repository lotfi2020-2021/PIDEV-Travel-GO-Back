package tn.esprit.TRAVELGO.service.dto;


public class NotificationDto {
    private Long messageId;
    private String senderName;
	public Long getMessageId() {
		return messageId;
	}
	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public NotificationDto(Long messageId, String senderName) {
		super();
		this.messageId = messageId;
		this.senderName = senderName;
	}
    
    
    
	public NotificationDto() {
		super();
		
	}
    
}