package com.tplink.pbook.dao;

import com.tplink.pbook.bean.UserBean;

/**
 * <h1>Phone Book!</h1>
 * <h3>Interface Authentication Dao</h3>
 * The Data Access Object  Interface class for Authentication.
 * @author Pratap
 *
 */

public interface AuthenticationDao {
	public boolean authenticateUser(UserBean userBean);
	public boolean updateLoginStatus(UserBean userBean, int loginStatus);
	public boolean logoutuser(String emailid);
}
