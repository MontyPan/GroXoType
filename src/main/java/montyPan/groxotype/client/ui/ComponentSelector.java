package montyPan.groxotype.client.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer;

public class ComponentSelector {
	private Window window = new Window();
	private AddButton addButton;
	
	private AccordionLayoutContainer layout = new AccordionLayoutContainer();
	private HashMap<String, CategoryView> map = new HashMap<>();

	private ComponentSelector() {
		layout.setPixelSize(200, 400);
		window.setHeadingText("你要加三小？");
		window.setModal(true);
		window.add(layout);
	}
	
	private void setTriggerButton(AddButton addButton) {
		this.addButton = addButton;
	}

	private void add(ComponentProvider provider) {
		String category = provider.getCategory();
		CategoryView view = map.get(category);
		
		if (view == null) {
			view = new CategoryView(category);

			//用名稱排序，決定要插在哪裡
			ArrayList<String> keySet = new ArrayList<>(map.keySet());
			Collections.sort(keySet);
			for (int i = 0; i < keySet.size(); i++) {
				if (keySet.get(i).compareTo(category) > 0) {
					layout.insert(view, i);
					break;
				}
			}

			//layout 沒有真的加進去，就補在最後一個
			if (map.size() != layout.getWidgetCount() - 1) {
				layout.add(view);
			}
			
			map.put(category, view);
		}
		
		view.addItem(provider.getButton());
	}
	
	private static ComponentSelector instance = new ComponentSelector();
	
	public static void showBy(AddButton btn) {
		instance.setTriggerButton(btn);
		instance.window.show();
	}
	
	public static void hide() {
		instance.window.hide();
	}

	public static void replace(Component component) {
		instance.addButton.replace(component);
	}
	
	public static void addProvider(ComponentProvider cp) {
		instance.add(cp);
	}
}