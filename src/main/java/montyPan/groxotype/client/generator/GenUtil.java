package montyPan.groxotype.client.generator;

import java.util.ArrayList;
import java.util.HashMap;

import com.sencha.gxt.widget.core.client.Component;

public class GenUtil {
	public static final String ATTRIBUTE = "ATTRIBUTE";
	public static final String CONTEXT = "CONTEXT";

	public static void setAttribute(Component component, HashMap<String, Object> map) {
		if (map == null || map.size() == 0) { return; }

		StringBuffer result = new StringBuffer();
		for (String key : map.keySet()) {
			result.append(" " + key + "=\"" + map.get(key) + "\"");
		}
		component.setData(ATTRIBUTE, result.toString());
	}

	public static void setContext(Component component, ArrayList<String> context) {
		if (context == null || context.size() == 0) { return; }

		component.setData(CONTEXT, context);
	}
}