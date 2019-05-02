package com.sync.naver;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	// home page.
	@RequestMapping(value = "/home.do", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "blog.do", method = RequestMethod.GET)
	public String blogHomePage(Locale locale, Model model) {
		
		
		return "/blog/blogHomePage";
		
	}
	
	@RequestMapping(value = "naverLogin.do", method = RequestMethod.GET)
	public String naverLoginPage(Locale locale, Model model) {
		
		
		return "/naver/naverLoginPage";
		
	}
	
	@RequestMapping(value = "loginoath.do", method = RequestMethod.GET)
	public String loginaoth(Locale locale, Model model) {
		String token ="";
		
		
		return "/naver/loginOath";
		
	}
	
	@RequestMapping(value = "profileimage.do", method = RequestMethod.POST)
	public String profileImgae(Locale locale, Model model) {
		String token ="";
		logger.debug("token을 가지고 오고 싶다구 ");
		
		return "";
		
	}
	
}
