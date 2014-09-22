package montyPan.groxotype.client.ui.editor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Composite;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.DoubleField;

public class HVLayoutDataEditor extends Composite implements Editor<HorizontalLayoutData>{
	private static HVLayoutDataEditorUiBinder uiBinder = GWT.create(HVLayoutDataEditorUiBinder.class);
	interface HVLayoutDataEditorUiBinder extends UiBinder<Widget, HVLayoutDataEditor> {}

	interface Driver extends SimpleBeanEditorDriver<HorizontalLayoutData, HVLayoutDataEditor> {}
	private Driver driver = GWT.create(Driver.class);
	
	@UiField @Ignore ContentPanel root;
	@UiField DoubleField width;
	@UiField DoubleField height;

	private HorizontalLayoutData data = new HorizontalLayoutData();
	
	public HVLayoutDataEditor(int index) {
		initWidget(uiBinder.createAndBindUi(this));
		driver.initialize(this);
		driver.edit(data);
		root.setHeadingText("第 " + index + " 個");
	}
	
	public HorizontalLayoutData getHLayoutData() {
		return driver.flush();
	}
	
	public VerticalLayoutData getVLayoutData() {
		HorizontalLayoutData data = getHLayoutData();
		return new VerticalLayoutData(data.getWidth(), data.getHeight(), data.getMargins());
	}
}