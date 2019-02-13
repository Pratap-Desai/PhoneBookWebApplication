package com.tplink.pbook.dao;

import com.tplink.pbook.bean.UserBean;


/**
 * <h1>Phone Book!</h1>
 * <h3>Interface Verify User Dao</h3>
 * The Data Access Object  Interface for Verifying a User.
 * @author Pratap
 *
 */
public interface VerifyUserDao {
	public boolean userVerification(UserBean userBean);
	public boolean emailAssistance(String email);
	public boolean verifyEnteredCode(String email,String verificationCode);
}
