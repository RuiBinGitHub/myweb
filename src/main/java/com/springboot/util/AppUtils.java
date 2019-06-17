package com.springboot.util;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class AppUtils {

	public static Map<String, Object> getMap(Object... values) {
		Map<String, Object> map = new HashMap<>();
		for (int i = 0; i < values.length; i += 2)
			map.put(values[i].toString(), values[i + 1]);
		return map;
	}

	public static String getDate(String format) {
		if (format == null)
			format = "yyyy-MM-dd";
		Date date = new Date();
		Format simple = new SimpleDateFormat(format);
		return simple.format(date);
	}

	/** 获取session */
	public static HttpSession getHttpSession() {
		RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes) attributes).getRequest();
		HttpSession session = request.getSession(true);
		return session;
	}

	/** 添加数据至session */
	public static void pushMap(String key, Object value) {
		HttpSession session = getHttpSession();
		session.setAttribute(key, value);
	}

	/** 从session获取数据 */
	public static Object pullMap(String key) {
		HttpSession session = getHttpSession();
		return session.getAttribute(key);
	}

	public static void removeMap(String key) {
		HttpSession session = getHttpSession();
		session.removeAttribute(key);

	}
}
