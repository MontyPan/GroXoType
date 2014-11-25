package montyPan.groxotype.client.ui.provider;

import montyPan.groxotype.client.ui.ComponentProvider;
import montyPan.groxotype.client.util.ProviderUtil;

import com.sencha.gxt.widget.core.client.Component;

public class EmptyProvider extends ComponentProvider<Component> {

	@Override
	public String getCategory() {
		return ProviderUtil.GROXOTYPE;
	}

	@Override
	protected String buttonText() {
		return "～留白～";
	}

	@Override
	protected Component genComponent() {
		return null;
	}
}