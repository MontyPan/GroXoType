package montyPan.groxotype.client.generator;

import java.util.ArrayList;
import java.util.HashMap;

import com.sencha.gxt.widget.core.client.Component;

public class GenUtil {
	public static final String ATTRIBUTE = "ATTRIBUTE";
	public static final String CONTEXT = "CONTEXT";
	public static final String CHILD = "--CHILD--";
	public static final String NAMESPACE = "--NS--";

	public static void setAttribute(Component component, HashMap<String, Object> map) {
		component.setData(ATTRIBUTE, map);
	}
	
	public static String genAttribute(Component component) {
		HashMap<String, Object> map = component.getData(ATTRIBUTE);
		if (map == null || map.size() == 0) { return ""; }
		
		StringBuffer result = new StringBuffer();
		for (String key : map.keySet()) {
			result.append(" " + key + "=\"" + map.get(key) + "\"");
		}
		return result.toString();
	}

	public static void setContext(Component component, ArrayList<String> context) {
		if (context == null || context.size() == 0) { return; }

		component.setData(CONTEXT, context);
	}
	
	public static String genTab(int level) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < level; i++) {
			result.append('\t');
		}
		return result.toString();
	}
}