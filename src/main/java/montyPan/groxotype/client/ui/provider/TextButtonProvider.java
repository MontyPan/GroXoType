package montyPan.groxotype.client.ui.provider;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.form.TextField;

import montyPan.groxotype.client.ui.ComponentProvider;
import montyPan.groxotype.client.util.ProviderUtil;

public class TextButtonProvider extends ComponentProvider {
	interface MyUiBinder extends UiBinder<Widget, TextButtonProvider> {}
	private MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	
	@UiField TextField text;
	
	@Override
	public String getCategory() {
		return ProviderUtil.COMPONENT;
	}

	@Override
	protected String buttonText() {
		return "TextButton";
	}
	
	@Override
	public Widget genSettingView() {
		return uiBinder.createAndBindUi(this);
	}

	@Override
	protected Widget genComponent() {
		return new TextButton(text.getValue());
	}
}