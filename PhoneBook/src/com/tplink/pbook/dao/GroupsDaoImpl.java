package com.tplink.pbook.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.tplink.pbook.bean.GroupBean;
import com.tplink.pbook.bean.GroupedContactsBean;
import com.tplink.pbook.util.DbConnectionUtil;

public class GroupsDaoImpl {
	private Connection con;

	public GroupsDaoImpl() {

		con = DbConnectionUtil.getDBConnection();

	}

	public boolean addANewGroups(GroupBean groupBean) {
		PreparedStatement ps;

		try {
			groupBean.setGroupId(generateGroupId());
			ps = (PreparedStatement) con.prepareStatement("INSERT INTO pbook_groups_table VALUES (?,?,?)");
			ps.setString(1, groupBean.getGroupId());
			ps.setString(2, groupBean.getGroupName());
			ps.setString(3, groupBean.getUserId());
			int rs1 = ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			System.out.println("exception" + e.getStackTrace());
			return false;

		}

	}

	private String generateGroupId() {
		return UUID.randomUUID().toString();
	}

	public JSONArray allUserGroups(String userId) {
		// TODO Auto-generated method stub
		PreparedStatement ps;
		JSONObject grpObj;
		JSONArray grpArray = new JSONArray();

		try {
			ps = (PreparedStatement) con.prepareStatement("SELECT * from pbook_groups_table where user_id=?");
			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				grpObj = new JSONObject();
				grpObj.put("group_id", rs.getString(1));
				grpObj.put("group_name", rs.getString(2));
				grpObj.put("user_id", rs.getString(3));
				grpArray.put(grpObj);

			}
		} catch (SQLException e) {
			grpObj = new JSONObject();
			grpObj.put("ERROR", "ERROR");
			grpArray.put(grpObj);
			e.printStackTrace();
		}
		return grpArray;

	}

	public boolean deletePerticularGroups(String groupsId, String userId) {

		PreparedStatement ps;
		PreparedStatement ps1;
		PreparedStatement ps2;
		try {

			ps = (PreparedStatement) con.prepareStatement("DELETE FROM pbook_groups_table WHERE (group_id=?)");
			ps.setString(1, groupsId);
			int rs1 = ps.executeUpdate();
			ps1 = (PreparedStatement) con.prepareStatement(
					"select map_id from pbook_contact_grp_mapping_table where group_id=? and user_id=?");
			ps1.setString(1, groupsId);
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

	public boolean addAcontactToGroup(GroupedContactsBean groupedContactsBean) {
		PreparedStatement ps;
		PreparedStatement ps1;
		try {
			ps1 = (PreparedStatement) con.prepareStatement(
					"select * from pbook_contact_grp_mapping_table where contact_id=? and group_id=?");
			ps1.setString(1, groupedContactsBean.getContactId());
			ps1.setString(2, groupedContactsBean.getGroupId());
			ResultSet rs = ps1.executeQuery();
			if (!rs.next()) {
				String mapId = generateGroupId();
				ps = (PreparedStatement) con
						.prepareStatement("INSERT INTO pbook_contact_grp_mapping_table VALUES (?,?,?,?)");
				ps.setString(1, mapId);
				ps.setString(2, groupedContactsBean.getContactId());
				ps.setString(3, groupedContactsBean.getGroupId());
				ps.setString(4, groupedContactsBean.getUserId());
				int rs1 = ps.executeUpdate();
				return true;
			}
			return false;
		} catch (SQLException e) {
			System.out.println("exception" + e.getStackTrace());
			return false;

		}

	}

}
