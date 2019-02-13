package com.tplink.pbook.service;

import com.tplink.pbook.bean.UserBean;
import com.tplink.pbook.dao.VerifyUserDaoImpl;
import com.tplink.pbook.util.VerifyUser;

/**
 * <h1>Phone Book!</h1>
 * <h3>class VerifyUser Impl</h3> The service class to Verify User.
 * 
 * @author Pratap
 *
 */
public class VerifyUserImpl implements VerifyUser {
	private VerifyUserDaoImpl verifyUserDaoImpl;

	public VerifyUserImpl() {
		verifyUserDaoImpl = new VerifyUserDaoImpl();
	}

	@Override
	public boolean validateUser(UserBean userBean) {
		// TODO Auto-generated method stub
		return verifyUserDaoImpl.userVerification(userBean);
	}

	@Override
	public boolean generateSendVerificationCode(String email) {
		// TODO Auto-generated method stub

		return verifyUserDaoImpl.emailAssistance(email);
	}

	@Override
	public boolean verifyCode(String email, String verificationCode) {
		// TODO Auto-generated method stub
		return verifyUserDaoImpl.verifyEnteredCode(email, verificationCode);
	}

}
