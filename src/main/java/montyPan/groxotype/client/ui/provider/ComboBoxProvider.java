package montyPan.groxotype.client.ui.provider;

import java.util.Arrays;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.form.StringComboBox;
import com.sencha.gxt.widget.core.client.form.TextArea;

import montyPan.groxotype.client.ui.ComponentProvider;
import montyPan.groxotype.client.util.ProviderUtil;

public class ComboBoxProvider extends ComponentProvider {
	interface MyUiBinder extends UiBinder<Widget, ComboBoxProvider> {}
	private MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	
	@UiField TextArea data;
	
	@Override
	public String getCategory() {
		return ProviderUtil.COMPONENT;
	}

	@Override
	protected String buttonText() {
		return "ComboBox";
	}
	
	@Override
	public Widget genSettingView() {
		Widget result = uiBinder.createAndBindUi(this);
		data.setValue("data1\ndata2\ndata3");
		return result;
	}

	@Override
	protected Widget genComponent() {
		StringComboBox result = new StringComboBox(
			Arrays.asList(data.getValue().split("\n"))
		);
		return result;
	}

}
