package montyPan.groxotype.client.ui.provider;

import com.google.gwt.user.client.ui.Widget;

import montyPan.groxotype.client.ui.ComponentProvider;
import montyPan.groxotype.client.util.ProviderUtil;

public class EmptyProvider extends ComponentProvider {

	@Override
	public String getCategory() {
		return ProviderUtil.GROXOTYPE;
	}

	@Override
	protected String buttonText() {
		return "～留白～";
	}

	@Override
	protected Widget genComponent() {
		return null;
	}
}