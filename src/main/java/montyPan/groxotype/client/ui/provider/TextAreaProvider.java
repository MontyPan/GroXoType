package montyPan.groxotype.client.ui.provider;

import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.form.TextArea;

import montyPan.groxotype.client.ui.ComponentProvider;
import montyPan.groxotype.client.util.ProviderUtil;

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
	protected Widget genComponent() {
		return new TextArea();
	}
}