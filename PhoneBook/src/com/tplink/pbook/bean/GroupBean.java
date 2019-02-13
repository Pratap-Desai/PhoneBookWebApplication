package com.tplink.pbook.bean;
/**
 * <h1>Phone Book!</h1>
 * <h3>Group Bean</h3>
 * The Group Bean is a Bean class to add user Group.
 * @author Pratap
 *
 */

public class GroupBean {

	private String groupId;
	private String groupName;
	private String userId;
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
