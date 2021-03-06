package org.groxotype.client.ui;

import com.google.gwt.uibinder.client.UiConstructor;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.InsertResizeContainer;
import com.sencha.gxt.widget.core.client.container.ResizeContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.tree.Tree;

public class AddButton extends TextButton {
	private ResizeContainer container;
	
	@UiConstructor
	public AddButton(ResizeContainer container) {
		this.container = container;
		Operator.init(container);
		setText("+");
		this.addSelectHandler(new AddHandler(this));
	}
	
	/**
	 * @param widget 傳入 null，就是作 {@link AddButton#setVisible(boolean)} 讓 button 消失
	 */
	public void replace(Component widget) {
		if (widget == null) {
			this.setVisible(false);
			return;
		}
		
		widget.setLayoutData(this.getLayoutData());

		if (container instanceof InsertResizeContainer) {
			int index = container.getWidgetIndex(this);
			if (container.getParent() instanceof TabPanel) {	//就是要跟別人不一樣的 TabPanel [怒]
				//這時候的 container 是 TabPanel 裡頭的 CardLayoutContainer
				TabPanel tabPanel = (TabPanel) container.getParent();
				TabItemConfig config = tabPanel.getConfig(this);
				tabPanel.remove(this);
				tabPanel.insert(widget, index ,config);
				tabPanel.setActiveWidget(widget);
			} else {
				((InsertResizeContainer) container).insert(widget, index);
				container.remove(this);
			}
		} else if (container instanceof BorderLayoutContainer) {
			//BorderLayout 不能直接用 add，東西南北需要分別處理
			BorderLayoutContainer b = (BorderLayoutContainer) container;
			if (b.getEastWidget() == this) {
				b.setEastWidget(widget);
			}
			if (b.getWestWidget() == this) {
				b.setWestWidget(widget);
			}
			if (b.getSouthWidget() == this) {
				b.setSouthWidget(widget);
			}
			if (b.getNorthWidget() == this) {
				b.setNorthWidget(widget);
			}
			if (b.getCenterWidget() == this) {
				b.setCenterWidget(widget);
			}
		} else {
			//理論上 container 就是 SimpleContainer
			container.remove(this);
			container.add(widget);
		}
		
		container.forceLayout();
		
		// ==== 加到畫面上才有辦法操作的黑魔法區 ==== //
		if (widget instanceof Tree<?, ?>) {
			((Tree<?, ?>) widget).expandAll();
		}
		// ======== //
	}
		
	class AddHandler implements SelectHandler {
		private AddButton button;
		public AddHandler(AddButton btn) {
			this.button = btn;
		}
		
		@Override
		public void onSelect(SelectEvent event) {
			ComponentSelector.showBy(button);
		}
	}
}