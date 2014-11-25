package montyPan.groxotype.client.ui.provider;

import java.util.ArrayList;

import montyPan.groxotype.client.ui.AddButton;
import montyPan.groxotype.client.ui.ComponentProvider;
import montyPan.groxotype.client.ui.editor.HVLayoutDataEditor;
import montyPan.groxotype.client.util.ProviderUtil;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.form.IntegerField;

public class VerticalLayoutProvider extends ComponentProvider<VerticalLayoutContainer> {
	interface MyUiBinder extends UiBinder<Widget, VerticalLayoutProvider> {}
	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	@UiField IntegerField childAmount;
	@UiField VerticalLayoutContainer layoutDataPanel;

	private ArrayList<HVLayoutDataEditor> editorList = new ArrayList<>();

	@Override
	protected String buttonText() {
		return "Vertical";
	}

	@Override
	public String getCategory() {
		return ProviderUtil.LAYOUT;
	}

	@Override
	protected Widget genSettingView() {
		return uiBinder.createAndBindUi(this);
	}

	@Override
	protected VerticalLayoutContainer genComponent() {
		VerticalLayoutContainer result = new VerticalLayoutContainer();
		for (int i = 0; i < childAmount.getValue(); i++) {
			result.add(new AddButton(result), editorList.get(i).getVLayoutData());
		}
		return result;
	}

	@UiHandler("childAmount")
	void childChange(ValueChangeEvent<Integer> vce) {
		layoutDataPanel.clear();
		for (int i = 0; i < childAmount.getValue(); i++) {
			HVLayoutDataEditor editor = new HVLayoutDataEditor(i);
			layoutDataPanel.add(editor);
			editorList.add(editor);
		}
	}
}