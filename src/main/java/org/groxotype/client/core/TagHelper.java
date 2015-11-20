package org.groxotype.client.core;

import java.util.HashMap;

import com.sencha.gxt.widget.core.client.Component;

class TagHelper {
	private static final String AUTO_NAME = "ns";
	private int autoIndex = 0;
	private HashMap<String, String> namespace = new HashMap<>();

	public TagHelper() {
		namespace.put("urn:ui:com.google.gwt.uibinder", "ui");
	}

	public String header(Component component) {
		StringBuffer result = new StringBuffer("<" + tagName(component));
		result.append(GenUtil.genAttribute(component));
		result.append(">");
		return result.toString();
	}

	public String tail(Component component) {
		return "</" + tagName(component) + ">";
	}

	public String all(Component component) {
		StringBuffer result = new StringBuffer("<" + tagName(component));
		result.append(GenUtil.genAttribute(component));
		result.append("/>");
		return  result.toString();
	}

	public String xmlns() {
		StringBuffer result = new StringBuffer();
		for (String pkgName : namespace.keySet()) {
			result.append("\txmlns:" + namespace.get(pkgName) + "=\"" + pkgName + "\"\n");
		}
		return result.substring(0, result.length() - 1);
	}

	private String tagName(Component component) {
		String fullName = component.getClass().getName();
		return getNamespace(component) + ":" + ClassUtil.className(fullName);
	}

	public String getNamespace(Component component) {
		String fullName = component.getClass().getName();
		String packageName = ClassUtil.packageName(fullName);
		String foo = "urn:import:" + packageName;
		if (namespace.containsKey(foo)) {
			return namespace.get(foo);
		}
		String lastName = ClassUtil.className(packageName);
		for (int i = 1; i <= lastName.length(); i++) {
			String rc = lastName.substring(0, i);
			if (!namespace.containsValue(rc)) {
				namespace.put(foo, rc);
				return rc;
			}
		}
		return AUTO_NAME + autoIndex++;
	}
}