package org.groxotype.client;

import org.groxotype.client.core.ProviderCenter;
import org.groxotype.client.ui.AddButton;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.container.Viewport;

public class GroXoTypeEP implements EntryPoint {
	@Override
	public void onModuleLoad() {
		ProviderCenter.addAll();
		Viewport vp = new Viewport();
		vp.add(new AddButton(vp));
		RootPanel.get().add(vp);
	}
}
