package com.sync.naver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
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
//	https://nid.naver.com/oauth2.0/authorize?response_type=code&
//		state=1280844713840924495371453604610442199972&
//		client_id=iCCimufOglnCbDHjCAf0&
//		redirect_uri=http%3A%2F%2F127.0.0.1%2Floginoath.do&
//		locale=ko_KR&
//		inapp_view=&
//		oauth_os=
	@RequestMapping(value = "loginoath.do", method = RequestMethod.GET)
	public String loginaoth(Locale locale, Model model) {
		String token ="";
		logger.info("loginoath.do ");
		
		String clientId = "iCCimufOglnCbDHjCAf0";//애플리케이션 클라이언트 아이디값";
	    String redirectURI = "http://127.0.0.1/loginoath.do";
		try {
			redirectURI = URLEncoder.encode("http://127.0.0.1/loginoath.do", "UTF-8");
			SecureRandom random = new SecureRandom();
			String state = new BigInteger(130, random).toString();
			String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
			apiURL += "&client_id=" + clientId;
			apiURL += "&redirect_uri=" + redirectURI;
			apiURL += "&state=" + state;
			URL url = new URL(apiURL);
		      HttpURLConnection con = (HttpURLConnection)url.openConnection();
		      con.setRequestMethod("GET");
		      int responseCode = con.getResponseCode();
		      BufferedReader br;
		      System.out.print("responseCode="+responseCode);
		      if(responseCode==200) { // 정상 호출
		    	  System.out.println("정상");
		    	  br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		    	  System.out.println(br.toString());
		      } else {  // 에러 발생
		    	  System.out.println("에러");
		        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		      }
		      String inputLine;
		      StringBuffer res = new StringBuffer();
		      while ((inputLine = br.readLine()) != null) {
		        res.append(inputLine);
		      }
		      br.close();
		      if(responseCode==200) {
		    	  System.out.println(res.toString());
		      }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   

		return "/naver/loginOath";
		
	}
	
	@RequestMapping(value = "profileimage.do", produces="application/json;charset=UTF-8" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject profileImgae(Locale locale, Model model, @RequestBody String data) {
		JSONObject result = new JSONObject();
		logger.info("token을 가지고 오고 싶다구 token " + data);
		result.put("token", data);
		return result;
		
	}
	
	@RequestMapping(value = "tokenReload.do", produces="application/json;charset=UTF-8" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject tokenReload(Locale locale, Model model, @RequestBody String data) {
		JSONObject result = new JSONObject();
		logger.info("token을 가지고 오고 싶다구 token '" + data + "'");
		result.put("token", data);
		String clientId = "iCCimufOglnCbDHjCAf0";//애플리케이션 클라이언트 아이디값";
	    String clientSecret = "1sJRmEcJoN";//애플리케이션 클라이언트 시크릿값";
	    String refresh_token = data;
	    String access_token = "";
	    String apiURL;
	    String sercive_provider = "NAVER";
	    access_token = data;

	    try {
			access_token = URLEncoder.encode(data, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}			

	    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=refresh_token&";
	    apiURL += "client_id=" + clientId;
	    apiURL += "&client_secret=" + clientSecret;
	    apiURL += "&refresh_token=" + data;
//	    apiURL += "&access_token=" + access_token;
//	    apiURL += "&access_token=" + access_token;
	    apiURL += "&service_provider=" + sercive_provider;
	    System.out.println("apiURL="+apiURL);
	    System.out.println(apiURL);
	    System.out.println("https://nid.naver.com/oauth2.0/token?grant_type=refresh_token&client_id=jyvqXeaVOVmV&client_secret=527300A0_COq1_XV33cf&refresh_token=c8ceMEJisO4Se7uGCEYKK1p52L93bHXLn");
	    try {
	      URL url = new URL(apiURL);
	      HttpURLConnection con = (HttpURLConnection)url.openConnection();
	      con.setRequestMethod("GET");
	      int responseCode = con.getResponseCode();
	      BufferedReader br;
	      System.out.print("responseCode="+responseCode);
	      if(responseCode==200) { // 정상 호출
	        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	      } else {  // 에러 발생
	        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	      }
	      String inputLine;
	      StringBuffer res = new StringBuffer();
	      while ((inputLine = br.readLine()) != null) {
	        res.append(inputLine);
	      }
	      br.close();
	      if(responseCode==200) {
	    	  System.out.println(res.toString());
	      }
	    } catch (Exception e) {
	      System.out.println(e);
	    }

		return result;
		
	}
	
	@RequestMapping(value = "tokenDelete.do", produces="application/json;charset=UTF-8" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject tokenDelete(Locale locale, Model model, @RequestBody String data) {
		JSONObject result = new JSONObject();
		logger.info("token을 가지고 오고 싶다구 token '" + data + "'");
		result.put("token", data);
		/*
		 * access_token	string	삭제 처리된 접근 토큰 값
			result	string	처리 결과가 성공이면 'success'가 리턴
			expires_in	integer	접근 토큰의 유효 기간(초 단위)
			error	string	에러 코드
			error_description	string	에러 메시지
		 */
		String clientId = "iCCimufOglnCbDHjCAf0";//애플리케이션 클라이언트 아이디값";
	    String clientSecret = "1sJRmEcJoN";//애플리케이션 클라이언트 시크릿값";
	    String refresh_token = data;
	    String access_token = "";
	    String apiURL;
	    String sercive_provider = "NAVER";
	    access_token = data;
	    /*
	    try {
			access_token = URLEncoder.encode(data, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}			
		*/
	    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=delete&";
	    apiURL += "client_id=" + clientId;
	    apiURL += "&client_secret=" + clientSecret;
	    apiURL += "&access_token=" + access_token;
	    apiURL += "&sercive_provider=" + sercive_provider;
	    System.out.println("apiURL="+apiURL);
	    System.out.println(apiURL);
	    System.out.println("https://nid.naver.com/oauth2.0/token?grant_type=delete&client_id=jyvqXeaVOVmV&client_secret=527300A0_COq1_XV33cf&access_token=c8ceMEJisO4Se7uGCEYKK1p52L93bHXLnaoETis9YzjfnorlQwEisqemfpKHUq2gY&service_provider=NAVER");
	    try {
	      URL url = new URL(apiURL);
	      HttpURLConnection con = (HttpURLConnection)url.openConnection();
	      con.setRequestMethod("GET");
	      int responseCode = con.getResponseCode();
	      BufferedReader br;
	      System.out.print("responseCode="+responseCode);
	      if(responseCode==200) { // 정상 호출
	        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	      } else {  // 에러 발생
	        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	      }
	      String inputLine;
	      StringBuffer res = new StringBuffer();
	      while ((inputLine = br.readLine()) != null) {
	        res.append(inputLine);
	      }
	      br.close();
	      if(responseCode==200) {
	    	  System.out.println(res.toString());
	      }
	    } catch (Exception e) {
	      System.out.println(e);
	    }
		
		
		return result;
		
	}
	
	
}
