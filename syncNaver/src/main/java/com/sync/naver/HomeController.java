package com.sync.naver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
		logger.info("naverLogin.do ");
		
		return "/naver/naverLoginPage";
		
	}
	
	@RequestMapping(value = "loginoath.do", method = RequestMethod.GET)
	public String loginaoth(Locale locale, Model model) {
		String token ="";
		logger.info("loginoath.do ");
		
		return "/naver/loginOath";
		
	}
	
	@RequestMapping(value = "profileimage.do", produces="application/json;charset=UTF-8" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject profileImgae(Locale locale, Model model, @RequestBody String data) {
		JSONObject result = new JSONObject();
		logger.info("token을 가지고 오고 싶다구 token " + data);
//		model.addAttribute("token",token);
		result.put("token", data);
		
		String token = data;// 네이버 로그인 접근 토큰;
        String header = "Bearer " + token; // Bearer 다음에 공백 추가
        try {
            String apiURL = "https://openapi.naver.com/v1/nid/me";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", header);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
		
		
		
		
		
		
		
		
		
		
		
		
		return result;
		
	}
	
}
