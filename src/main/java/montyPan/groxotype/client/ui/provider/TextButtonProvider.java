package montyPan.groxotype.client.ui.provider;

import java.util.HashMap;

import montyPan.groxotype.client.ui.ComponentProvider;
import montyPan.groxotype.client.util.ProviderUtil;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.form.TextField;

public class TextButtonProvider extends ComponentProvider<TextButton> {
	interface MyUiBinder extends UiBinder<Widget, TextButtonProvider> {}
	private MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	
	@UiField TextField text;
	
	@Override
	public String getCategory() {
		return ProviderUtil.COMPONENT;
	}

	@Override
	protected String buttonText() {
		return "TextButton";
	}
	
	@Override
	public Widget genSettingView() {
		return uiBinder.createAndBindUi(this);
	}

	@Override
	protected TextButton genComponent() {
		return new TextButton(text.getValue());
	}
	
	@Override
	protected HashMap<String, Object> genAttrMap(TextButton component) {
		HashMap<String, Object> result = new HashMap<>();
		result.put("text", component.getText());
		return result;
	}
}