package com.security.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


public class CookiesUtils {
	
	public static String getUserId(HttpServletRequest request){
		
		Cookie[] cookies = request.getCookies();
		String userId = "";
		for(Cookie cookie:cookies){
			if("userId".equals(cookie.getName())){
				userId = cookie.getValue();
			}
		}
		return userId;
	}

}
