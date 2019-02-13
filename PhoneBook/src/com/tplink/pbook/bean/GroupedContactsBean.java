package com.tplink.pbook.bean;


/**
 * <h1>Phone Book!</h1>
 * <h3>Grouped Contacts Bean</h3>
 * The Grouped Contacts Bean is a Bean class to map Contacts and Group.
 * @author Pratap
 *
 */
public class GroupedContactsBean {

	private String groupId;
	private String contactId;
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

}
