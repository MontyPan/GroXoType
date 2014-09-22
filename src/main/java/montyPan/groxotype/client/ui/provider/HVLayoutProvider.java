package montyPan.groxotype.client.ui.provider;

import java.util.ArrayList;

import montyPan.groxotype.client.ui.AddButton;
import montyPan.groxotype.client.ui.ComponentProvider;
import montyPan.groxotype.client.ui.editor.HVLayoutDataEditor;
import montyPan.groxotype.client.util.ProviderUtil;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.form.IntegerField;

/**
 * {@link HorizontalLayoutContainer} 跟 {@link VerticalLayoutContainer} 實在太相了，
 * 所以就用同一個 provider，透過 constructor 傳入 boolean 來區別。
 */
public class HVLayoutProvider extends ComponentProvider {
	interface HVLayoutProviderUiBinder extends UiBinder<Widget, HVLayoutProvider> {}
	private static HVLayoutProviderUiBinder uiBinder = GWT.create(HVLayoutProviderUiBinder.class);
	
	@UiField IntegerField childAmount;
	@UiField VerticalLayoutContainer layoutDataPanel;
	
	private ArrayList<HVLayoutDataEditor> editorList = new ArrayList<>();
	private boolean horizontal;
	
	public HVLayoutProvider(boolean isHorizontal) {
		this.horizontal = isHorizontal;
	}
	
	@Override
	protected String buttonText() {
		return horizontal ? "Horizontal" : "Vertical";
	}
	
	@Override
	public String getCategory() {
		return ProviderUtil.LAYOUT;
	}
	
	@Override
	protected Widget genSettingView() {
		return uiBinder.createAndBindUi(this);
	}
	
	@Override
	protected Widget genComponent() {
		if (horizontal) {
			HorizontalLayoutContainer hlc = new HorizontalLayoutContainer();
			for (int i = 0; i < childAmount.getValue(); i++) {
				hlc.add(new AddButton(hlc), editorList.get(i).getHLayoutData());
			}
			return hlc;
		} else {
			VerticalLayoutContainer vlc = new VerticalLayoutContainer();
			for (int i = 0; i < childAmount.getValue(); i++) {
				vlc.add(new AddButton(vlc), editorList.get(i).getVLayoutData());
			}
			return vlc;	
		}
	}
	
	@UiHandler("childAmount")
	void childChange(ValueChangeEvent<Integer> vce) {
		layoutDataPanel.clear();
		for (int i = 0; i < childAmount.getValue(); i++) {
			HVLayoutDataEditor editor = new HVLayoutDataEditor(i);
			layoutDataPanel.add(editor);
			editorList.add(editor);
		}
	}
}