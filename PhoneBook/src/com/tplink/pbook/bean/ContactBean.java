package com.tplink.pbook.bean;

import java.sql.Date;

/**
 * <h1>Phone Book!</h1>
 * <h3>Contact Bean</h3>
 * The Contact Bean is a Bean class to add user Contacts.
 * @author Pratap
 *
 */
public class ContactBean {

	private String contactId;
	private String conName;
	private String numPrimary;
	private String numSecondary;
	private String numAdditional;
	private String conEmail;
	private String conDob;
	private String userId;
	private Date contactDob;
		
	
	
	
	public Date getContactDob() {
		return contactDob;
	}
	public void setContactDob(Date contactDob) {
		this.contactDob = contactDob;
	}
	public String getContactId() {
		return contactId;
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	public String getConName() {
		return conName;
	}
	public void setConName(String conName) {
		this.conName = conName;
	}
	public String getNumPrimary() {
		return numPrimary;
	}
	public void setNumPrimary(String numPrimary) {
		this.numPrimary = numPrimary;
	}
	public String getNumSecondary() {
		return numSecondary;
	}
	public void setNumSecondary(String numSecondary) {
		this.numSecondary = numSecondary;
	}
	public String getNumAdditional() {
		return numAdditional;
	}
	public void setNumAdditional(String numAdditional) {
		this.numAdditional = numAdditional;
	}
	public String getConEmail() {
		return conEmail;
	}
	public void setConEmail(String conEmail) {
		this.conEmail = conEmail;
	}
	public String getConDob() {
		return conDob;
	}
	public void setConDob(String conDob) {
		this.conDob = conDob;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
