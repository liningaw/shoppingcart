package com.tarena.shoppingcart.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
	private static String path = "/shopping";

	private static int ttl = 24 * 60 * 60;

	/**
	 * @param name
	 * @param value
	 * @param response
	 * @param ttl
	 * @throws UnsupportedEncodingException
	 * 设置cookie，可以自定义其生存时间
	 */
	public static void addCookie(String name, String value, HttpServletResponse response, int ttl)
			throws UnsupportedEncodingException {
		Cookie cookie = new Cookie(name, URLEncoder.encode(value, "utf-8"));
		cookie.setMaxAge(CookieUtils.ttl);
		cookie.setPath(CookieUtils.path);

		response.addCookie(cookie);
	}

	/**
	 * @param name
	 * @param value
	 * @param response
	 * @throws UnsupportedEncodingException
	 * 设置cookie，生存时间为默认值
	 */
	public static void addCookie(String name, String value,
			HttpServletResponse response) throws UnsupportedEncodingException {
		CookieUtils.addCookie(name, value, response, CookieUtils.ttl);
	}

	/**
	 * @param name
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 * 得到cookie
	 */
	public static String findCookie(String name, HttpServletRequest request)
			throws UnsupportedEncodingException {
		String value = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equals(name)) {
					value = URLDecoder.decode(cookie.getValue(), "utf-8");
				}
			}
		}

		return value;
	}
	
	/**
	 * @param name
	 * @param response
	 * 删除cookie
	 */
	public static void deleteCookie(String name, HttpServletResponse response){
		Cookie cookie = new Cookie(name,"");
		cookie.setMaxAge(0);
		cookie.setPath(CookieUtils.path);
		response.addCookie(cookie);
	}
}