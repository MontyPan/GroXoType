package org.groxotype.client.core;

import java.util.HashMap;

import com.sencha.gxt.widget.core.client.Component;

public class GenUtil {
	public static final String ATTRIBUTE = "ATTRIBUTE";

	public static void addAttribute(Component component, HashMap<String, Object> map) {
		if (map == null) { return; }

		for (String key : map.keySet()) {
			addAttribute(component, key, map.get(key).toString());
		}
	}

	public static void addAttribute(Component component, String key, String value) {
		HashMap<String, Object> map = component.getData(GenUtil.ATTRIBUTE);

		if (map == null) {
			map = new HashMap<>();
			component.setData(ATTRIBUTE, map);
		}

		map.put(key, value);
	}

	public static String genAttribute(Component component) {
		HashMap<String, Object> map = component.getData(ATTRIBUTE);
		return genAttribute(map);
	}

	public static String genAttribute(HashMap<String, Object> map) {
		if (map == null || map.size() == 0) { return ""; }

		StringBuffer result = new StringBuffer();

		for (String key : map.keySet()) {
			result.append(" " + key + "=\"" + map.get(key) + "\"");
		}

		return result.toString();
	}

	public static String genTab(int level) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < level; i++) {
			result.append('\t');
		}
		return result.toString();
	}

	public static String uiWithHeader(String name, Class<?> clazz) {
		return "<ui:with field=\"" + name + "\" type=\"" + ClassUtil.fullName(clazz.getName()) + "\">";
	}
}