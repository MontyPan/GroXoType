package montyPan.groxotype.client.ui.provider;

import java.util.HashMap;

import montyPan.groxotype.client.ui.AddButton;
import montyPan.groxotype.client.ui.ComponentProvider;
import montyPan.groxotype.client.util.ProviderUtil;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.form.FieldSet;
import com.sencha.gxt.widget.core.client.form.TextField;

public class FieldSetProvider extends ComponentProvider<FieldSet> {
	interface MyUiBinder extends UiBinder<Widget, FieldSetProvider> {}
	private MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	
	@UiField TextField text;
	
	@Override
	public String getCategory() {
		return ProviderUtil.LAYOUT;
	}

	@Override
	protected String buttonText() {
		return "FieldSet";
	}

	@Override
	public Widget genSettingView() {
		return uiBinder.createAndBindUi(this);
	}
	
	@Override
	protected FieldSet genComponent() {
		FieldSet result = new FieldSet();
		result.setHeadingText(text.getValue());
		result.setWidget(new AddButton(result));
		return result;
	}
	
	@Override
	protected HashMap<String, Object> genAttrMap(FieldSet component) {
		HashMap<String, Object> result = new HashMap<>();
		result.put("headingText", component.getHeadingText());
		return result;
	}
}