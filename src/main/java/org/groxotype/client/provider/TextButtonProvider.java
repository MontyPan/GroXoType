package org.groxotype.client.provider;

import java.util.HashMap;

import org.groxotype.client.core.AbstractProvider;
import org.groxotype.client.core.ProviderCenter;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.form.TextField;

public class TextButtonProvider extends AbstractProvider<TextButton> {
	interface MyUiBinder extends UiBinder<Widget, TextButtonProvider> {}
	private MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	@UiField TextField text;

	@Override
	public Widget genSettingView() {
		return uiBinder.createAndBindUi(this);
	}

	@Override
	public Class<TextButton> getClazz() {
		return TextButton.class;
	}

	@Override
	public String getCategory() {
		return ProviderCenter.COMPONENT;
	}

	@Override
	protected TextButton genComponent() {
		return new TextButton(text.getValue());
	}

	@Override
	public HashMap<String, Object> genAttributeMap(TextButton component) {
		HashMap<String, Object> result = new HashMap<>();
		result.put("text", component.getText());
		return result;
	}
}