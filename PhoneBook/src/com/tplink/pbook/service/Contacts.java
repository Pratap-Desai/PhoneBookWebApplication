package com.tplink.pbook.service;

import org.json.JSONArray;

import com.tplink.pbook.bean.ContactBean;
import com.tplink.pbook.bean.GroupBean;
import com.tplink.pbook.dao.ContactsDaoImpl;

/**
 * <h1>Phone Book!</h1>
 * <h3>class Contacts Impl</h3> The service class to serve Contacts related
 * services.
 * 
 * @author Pratap
 *
 */
public class Contacts {

	ContactsDaoImpl contactsDaoImpl;

	public boolean addAContact(ContactBean contactBean) {
		contactsDaoImpl = new ContactsDaoImpl();
		return contactsDaoImpl.addANewContact(contactBean);

	}

	public JSONArray getAllContacts(String userId) {
		contactsDaoImpl = new ContactsDaoImpl();
		return contactsDaoImpl.allUserContacts(userId);
	}

	public boolean deleteAcontact(String contactId, String userId) {
		contactsDaoImpl = new ContactsDaoImpl();
		return contactsDaoImpl.deletePerticularContact(contactId, userId);

	}

	public JSONArray getAddedgrps(ContactBean contactBean) {
		contactsDaoImpl = new ContactsDaoImpl();
		return contactsDaoImpl.getAllAddedGroups(contactBean);

	}

	public boolean addGroupsUnderContact(String contactgrpsPairs, String userId) {
		contactsDaoImpl = new ContactsDaoImpl();
		return contactsDaoImpl.addGrpsToContact(contactgrpsPairs, userId);

	}

	public JSONArray getcontactsinGroup(GroupBean groupBean) {
		// TODO Auto-generated method stub
		contactsDaoImpl = new ContactsDaoImpl();
		return contactsDaoImpl.getAllContactsinGroup(groupBean);
	}
}
