package com.tplink.pbook.bean;

/**
 * <h1>Phone Book!</h1>
 * <h3>User Bean</h3>
 * The User Bean is a Bean class to add user Users.
 * @author Pratap
 *
 */

public class UserBean {
	private String userId;
	private String userName;
	private String userPassword;
	private String emailId;
	private String varificationCode;
	private boolean verified;
	private boolean loginStatus;
	private String userPasswordAgain;
	
	
	public boolean isLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getVarificationCode() {
		return varificationCode;
	}

	public void setVarificationCode(String varificationCode) {
		this.varificationCode = varificationCode;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getuserPassword() {
		return userPassword;
	}

	public void setuserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPasswordAgain() {
		return userPasswordAgain;
	}

	public void setUserPasswordAgain(String userPasswordAgain) {
		this.userPasswordAgain = userPasswordAgain;
	}
}