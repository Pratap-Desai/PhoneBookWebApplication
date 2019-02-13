package com.tplink.pbook.controllers;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tplink.pbook.bean.ContactBean;
import com.tplink.pbook.bean.GroupBean;
import com.tplink.pbook.bean.GroupedContactsBean;
import com.tplink.pbook.service.Contacts;
import com.tplink.pbook.service.Groups;

import jdk.nashorn.internal.parser.JSONParser;

/**
 * <h1>Phone Book!</h1>
 * <h3>Controller Phone Book Page</h3> The PbookController is a Controller class
 * that controls events on pbook Page.
 * 
 * @author Pratap
 *
 */
@Controller
public class PbookController {

	private static final Logger LOGGER = Logger.getLogger(PbookController.class.getName());

	Contacts contact = new Contacts();
	Groups group = new Groups();

	@RequestMapping(value = "/groups", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String hometr(Model model, HttpServletResponse response) {

		LOGGER.info("groups Page Requested");

		JSONObject obj;
		JSONArray array = new JSONArray();
		for (int i = 0; i < 3; i++) {

			obj = new JSONObject();
			obj.put("id", "idUser");
			obj.put("email", "emailUser");
			obj.put("password", "motPassUser");
			array.put(obj);
		}

		return array.toString();

	}

	@RequestMapping(value = "/add_new_contact", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String addContact(@RequestBody ContactBean contactBean, HttpSession session,
			HttpServletResponse response, HttpServletRequest request) {
		JSONObject obj;
		JSONArray array = new JSONArray();
		contactBean.setUserId(session.getAttribute("uid").toString());
		if (contact.addAContact(contactBean)) {
			obj = new JSONObject();
			obj.put("result", "Success");
			array.put(obj);

		} else {
			obj = new JSONObject();
			obj.put("result", "Failed");
			array.put(obj);

		}
		return array.toString();

	}

	@RequestMapping(value = "/add_new_group", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String addGroup(@RequestBody GroupBean groupBean, HttpSession session,
			HttpServletResponse response, HttpServletRequest request) {
		JSONObject obj;
		JSONArray array = new JSONArray();
		groupBean.setUserId(session.getAttribute("uid").toString());
		if (group.addAGroups(groupBean)) {
			obj = new JSONObject();
			obj.put("result", "Success");
			array.put(obj);

		} else {
			obj = new JSONObject();
			obj.put("result", "Failed");
			array.put(obj);

		}
		return array.toString();

	}

	@RequestMapping(value = "/deleteContact", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String deleteAContact(@RequestBody ContactBean contactBean, HttpSession session,
			HttpServletResponse response, HttpServletRequest request) {
		JSONObject obj;
		JSONArray array = new JSONArray();
		contactBean.setUserId(session.getAttribute("uid").toString());
		if (contact.deleteAcontact(contactBean.getContactId(), session.getAttribute("uid").toString())) {
			obj = new JSONObject();
			obj.put("result", "Success");
			array.put(obj);

		} else {
			obj = new JSONObject();
			obj.put("result", "Failed");
			array.put(obj);

		}
		return array.toString();

	}

	@RequestMapping(value = "/deleteGroup", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String deleteAGroup(@RequestBody GroupBean groupBean, HttpSession session,
			HttpServletResponse response, HttpServletRequest request) {
		JSONObject obj;
		JSONArray array = new JSONArray();
		groupBean.setUserId(session.getAttribute("uid").toString());
		if (group.deleteAGroup(groupBean.getGroupId(), session.getAttribute("uid").toString())) {
			obj = new JSONObject();
			obj.put("result", "Success");
			array.put(obj);

		} else {
			obj = new JSONObject();
			obj.put("result", "Failed");
			array.put(obj);

		}
		return array.toString();

	}

	@RequestMapping(value = "/displayContacts", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String displayAllContacts(HttpSession session, HttpServletResponse response) {

		LOGGER.info("displayAllContacts Requested");
		JSONArray contactsArray = contact.getAllContacts(session.getAttribute("uid").toString());
		if (contactsArray == null) {
			contactsArray = new JSONArray();
		}
		return contactsArray.toString();

	}

	//
	@RequestMapping(value = "/displayGroups", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String displayAllGroups(HttpSession session, HttpServletResponse response) {

		LOGGER.info("displayGroups Requested");

		JSONArray contactsArray = group.getAllGroups(session.getAttribute("uid").toString());
		return contactsArray.toString();

	}

	@RequestMapping(value = "/add_contact_to_grp", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String addContacttoGrp(@RequestBody String contactgrpsPairs, HttpSession session,
			HttpServletResponse response) {
		LOGGER.info("add_contact_to_grp Requested");
		JSONObject obj;
		JSONArray array = new JSONArray();

		if (contact.addGroupsUnderContact(contactgrpsPairs, session.getAttribute("uid").toString())) {
			obj = new JSONObject();
			obj.put("result", "Success");
			array.put(obj);

		} else {
			obj = new JSONObject();
			obj.put("result", "Failed");
			array.put(obj);
		}

		return array.toString();

	}

	@RequestMapping(value = "/ContactGroups", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String contactGroups(@RequestBody ContactBean contactBean, HttpSession session,
			HttpServletResponse response, HttpServletRequest request) {
		JSONObject obj;
		JSONArray array = new JSONArray();
		contactBean.setUserId(session.getAttribute("uid").toString());

		return contact.getAddedgrps(contactBean).toString();

	}

	@RequestMapping(value = "/view_Contacts_InGrp", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String viewcontactinGroup(@RequestBody GroupBean groupBean, HttpSession session,
			HttpServletResponse response, HttpServletRequest request) {
		JSONObject obj;
		JSONArray array = new JSONArray();
		groupBean.setUserId(session.getAttribute("uid").toString());
		return contact.getcontactsinGroup(groupBean).toString();
	}

}
