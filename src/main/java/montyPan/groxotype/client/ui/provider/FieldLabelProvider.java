package montyPan.groxotype.client.ui.provider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import montyPan.groxotype.client.generator.GenUtil;
import montyPan.groxotype.client.generator.TagHelper;
import montyPan.groxotype.client.ui.AddButton;
import montyPan.groxotype.client.ui.ComponentProvider;
import montyPan.groxotype.client.util.ProviderUtil;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.sencha.gxt.widget.core.client.form.IntegerField;
import com.sencha.gxt.widget.core.client.form.SimpleComboBox;
import com.sencha.gxt.widget.core.client.form.TextField;

public class FieldLabelProvider extends ComponentProvider<FieldLabel> {
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
	protected FieldLabel genComponent() {
		FieldLabel result = new FieldLabel();
		result.setText(text.getValue());
		result.setLabelAlign(labelAlign.getValue());
		result.setLabelWidth(labelWidth.getValue());
		result.setWidget(new AddButton(result));
		return result;
	}
	

	@Override
	protected HashMap<String, Object> genAttrMap(FieldLabel component) {
		HashMap<String, Object> result = new HashMap<>();
		String text = component.getText();
		result.put("text", text.substring(0, text.length() - component.getLabelSeparator().length()));
		result.put("labelAlign", component.getLabelAlign());
		result.put("labelWidth", component.getLabelWidth());
		return result;
	}
	
	@Override
	protected ArrayList<String> genContext() {
		ArrayList<String> result = new ArrayList<>();
		result.add(TagHelper.fakeHeader("widget"));
		result.add("\t" + GenUtil.CHILD);
		result.add(TagHelper.fakeTail("widget"));
		return result;
	}
}