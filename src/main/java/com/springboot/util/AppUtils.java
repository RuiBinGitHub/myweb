package com.springboot.util;

import java.util.HashMap;
import java.util.Map;

public class AppUtils {

	public static Map<String, Object> getMap(Object... values) {
		Map<String, Object> map = new HashMap<>();
		for (int i = 0; i < values.length; i += 2)
			map.put(values[i].toString(), values[i + 1]);
		return map;
	}
}
