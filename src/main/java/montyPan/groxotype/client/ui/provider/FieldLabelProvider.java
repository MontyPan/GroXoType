package montyPan.groxotype.client.ui.provider;

import java.util.Arrays;

import montyPan.groxotype.client.ui.AddButton;
import montyPan.groxotype.client.ui.ComponentProvider;
import montyPan.groxotype.client.util.ProviderUtil;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.sencha.gxt.widget.core.client.form.IntegerField;
import com.sencha.gxt.widget.core.client.form.SimpleComboBox;
import com.sencha.gxt.widget.core.client.form.TextField;

public class FieldLabelProvider extends ComponentProvider {
	interface MyUiBinder extends UiBinder<Widget, FieldLabelProvider> {}
	private MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	
	@UiField(provided=true) SimpleComboBox<LabelAlign> labelAlign = new SimpleComboBox<>(new LabelProvider<LabelAlign>() {
		@Override
		public String getLabel(LabelAlign item) {
			return item.toString();
		}
	});
	@UiField TextField text;
	@UiField IntegerField labelWidth;
	
	public FieldLabelProvider() {
		labelAlign.add(Arrays.asList(LabelAlign.values()));
	}
	
	@Override
	public String getCategory() {
		return ProviderUtil.LAYOUT;
	}

	@Override
	protected String buttonText() {
		return "FieldLabel";
	}
	
	@Override
	public Widget genSettingView() {
		labelAlign.setValue(LabelAlign.LEFT);
		return uiBinder.createAndBindUi(this);
	}
	
	@Override
	protected Component genComponent() {
		FieldLabel result = new FieldLabel();
		result.setText(text.getValue());
		result.setLabelAlign(labelAlign.getValue());
		result.setLabelWidth(labelWidth.getValue());
		result.setWidget(new AddButton(result));
		return result;
	}
}