package montyPan.groxotype.client;

import montyPan.groxotype.client.ui.AddButton;
import montyPan.groxotype.client.util.ProviderUtil;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.container.Viewport;

public class GroXoTypeEP implements EntryPoint {
	@Override
	public void onModuleLoad() {
		ProviderUtil.addAll();
		
		Viewport vp = new Viewport();
		vp.add(new AddButton(vp));
		RootPanel.get().add(vp);
	}
}