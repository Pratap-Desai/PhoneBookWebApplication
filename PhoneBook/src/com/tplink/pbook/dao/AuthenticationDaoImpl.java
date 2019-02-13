package com.tplink.pbook.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.tplink.pbook.bean.UserBean;
import com.tplink.pbook.util.DbConnectionUtil;

/**
 * <h1>Phone Book!</h1>
 * <h3>class Authentication Dao Impl</h3> The Data Access Object class with
 * implementations for Authentication.
 * 
 * @author Pratap
 *
 */

public class AuthenticationDaoImpl implements AuthenticationDao {
	private Connection con;

	public AuthenticationDaoImpl() {

		con = DbConnectionUtil.getDBConnection();

	}

	@Override
	public boolean authenticateUser(UserBean userBean) {
		// TODO Auto-generated method stub

		PreparedStatement ps;
		String pass = "";
		int loginstatus = 0;
		try {
			ps = (PreparedStatement) con.prepareStatement("SELECT * from user_pbook_table where email=?");
			ps.setString(1, userBean.getEmailId());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				pass = rs.getString(3);
				loginstatus = rs.getInt(7);

				if (pass.equals(userBean.getuserPassword())) {
					userBean.setUserId(rs.getString(1));
					if (updateLoginStatus(userBean, loginstatus)) {

						return true;
					}
					return false;

				}
			} else {

				return false;
			}
		} catch (SQLException e) {
			return false;
		}
		return false;

	}

	@Override
	public boolean updateLoginStatus(UserBean userBean, int loginStatus) {
		// TODO Auto-generated method stub

		PreparedStatement ps;

		if (loginStatus == 0) {
			try {
				ps = (PreparedStatement) con
						.prepareStatement("Update user_pbook_table set loginstatus=1 where iduser=?");
				ps.setString(1, userBean.getUserId());
				int rs = ps.executeUpdate();
				userBean.setLoginStatus(true);
				return true;
			} catch (SQLException e) {
				return false;
			}
		} else if (loginStatus == 1) {
			System.out.println("Already logged in");
			return true;
		}
		return false;
	}

	@Override
	public boolean logoutuser(String userid) {
		PreparedStatement ps;

		try {
			System.out.println("log");
			ps = (PreparedStatement) con.prepareStatement("Update user_pbook_table set loginstatus=0 where iduser=?");
			ps.setString(1, userid);
			ResultSet rs = ps.executeQuery();
			return true;
		} catch (SQLException e) {
			return false;
		}

	}
}
