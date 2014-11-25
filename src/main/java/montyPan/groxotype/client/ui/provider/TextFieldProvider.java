package montyPan.groxotype.client.ui.provider;

import montyPan.groxotype.client.ui.ComponentProvider;
import montyPan.groxotype.client.util.ProviderUtil;

import com.sencha.gxt.widget.core.client.form.TextField;

public class TextFieldProvider extends ComponentProvider<TextField> {
	@Override
	public String getCategory() {
		return ProviderUtil.COMPONENT;
	}

	@Override
	protected String buttonText() {
		return "TextField";
	}
	
	@Override
	protected TextField genComponent() {
		return 	new TextField();
	}
}