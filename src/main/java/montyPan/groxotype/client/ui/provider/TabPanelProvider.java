package montyPan.groxotype.client.ui.provider;

import java.util.ArrayList;

import montyPan.groxotype.client.ui.AddButton;
import montyPan.groxotype.client.ui.ComponentProvider;
import montyPan.groxotype.client.ui.editor.TabConfigEditor;
import montyPan.groxotype.client.util.ProviderUtil;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.TabPanel.TabPanelBottomAppearance;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.IntegerField;

public class TabPanelProvider extends ComponentProvider<TabPanel> {
	interface MyUiBinder extends UiBinder<Widget, TabPanelProvider> {}
	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	
	@UiField CheckBox topOrBottom;
	@UiField IntegerField childAmount;
	@UiField VerticalLayoutContainer tabConfigPanel;

	private ArrayList<TabConfigEditor> editorList = new ArrayList<>();
	
	@Override
	public String getCategory() {
		return ProviderUtil.COMPONENT;
	}

	@Override
	protected String buttonText() {
		return "TabPanel";
	}
	
	@Override
	public Widget genSettingView() {
		return uiBinder.createAndBindUi(this);
	}

	@Override
	protected TabPanel genComponent() {
		TabPanel result = topOrBottom.getValue() ? new TabPanel() :
			new TabPanel(GWT.<TabPanelBottomAppearance>create(TabPanelBottomAppearance.class));
		
		for (int i = 0; i < childAmount.getValue(); i++) {
			result.add(
				new AddButton(result.getContainer()),
				editorList.get(i).getTabItemConfig()
			);
		}
		return result;
	}
	
	@UiHandler("childAmount")
	void childChange(ValueChangeEvent<Integer> vce) {
		tabConfigPanel.clear();
		for (int i = 0; i < childAmount.getValue(); i++) {
			TabConfigEditor editor = new TabConfigEditor(i);
			tabConfigPanel.add(editor);
			editorList .add(editor);
		}
	}
}