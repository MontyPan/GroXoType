package montyPan.groxotype.client.ui.editor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Composite;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.TextField;

public class TabConfigEditor extends Composite implements Editor<TabItemConfig> {
	interface TabConfigEditorUiBinder extends UiBinder<Widget, TabConfigEditor> {}
	private static TabConfigEditorUiBinder uiBinder = GWT.create(TabConfigEditorUiBinder.class);

	interface Driver extends SimpleBeanEditorDriver<TabItemConfig, TabConfigEditor> {}
	private Driver driver = GWT.create(Driver.class);

	@UiField @Ignore ContentPanel root;
	@UiField TextField text;
	@UiField CheckBox closable;
	
	private TabItemConfig data = new TabItemConfig("？");
	
	public TabConfigEditor(int index) {
		initWidget(uiBinder.createAndBindUi(this));
		driver.initialize(this);
		driver.edit(data);
		root.setHeadingText("第 " + index + " 個");
	}
	
	public TabItemConfig getTabItemConfig() {
		return driver.flush();
	}
}