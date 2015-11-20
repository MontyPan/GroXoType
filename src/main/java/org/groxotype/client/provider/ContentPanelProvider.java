package org.groxotype.client.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.groxotype.client.core.AbstractProvider;
import org.groxotype.client.core.ProviderCenter;
import org.groxotype.client.core.UiXmlGenerator;
import org.groxotype.client.ui.AddButton;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.ButtonBar;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * {@link ContentPanel} 跟 {@link FramedPanel} 都一樣，所以就共用 provider。
 */
public class ContentPanelProvider extends AbstractProvider<ContentPanel> {

	interface MyUiBinder extends UiBinder<Widget, ContentPanelProvider> {}
	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	@UiField CheckBox cOrF;
	@UiField TextField headingText;
	@UiField TextField btn1Text;
	@UiField TextField btn2Text;

	@Override
	public Widget genSettingView() {
		return uiBinder.createAndBindUi(this);
	}

	@Override
	public Class<ContentPanel> getClazz() {
		return ContentPanel.class;
	}

	@Override
	public List<Class<? extends ContentPanel>> getExClass() {
		ArrayList<Class<? extends ContentPanel>> result = new ArrayList<>();
		result.add(FramedPanel.class);
		return result;
	}

	@Override
	public String getCategory() {
		return ProviderCenter.LAYOUT;
	}

	@Override
	protected ContentPanel genComponent() {
		ContentPanel result = cOrF.getValue() ? new ContentPanel() : new FramedPanel();

		if (headingText.getValue() != null) {
			result.setHeadingText(headingText.getValue());
		} else {
			result.setHeaderVisible(false);
		}

		if (btn1Text.getValue() != null) {
			result.addButton(new TextButton(btn1Text.getValue()));
		}

		if (btn2Text.getValue() != null) {
			result.addButton(new TextButton(btn2Text.getValue()));
		}

		result.add(new AddButton(result));
		return result;
	}

	@Override
	public HashMap<String, Object> genAttributeMap(ContentPanel component) {
		HashMap<String, Object> result = new HashMap<>();
		String header = component.getHeader().getText();
		if (header == null ) {
			result.put("headerVisible", "false");
		} else {
			result.put("headingText", header);
		}
		return result;
	}

	@Override
	public void genCode(ContentPanel component, UiXmlGenerator uiXml, int level) {
		uiXml.addStartTag(level, component);
		uiXml.addChild(level + 1, (Component)component.getWidget());

		//處理 button bar
		ButtonBar buttonBar = component.getButtonBar();

		for (int i = 0; i < buttonBar.getWidgetCount(); i++) {
			uiXml.addFakeStartTag(level + 1, component, "button");
			uiXml.addChild(level + 2, (Component) buttonBar.getWidget(i));
			uiXml.addFakeEndTag(level + 1, component, "button");
		}
		////

		uiXml.addEndTag(level, component);
	}
}
