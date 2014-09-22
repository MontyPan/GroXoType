package montyPan.groxotype.client.ui;

import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;

public class CategoryView extends ContentPanel {
	private static final VerticalLayoutData LAYOUTDATA = new VerticalLayoutData(1, -1, new Margins(4, 5, 0, 5));
	private VerticalLayoutContainer vp = new VerticalLayoutContainer();

	public CategoryView(String name) {
		this.setHeadingText(name);
		add(vp);
		
		vp.setScrollMode(ScrollMode.AUTOY);
	}

	public void addItem(TextButton button) {
		button.setLayoutData(LAYOUTDATA);

		//按照名稱排序
		for (int i = 0; i < vp.getWidgetCount(); i++) {
			if (((TextButton) vp.getWidget(i)).getText().compareTo(button.getText()) > 0) {
				vp.insert(button, i);
				return;
			}
		}
		vp.add(button);
	}
}