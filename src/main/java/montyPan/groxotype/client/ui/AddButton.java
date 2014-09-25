package montyPan.groxotype.client.ui;

import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.InsertResizeContainer;
import com.sencha.gxt.widget.core.client.container.ResizeContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

public class AddButton extends TextButton {
	private ResizeContainer container;
	
	@UiConstructor
	public AddButton(ResizeContainer container) {
		this.container = container;
		setText("+");
		this.addSelectHandler(new AddHandler(this));
	}
	
	/**
	 * @param widget 傳入 null，就是作 {@link AddButton#setVisible(boolean)} 讓 button 消失
	 */
	public void replace(Widget widget) {
		if (widget == null) {
			this.setVisible(false);
			return;
		}
		
		widget.setLayoutData(this.getLayoutData());

		if (container instanceof InsertResizeContainer) {
			int index = container.getWidgetIndex(this);
			((InsertResizeContainer) container).insert(widget, index);
			container.remove(this);
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