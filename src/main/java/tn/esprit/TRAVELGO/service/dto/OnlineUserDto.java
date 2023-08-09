package tn.esprit.TRAVELGO.service.dto;



public class OnlineUserDto {
    private Long userId;
    private String sessionId;
    private String username;
    private Integer noOfNewMessages;
    private String status;

    @Override
    public int hashCode() {
        int prime = 31;
        return prime+ ((userId==null)?0:prime+userId.hashCode());
    }
    
    

    public OnlineUserDto() {
		super();
	}



	public OnlineUserDto(Long userId, String sessionId, String username, Integer noOfNewMessages, String status) {
		super();
		this.userId = userId;
		this.sessionId = sessionId;
		this.username = username;
		this.noOfNewMessages = noOfNewMessages;
		this.status = status;
	}



	public Long getUserId() {
		return userId;
	}



	public void setUserId(Long userId) {
		this.userId = userId;
	}



	public String getSessionId() {
		return sessionId;
	}



	public void setSessionId(String sessionId2) {
		this.sessionId = sessionId2;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public Integer getNoOfNewMessages() {
		return noOfNewMessages;
	}



	public void setNoOfNewMessages(Integer noOfNewMessages) {
		this.noOfNewMessages = noOfNewMessages;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	@Override
    public boolean equals(Object obj) {
        if(obj==null){
            return false;
        }
        OnlineUserDto user = (OnlineUserDto) obj;
        if(!this.userId.equals(((OnlineUserDto) obj).getUserId())){
            return false;
        }
        else if(!this.sessionId.equals(((OnlineUserDto) obj).sessionId)){
            return false;
        }
        return true;
    }
}
