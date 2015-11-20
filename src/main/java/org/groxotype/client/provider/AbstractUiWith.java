package org.groxotype.client.provider;

import java.util.HashMap;
import java.util.Set;

import org.groxotype.client.core.GenUtil;

import com.sencha.gxt.widget.core.client.Component;

public abstract class AbstractUiWith<T> {
	private HashMap<String, T> map = new HashMap<>();
	private int index;
	
	public abstract String getHeader();
	public abstract void process(Component component);
	public abstract String genCode();
	
	/**
	 * @param value 會幫忙加上左右大括號
	 */
	protected void setIntoComponent(Component component, String key, String value) {
		GenUtil.addAttribute(component, key, "{" + value + "}");
	}
	
	protected String nextName() {
		return getHeader() + index++;
	}
	
	public final void putEntity(String name, T value) {
		map.put(name, value);
	}
	
	public final Set<String> getKeys() {
		return map.keySet();
	}
	
	public final T getValue(String key) {
		return map.get(key);
	}
}