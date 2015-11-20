package org.groxotype.client.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.groxotype.client.core.AbstractProvider;
import org.groxotype.client.core.ProviderCenter;

import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

public class ComponentSelector {
	private Window window = new Window();
	private AddButton addButton;

	private AccordionLayoutContainer layout = new AccordionLayoutContainer();
	private HashMap<String, CategoryView> map = new HashMap<>();

	private ComponentSelector() {
		layout.setPixelSize(200, 400);
		window.setHeadingText("你要加三小？");	//I18N
		window.setModal(true);
		window.add(layout);

		ArrayList<AbstractProvider<? extends Component>> tmpList = new ArrayList<>();

		for (AbstractProvider<? extends Component> provider : ProviderCenter.getProvider()) {
			if (tmpList.contains(provider)) { continue; }

			this.add(provider);
			tmpList.add(provider);
		}
	}

	private void setTriggerButton(AddButton addButton) {
		this.addButton = addButton;
	}

	private void add(final AbstractProvider<? extends Component> provider) {
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

		TextButton button = new TextButton(provider.getName());
		button.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				provider.whenSelect();
			}
		});
		view.addItem(button);
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
}
