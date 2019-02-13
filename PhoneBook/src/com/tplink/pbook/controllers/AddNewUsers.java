package com.tplink.pbook.controllers;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tplink.pbook.bean.UserBean;
import com.tplink.pbook.service.VerifyUserImpl;

/**
 * <h1>Phone Book!</h1>
 * <h3>Controller Add New Users</h3> The AddNewUsers is a Controller class that
 * routes data to add new Users.
 * 
 * @author Pratap
 *
 */
@Controller
public class AddNewUsers {

	private static final Logger LOGGER = Logger.getLogger(AddNewUsers.class.getName());

	VerifyUserImpl verifyUserImpl = new VerifyUserImpl();

	@RequestMapping(value = "/userSubmit", method = RequestMethod.POST)
	public ModelAndView userOnSubmit(@ModelAttribute UserBean userBean, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		LOGGER.info("PhoneBook Page Requested");
		ModelAndView modelAndView = null;
		boolean b = verifyUserImpl.validateUser(userBean);
		if (b == true) {
			modelAndView = new ModelAndView("pbook");
			modelAndView.addObject("userName", userBean.getUserId());
			createSession(request, response, userBean, session);
		} else {
			modelAndView = new ModelAndView("home");

			String errMsg1 = "UserId or Password is Incorrect";
			modelAndView.addObject("errMsg1", errMsg1);
		}
		return modelAndView;
	}

	public void createSession(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute UserBean userBean, HttpSession session) {

		request.getSession(true);
		session.setAttribute("uid", userBean.getUserId());

	}

}
