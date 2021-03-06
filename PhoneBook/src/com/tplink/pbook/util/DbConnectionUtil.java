package com.tplink.pbook.util;

import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;

public class DbConnectionUtil {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URl = "jdbc:mysql://localhost:3306/pbookdb";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "admin";
	private static Connection con = null;

	public static Connection getDBConnection() {
		try {
			Class.forName(DRIVER);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (con == null) {
				con = (Connection) DriverManager.getConnection(URl, USERNAME, PASSWORD);
				System.out.println("connected........");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;

	}

	public static void closeDBConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
