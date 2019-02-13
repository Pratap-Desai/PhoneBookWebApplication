package com.tplink.pbook.service;


import com.tplink.pbook.bean.UserBean;
import com.tplink.pbook.dao.AuthenticationDaoImpl;
import com.tplink.pbook.util.Authentication;

/**
 * <h1>Phone Book!</h1>
 * <h3>class Authentication Impl</h3>
 * The service  class to serve Authentication of user.
 * @author Pratap
 *
 */

public class AuthenticationImpl implements Authentication {
	
	private AuthenticationDaoImpl authenticationImplDao;
	
	public AuthenticationImpl() {
		
		authenticationImplDao=new AuthenticationDaoImpl();
		
	}

	@Override
	public boolean authenticate(UserBean userBean) {
		// TODO Auto-generated method stub
		
		
		/*
		 * System.out.println("in auth"); System.out.println(userBean.getEmailId());
		 * PreparedStatement ps; String pass=""; int loginstatus=0; try { ps =
		 * (PreparedStatement)
		 * con.prepareStatement("SELECT * from user_pbook_table where email=?");
		 * ps.setString(1, userBean.getEmailId()); ResultSet rs=ps.executeQuery();
		 * if(rs.next()){ pass=rs.getString(3); System.out.println("inside rs"+pass);
		 * loginstatus=rs.getInt(7);
		 * 
		 * System.out.println("inside rs"+loginstatus); if
		 * (pass.equals(userBean.getuserPassword())) {
		 * System.out.println("inside pass"+rs.getString(1));
		 * userBean.setUserId(rs.getString(1)); if(changeLoginStatus(userBean,
		 * loginstatus)){
		 * 
		 * return true; } return false;
		 * 
		 * } } else{
		 * 
		 * return false; } } catch (SQLException e) { return false; } return false;
		 */		
		
		return authenticationImplDao.authenticateUser(userBean);
		
	}


	@Override
	public boolean changeLoginStatus(UserBean userBean, int loginStatus) {
		// TODO Auto-generated method stub
		
		/*
		 * PreparedStatement ps;
		 * 
		 * 
		 * 
		 * if(loginStatus==0){ try { ps = (PreparedStatement) con.
		 * prepareStatement("Update user_pbook_table set loginstatus=1 where iduser=?"
		 * ); ps.setString(1, userBean.getUserId()); ResultSet rs=ps.executeQuery();
		 * userBean.setLoginStatus(true); return true; } catch (SQLException e) { return
		 * false; } } else if(loginStatus==1) { System.out.println("Already logged in");
		 * return true; } return false;
		 */
		
		return authenticationImplDao.updateLoginStatus(userBean, loginStatus);
	}
	@Override
	public boolean logout(String userid) {
		
		return authenticationImplDao.logoutuser(userid);
}
}