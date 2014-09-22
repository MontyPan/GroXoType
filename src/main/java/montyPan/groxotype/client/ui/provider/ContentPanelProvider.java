package montyPan.groxotype.client.ui.provider;

import montyPan.groxotype.client.ui.AddButton;
import montyPan.groxotype.client.ui.ComponentProvider;
import montyPan.groxotype.client.util.ProviderUtil;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.widget.client.TextButton;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * {@link ContentPanel} 跟 {@link FramedPanel} 都一樣，所以就共用 provider。
 */
public class ContentPanelProvider extends ComponentProvider {
	interface MyUiBinder extends UiBinder<Widget, ContentPanelProvider> {}
	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	@UiField CheckBox cOrF;
	@UiField TextField headingText;
	@UiField TextField btn1Text;
	@UiField TextField btn2Text;
	
	@Override
	public String getCategory() {
		return ProviderUtil.LAYOUT;
	}

	@Override
	protected String buttonText() {
		return "Framed / ContentPanel";
	}
	
	@Override
	public Widget genSettingView() {
		return uiBinder.createAndBindUi(this);
	}

	@Override
	protected Widget genComponent() {
		ContentPanel result = cOrF.getValue() ? new ContentPanel() : new FramedPanel();
		
		if (headingText.getValue() != null) {
			result.setHeadingText(headingText.getValue());
		} else {
			result.setHeaderVisible(false);
		}
		
		if (btn1Text.getValue() != null) {
			result.addButton(new TextButton(btn1Text.getValue()));
		}
		
		if (btn2Text.getValue() != null) {
			result.addButton(new TextButton(btn2Text.getValue()));
		}
		
		result.add(new AddButton(result));
		return result;
	}
}