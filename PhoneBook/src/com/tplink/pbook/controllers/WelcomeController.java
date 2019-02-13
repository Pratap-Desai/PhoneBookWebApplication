package com.tplink.pbook.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tplink.pbook.bean.UserBean;
import com.tplink.pbook.service.AuthenticationImpl;
import com.tplink.pbook.service.VerifyUserImpl;

/**
 * <h1>Phone Book!</h1>
 * <h3>Controller Home Page</h3> The WelcomeController is a Controller class
 * that controls events on home Page.
 * 
 * @author Pratap
 *
 */

@Controller
public class WelcomeController {

	private static final Logger LOGGER = Logger.getLogger(WelcomeController.class.getName());
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	AuthenticationImpl authenticationImpl = new AuthenticationImpl();
	VerifyUserImpl verifyUserImpl = new VerifyUserImpl();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		LOGGER.info("Home Page Requested");
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView userlogin(@ModelAttribute UserBean userBean, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		LOGGER.info("PhoneBook Page Requested");
		ModelAndView modelAndView = null;
		boolean b = authenticationImpl.authenticate(userBean);
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

	@RequestMapping(value = "/newUserSignIn", method = RequestMethod.GET)
	public String newUserPage(Model model) {
		return "newuser";
	}

	@RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
	public String forgotPassword(Model model) {
		return "forgotpassword";
	}

	@RequestMapping(value = "/codeGen", method = RequestMethod.GET)
	public String verifyPage(Model model, HttpServletRequest request, HttpSession session) {
		String vemail = request.getParameter("emailId").toString();
		System.out.println(vemail);
		session.setAttribute("vemail", vemail);
		// generate and send verification code to email addr
		boolean verified = verifyUserImpl.generateSendVerificationCode(vemail);
		if (verified) {
			return "verify";
		}
		return "inValid";

	}

	@RequestMapping(value = "/verifyCode", method = RequestMethod.GET)
	public String verifyUserEmail(Model model, HttpServletRequest request, HttpSession session) {
		boolean verifycode = verifyUserImpl.verifyCode(session.getAttribute("vemail").toString(),
				request.getParameter("vcode").toString());
		if (verifycode) {
			return "pbook";
		}
		return "inValid";

	}

	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		session = request.getSession(false);
		if (session != null) {
			if (session.getAttribute("uid") != null) {

				if (authenticationImpl.logout(session.getAttribute("uid").toString()) == true) {

					session.invalidate();
					System.out.println(("logout"));
				}

			}

		}
		String redirect = "home";
		return new ModelAndView(redirect);

	}

}