package com.tplink.pbook.dao;

import org.json.JSONArray;

import com.tplink.pbook.bean.ContactBean;


/**
 * <h1>Phone Book!</h1>
 * <h3>Interface Contacts Dao</h3>
 * The Data Access Object  Interface for adding and viewing Contacts.
 * @author Pratap
 *
 */
public interface ContactsDao {
	public boolean addANewContact(ContactBean contactBean);
	public	JSONArray allUserContacts(String userId);
}