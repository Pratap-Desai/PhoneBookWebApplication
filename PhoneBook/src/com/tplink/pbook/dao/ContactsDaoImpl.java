package com.tplink.pbook.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.tplink.pbook.bean.ContactBean;
import com.tplink.pbook.bean.GroupBean;
import com.tplink.pbook.util.DbConnectionUtil;

/**
 * <h1>Phone Book!</h1>
 * <h3>class Contacts Dao Impl</h3> The Data Access Object class with
 * implementations for adding and viewing Contacts.
 * 
 * @author Pratap
 *
 */
public class ContactsDaoImpl implements ContactsDao {
	private Connection con;

	public ContactsDaoImpl() {

		con = DbConnectionUtil.getDBConnection();

	}

	public boolean addANewContact(ContactBean contactBean) {
		PreparedStatement ps;

		try {
			contactBean.setContactId(generateContactId());
			if (contactBean.getConDob() != null) {
				contactBean.setContactDob(dateToSql(contactBean.getConDob()));

			}
			ps = (PreparedStatement) con.prepareStatement("INSERT INTO pbook_contacts_table VALUES (?,?,?,?,?,?,?,?)");
			ps.setString(1, contactBean.getContactId());
			ps.setString(2, contactBean.getNumPrimary());
			ps.setString(3, contactBean.getNumSecondary());
			ps.setString(4, contactBean.getNumAdditional());
			ps.setString(5, contactBean.getConEmail());
			ps.setDate(6, contactBean.getContactDob());
			;
			ps.setString(7, contactBean.getUserId());
			ps.setString(8, contactBean.getConName());
			int rs1 = ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			System.out.println("exception" + e.getStackTrace());
			return false;

		}

	}

	public boolean deletePerticularContact(String contactId, String userId) {
		PreparedStatement ps;
		PreparedStatement ps1;
		PreparedStatement ps2;
		try {

			ps = (PreparedStatement) con.prepareStatement("DELETE FROM pbook_contacts_table WHERE (contact_id=?)");
			ps.setString(1, contactId);
			int rs1 = ps.executeUpdate();
			ps1 = (PreparedStatement) con.prepareStatement(
					"select map_id from pbook_contact_grp_mapping_table where contact_id=? and user_id=?");
			ps1.setString(1, contactId);
			ps1.setString(2, userId);
			ResultSet rs = ps1.executeQuery();
			while (rs.next()) {
				ps2 = (PreparedStatement) con
						.prepareStatement("DELETE FROM pbook_contact_grp_mapping_table WHERE (map_id=?)");
				ps2.setString(1, rs.getString(1));
				int rs12 = ps2.executeUpdate();
			}
			return true;

		} catch (SQLException e) {
			System.out.println("exception" + e.getStackTrace());
			return false;

		}

	}

	public JSONArray allUserContacts(String userId) {
		PreparedStatement ps;
		JSONObject contactObj;
		JSONArray contactArray = new JSONArray();

		try {
			ps = (PreparedStatement) con.prepareStatement("SELECT * from pbook_contacts_table where user_Id=?");
			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				contactObj = new JSONObject();
				contactObj.put("contact_id", rs.getString(1));
				contactObj.put("contact_name", rs.getString(8));
				contactObj.put("ph_num_primary", rs.getString(2));
				contactObj.put("ph_num_secondary", rs.getString(3));
				contactObj.put("ph_num_additional", rs.getString(4));
				contactObj.put("contact_email", rs.getString(5));
				String val = sqlDateToStr(rs.getDate(6));
				contactObj.put("contact_dob", val);
				contactArray.put(contactObj);

			}
		} catch (SQLException e) {
			contactObj = new JSONObject();
			contactObj.put("ERROR", "ERROR");
			contactArray.put(contactObj);
			e.printStackTrace();
		}
		return contactArray;

	}

	private String generateContactId() {
		return UUID.randomUUID().toString();
	}

	private Date dateToSql(String dob) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date;
		java.sql.Date sqlDate = null;
		try {
			date = sdf1.parse(dob);
			sqlDate = new java.sql.Date(date.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sqlDate;

	}

	private String sqlDateToStr(java.sql.Date sqlDate) {
		if (sqlDate != null) {
			return sqlDate.toString();
		}
		return null;

	}

	public JSONArray getAllAddedGroups(ContactBean contactBean) {
		// TODO Auto-generated method stub

		PreparedStatement ps;
		JSONObject contactObj;
		JSONArray contactArray = new JSONArray();

		try {
			ps = (PreparedStatement) con.prepareStatement(
					"SELECT group_id from pbook_contact_grp_mapping_table where user_Id=? and contact_id=?");
			ps.setString(1, contactBean.getUserId());
			ps.setString(2, contactBean.getContactId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				contactObj = new JSONObject();
				contactObj.put("group_id", rs.getString(1));
				contactArray.put(contactObj);

			}
		} catch (SQLException e) {
			contactObj = new JSONObject();
			contactObj.put("ERROR", "ERROR");
			contactArray.put(contactObj);
			e.printStackTrace();
		}
		return contactArray;

	}

	public boolean addGrpsToContact(String contactgrpsPairs, String userId) {

		ArrayList<String> groups = new ArrayList<String>();
		String contact = "";
		int len = 2;
		boolean updatedContact = false;
		String[] splited = contactgrpsPairs.split(",");

		for (String s : splited) {
			if (s.contains("groupId")) {
				String sp[] = s.split(":");
				groups.add(sp[1].substring(1, sp[1].length() - 1));
			} else {
				if (!updatedContact) {
					if (s.contains("contactId")) {
						String sp[] = s.split(":");
						if (splited.length == 2) {
							len = 3;
						}
						contact = sp[1].substring(1, sp[1].length() - len);
						updatedContact = true;
					}
				}
			}

		}

		PreparedStatement ps2;

		try {
			ps2 = (PreparedStatement) con.prepareStatement(
					"select map_id from pbook_contact_grp_mapping_table where contact_id=? and user_id=?");
			ps2.setString(1, contact);
			ps2.setString(2, userId);
			ResultSet rs = ps2.executeQuery();
			while (rs.next()) {
				PreparedStatement ps3;
				ps3 = (PreparedStatement) con
						.prepareStatement("DELETE FROM pbook_contact_grp_mapping_table WHERE (map_id=?)");
				ps3.setString(1, rs.getString(1));
				int res1 = ps3.executeUpdate();

			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		for (String grpid : groups) {
			PreparedStatement ps;
			PreparedStatement ps1;
			try {
				String mapId = generateContactId();
				ps = (PreparedStatement) con
						.prepareStatement("INSERT INTO pbook_contact_grp_mapping_table VALUES (?,?,?,?)");
				ps.setString(1, mapId);
				ps.setString(2, contact);
				ps.setString(3, grpid);
				ps.setString(4, userId);
				int rs1 = ps.executeUpdate();

			} catch (SQLException e) {
				System.out.println("exception" + e.getStackTrace());
				return false;

			}

		}

		return true;
	}

	public JSONArray getAllContactsinGroup(GroupBean groupBean) {
		PreparedStatement ps;
		JSONObject contactObj;
		JSONArray contactArray = new JSONArray();
		List<String> con_ids = new ArrayList<String>();

		try {
			ps = (PreparedStatement) con.prepareStatement(
					"SELECT contact_id from pbook_contact_grp_mapping_table where user_id=? and group_id=?");
			ps.setString(1, groupBean.getUserId());
			ps.setString(2, groupBean.getGroupId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				con_ids.add(rs.getString(1));
			}
			for (String conid : con_ids) {
				PreparedStatement ps1;
				ps1 = (PreparedStatement) con.prepareStatement(
						"SELECT contact_name,ph_num_primary from pbook_contacts_table where contact_id=?");
				ps1.setString(1, conid);
				ResultSet rs1 = ps1.executeQuery();

				if (rs1.next()) {
					contactObj = new JSONObject();
					contactObj.put("contact_name", rs1.getString(1));
					contactObj.put("ph_num_primary", rs1.getString(2));
					contactArray.put(contactObj);
				}

			}
		} catch (SQLException e) {
			contactObj = new JSONObject();
			contactObj.put("ERROR", "ERROR");
			contactArray.put(contactObj);
			e.printStackTrace();
		}
		return contactArray;

	}
}
