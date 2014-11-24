package montyPan.groxotype.client.ui.provider;

import montyPan.groxotype.client.ui.ComponentProvider;
import montyPan.groxotype.client.util.ProviderUtil;

import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.form.TextArea;

public class TextAreaProvider extends ComponentProvider {
	@Override
	public String getCategory() {
		return ProviderUtil.COMPONENT;
	}

	@Override
	protected String buttonText() {
		return "TextArea";
	}

	@Override
	protected Component genComponent() {
		return new TextArea();
	}
}