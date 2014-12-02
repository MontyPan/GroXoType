package montyPan.groxotype.client.ui.provider;

import java.util.HashMap;

import montyPan.groxotype.client.ui.ComponentProvider;
import montyPan.groxotype.client.util.ProviderUtil;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.Radio;
import com.sencha.gxt.widget.core.client.form.TextField;

public class CheckBoxProvider extends ComponentProvider<CheckBox> {
	interface MyUiBinder extends UiBinder<Widget, CheckBoxProvider> {}
	private MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	
	@UiField CheckBox isRadio;
	@UiField CheckBox isCheck;
	@UiField TextField boxLabel;
	
	@Override
	public String getCategory() {
		return ProviderUtil.COMPONENT;
	}

	@Override
	protected String buttonText() {
		return "CheckBox / Radio";
	}
	
	@Override
	public Widget genSettingView() {
		return uiBinder.createAndBindUi(this);
	}

	@Override
	protected CheckBox genComponent() {
		CheckBox result = isRadio.getValue() ? new Radio() : new CheckBox();
		result.setValue(isCheck.getValue());
		result.setBoxLabel(boxLabel.getValue());
		return result;
	}
	
	@Override
	protected HashMap<String, Object> genAttrMap(CheckBox component) {
		HashMap<String, Object> result = new HashMap<>();
		result.put("boxLabel", component.getBoxLabel());
		result.put("value", component.getValue());
		return result;
	}
}