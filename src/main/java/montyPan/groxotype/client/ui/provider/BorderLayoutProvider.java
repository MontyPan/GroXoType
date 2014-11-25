package montyPan.groxotype.client.ui.provider;

import montyPan.groxotype.client.ui.AddButton;
import montyPan.groxotype.client.ui.ComponentProvider;
import montyPan.groxotype.client.util.ProviderUtil;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.form.CheckBox;

public class BorderLayoutProvider extends ComponentProvider<BorderLayoutContainer> {
	interface MyUiBinder extends UiBinder<Widget, BorderLayoutProvider> {}
	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	
	@UiField CheckBox east;
	@UiField CheckBox west;
	@UiField CheckBox south;
	@UiField CheckBox north;
	
	@Override
	public String getCategory() {
		return ProviderUtil.LAYOUT;
	}

	@Override
	protected String buttonText() {
		return "Border";
	}
	
	@Override
	public Widget genSettingView() {
		return uiBinder.createAndBindUi(this);
	}

	@Override
	protected BorderLayoutContainer genComponent() {
		BorderLayoutContainer result = new BorderLayoutContainer();
		if(east.getValue()) {
			result.setEastWidget(new AddButton(result), genLayoutData());
		}
		if(west.getValue()) {
			result.setWestWidget(new AddButton(result), genLayoutData());
		}
		if(south.getValue()) {
			result.setSouthWidget(new AddButton(result), genLayoutData());
		}
		if(north.getValue()) {
			result.setNorthWidget(new AddButton(result), genLayoutData());
		}
		result.setCenterWidget(new AddButton(result));
		return result;
	}
	
	private static BorderLayoutData genLayoutData() {
		BorderLayoutData result = new BorderLayoutData();
		result.setSplit(true);
		return result;
	}
}