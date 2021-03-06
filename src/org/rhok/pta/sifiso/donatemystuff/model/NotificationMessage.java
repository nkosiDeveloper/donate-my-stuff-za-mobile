package org.rhok.pta.sifiso.donatemystuff.model;
/**
 * Notification to be sent via Google Cloud Messaging.
 * The sending application packages the notification which is to be processed by the GCM server for push notification
 * 
 * @author Ishmael Makitla
 *         GDG/RHoK Pretoria
 *         2013
 *         South Africa
 *        
 *
 */
public class NotificationMessage {
	//sender should always be the Project-API-ID
	private String sender;
	//content of the notification
	private String content;
	//array of users to send the notification to (1 - inf)
	private String[] recipients;
	
	public NotificationMessage(){
		//
	}

	public NotificationMessage(String sender, String message,String[] recipients) {

		this.sender = sender;
		this.content = message;
		this.recipients = recipients;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getMessage() {
		return content;
	}

	public void setMessage(String message) {
		this.content = message;
	}

	public String[] getRecipients() {
		return recipients;
	}

	public void setRecipients(String[] recipients) {
		this.recipients = recipients;
	}

}
