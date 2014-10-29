package montyPan.groxotype.client.ui.provider;

import montyPan.groxotype.client.ui.AddButton;
import montyPan.groxotype.client.ui.ComponentProvider;
import montyPan.groxotype.client.util.ProviderUtil;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.form.FieldSet;
import com.sencha.gxt.widget.core.client.form.TextField;

public class FieldSetProvider extends ComponentProvider {
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
	protected Widget genComponent() {
		FieldSet result = new FieldSet();
		result.setHeadingText(text.getValue());
		result.setWidget(new AddButton(result));
		return result;
	}
}