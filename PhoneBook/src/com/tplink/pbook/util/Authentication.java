package com.tplink.pbook.util;

import com.tplink.pbook.bean.UserBean;

public interface Authentication {
	public boolean authenticate(UserBean userBean);
	public boolean changeLoginStatus(UserBean userBean, int loginStatus);
	public boolean logout(String emailid);
}
