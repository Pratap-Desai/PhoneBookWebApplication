package com.tplink.pbook.dao;

import java.sql.ResultSet;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.SQLException;
import java.util.Random;
import java.util.UUID;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.tplink.pbook.bean.UserBean;
import com.tplink.pbook.util.DbConnectionUtil;

/**
 * <h1>Phone Book!</h1>
 * <h3>class Verify User Dao</h3> The Data Access Object class with
 * implementations for Verifying a User.
 * 
 * @author Pratap
 *
 */
public class VerifyUserDaoImpl implements VerifyUserDao {
	public static final String AllCAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String AllNUMS = "1234567890";
	public static final String AllSMALL = "abcdefghijklmnopqrstuvwxyz";
	private static final int VERIFICATIONCODELENGTH = 6;
	private Connection con;

	public VerifyUserDaoImpl() {

		con = DbConnectionUtil.getDBConnection();

	}

	@Override
	public boolean userVerification(UserBean userBean) {
		// TODO Auto-generated method stub

		PreparedStatement ps;
		String pass = "";
		int loginstatus = 0;
		try {
			if (!verifyUniqueEmail(userBean.getEmailId())) {
				System.out.println("not an Unique ID");
				return false;
			} else {

				userBean.setUserId(generateUserId());
				userBean.setVarificationCode(generateVerificationCode());
				ps = (PreparedStatement) con.prepareStatement("INSERT INTO user_pbook_table VALUES (?,?,?,?,?,?,?)");
				ps.setString(1, userBean.getUserId());
				ps.setString(2, userBean.getUserName());
				ps.setString(3, userBean.getuserPassword());
				ps.setString(4, userBean.getEmailId());
				ps.setString(5, userBean.getVarificationCode());
				ps.setInt(6, 0);
				ps.setInt(7, 0);
				int rs1 = ps.executeUpdate();
				return true;
			}
		} catch (SQLException e) {
			System.out.println("exception" + e.getStackTrace());
			return false;

		}

	}

	private boolean verifyUniqueEmail(String emailId) {
		PreparedStatement ps;

		try {
			ps = (PreparedStatement) con.prepareStatement("SELECT * from user_pbook_table where email=?");
			ps.setString(1, emailId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return false;
			}
		} catch (SQLException e) {
			return false;
		}

		return true;

	}

	private String generateUserId() {
		return UUID.randomUUID().toString();
	}

	public String generateVerificationCode() {

		String allChoices = AllCAPS + AllNUMS + AllSMALL + AllNUMS;
		int choiselen = allChoices.length();
		Random num = new Random();
		char[] code = new char[VERIFICATIONCODELENGTH];
		for (int i = 0; i < VERIFICATIONCODELENGTH; i++) {
			code[i] = allChoices.charAt(num.nextInt(choiselen));
		}
		return new String(code);

	}

	@Override
	public boolean emailAssistance(String email) {
		// TODO Auto-generated method stub
		PreparedStatement ps;
		String userid;
		String verificationCode;
		try {
			ps = (PreparedStatement) con.prepareStatement("SELECT * from user_pbook_table where email=?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				userid = rs.getString(1);
				verificationCode = generateVerificationCode();
				System.out.println("veri code= " + verificationCode);
				ps = (PreparedStatement) con
						.prepareStatement("update user_pbook_table set verification_code=? where iduser=?");
				ps.setString(1, verificationCode);
				ps.setString(2, userid);
				int rs1 = ps.executeUpdate();

				// send mail
				SendEmail(email, " Verification Code To Reset Password ",
						"Here is Your varification Code :  " + verificationCode);
			} else {
				return false;
			}
		} catch (SQLException e) {

			System.out.println("error " + e.getMessage());

			return false;
		}

		return true;
	}

	@Override
	public boolean verifyEnteredCode(String email, String verificationCode) {
		// TODO Auto-generated method stub

		PreparedStatement ps;
		String vCode;
		try {
			ps = (PreparedStatement) con
					.prepareStatement("SELECT verification_code from user_pbook_table where email=?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				vCode = rs.getString(1);
				if (vCode.equals(verificationCode))
					return true;

			} else {
				return false;
			}
		} catch (SQLException e) {
			return false;
		}

		return false;
	}

	public void SendEmail(String DestinationEmailID, String Subject, String Message_tosend) {
		final String username = "email.test.kasa@gmail.com";
		final String password = "sjsu.edu123@";

		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("email.test.kasa@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(DestinationEmailID));
			message.setSubject(Subject);
			message.setText(Message_tosend);

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
