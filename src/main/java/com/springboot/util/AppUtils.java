package com.springboot.util;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
}
