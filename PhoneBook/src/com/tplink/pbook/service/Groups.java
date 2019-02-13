package com.tplink.pbook.service;

import org.json.JSONArray;

import com.tplink.pbook.bean.GroupBean;
import com.tplink.pbook.bean.GroupedContactsBean;
import com.tplink.pbook.dao.GroupsDaoImpl;


/**
 * <h1>Phone Book!</h1>
 * <h3>class Groups Impl</h3>
 * The service  class to serve Group related services.
 * @author Pratap
 *
 */

public class Groups {


GroupsDaoImpl groupsDaoImpl;
	
	public boolean addAGroups(GroupBean groupBean) {
		groupsDaoImpl=new GroupsDaoImpl();
		return groupsDaoImpl.addANewGroups(groupBean);
		
	}
	
	
	public JSONArray getAllGroups(String userId){
		groupsDaoImpl=new GroupsDaoImpl(); 
		return groupsDaoImpl.allUserGroups(userId); 
		}
	public boolean  deleteAGroup(String groupsId,String userId) { 
		groupsDaoImpl=new GroupsDaoImpl();
		return groupsDaoImpl.deletePerticularGroups(groupsId,userId);
	  
	  }


	public boolean addAcontactToGrp(GroupedContactsBean groupedContactsBean) {
		// TODO Auto-generated method stub
		groupsDaoImpl=new GroupsDaoImpl();
		return groupsDaoImpl.addAcontactToGroup(groupedContactsBean);
		
	}
	 
}
