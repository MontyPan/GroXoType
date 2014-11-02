package montyPan.groxotype.client.util;

import montyPan.groxotype.client.ui.ComponentSelector;
import montyPan.groxotype.client.ui.provider.BorderLayoutProvider;
import montyPan.groxotype.client.ui.provider.ContentPanelProvider;
import montyPan.groxotype.client.ui.provider.EmptyProvider;
import montyPan.groxotype.client.ui.provider.FieldLabelProvider;
import montyPan.groxotype.client.ui.provider.FieldSetProvider;
import montyPan.groxotype.client.ui.provider.GridProvider;
import montyPan.groxotype.client.ui.provider.HVLayoutProvider;
import montyPan.groxotype.client.ui.provider.TabPanelProvider;
import montyPan.groxotype.client.ui.provider.TextButtonProvider;

public class ProviderUtil {
	public static final String GROXOTYPE = "GroXoType";
	
	//懶得給很長的名字，反正預設都是 GXT 的東西
	public static final String LAYOUT = "GXT Layout";
	public static final String COMPONENT = "GXT Component";

	public static void addAll() {
		addLayoutSet();
		addComponentSet();
		addGroXoTypeSet();
	}
	
	public static void addLayoutSet() {
		ComponentSelector.addProvider(new BorderLayoutProvider());
		ComponentSelector.addProvider(new ContentPanelProvider());
		ComponentSelector.addProvider(new FieldLabelProvider());
		ComponentSelector.addProvider(new FieldSetProvider());
		ComponentSelector.addProvider(new HVLayoutProvider(true));
		ComponentSelector.addProvider(new HVLayoutProvider(false));
	}
	
	public static void addComponentSet() {
		ComponentSelector.addProvider(new GridProvider());
		ComponentSelector.addProvider(new TextButtonProvider());
		ComponentSelector.addProvider(new TabPanelProvider());
	}
	
	public static void addGroXoTypeSet() {
		ComponentSelector.addProvider(new EmptyProvider());
	}
}