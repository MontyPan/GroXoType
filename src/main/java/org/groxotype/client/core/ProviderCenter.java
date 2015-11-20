package org.groxotype.client.core;

import java.util.Collection;
import java.util.HashMap;

import org.groxotype.client.provider.ContentPanelProvider;
import org.groxotype.client.provider.TextButtonProvider;

import com.sencha.gxt.widget.core.client.Component;

public class ProviderCenter {
	//懶得給很長的名字，反正預設都是 GXT 的東西
	public static final String LAYOUT = "GXT Layout";
	public static final String COMPONENT = "GXT Component";

	private static HashMap<Class<? extends Component>, AbstractProvider<? extends Component>> map = new HashMap<>();

	public static void add(AbstractProvider<? extends Component> provider) {
		map.put(provider.getClazz(), provider);

		if (provider.getExClass() == null) { return; }

		for (Class<? extends Component> clazz : provider.getExClass()) {
			map.put(clazz, provider);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T extends Component> AbstractProvider<T> getProvider(T component) {
		return (AbstractProvider<T>) map.get(component.getClass());
	}

	public static Collection<AbstractProvider<? extends Component>> getProvider() {
		return map.values();
	}

	public static void addAll() {
		addLayoutSet();
		addComponentSet();
	}

	public static void addLayoutSet() {
		add(new ContentPanelProvider());
	}

	public static void addComponentSet() {
		add(new TextButtonProvider());
	}
}
