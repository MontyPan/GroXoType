package montyPan.groxotype.client.ui;

import java.util.ArrayList;
import java.util.HashMap;

import montyPan.groxotype.client.generator.GenUtil;

import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.DialogHideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

public abstract class ComponentProvider<T extends Component> {
	private final Dialog dialog = new Dialog();
	private final TextButton button = new TextButton();
	
	protected ComponentProvider() {
		dialog.setHideOnButtonClick(true);
		dialog.setModal(true);
		
		button.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				ComponentSelector.hide();
				Widget settingView = genSettingView();
				if (settingView != null) {
					showSetting(settingView);
				} else {
					ComponentSelector.replace(gen());
				}
			}
		});
		
		dialog.addDialogHideHandler(new DialogHideHandler() {
			@Override
			public void onDialogHide(DialogHideEvent event) {
				if (event.getHideButton() == PredefinedButton.OK) {
					ComponentSelector.replace(gen());
				}
			}
		});
	}

	private final Component gen() {
		T result = genComponent();
		GenUtil.setAttribute(result, genAttrMap(result));
		GenUtil.setContext(result, genContext());
		return result;
	}

	public abstract String getCategory();
	protected abstract String buttonText();
	protected abstract T genComponent();

	public TextButton getButton() {
		button.setText(buttonText());
		return button;
	}
	
	protected Widget genSettingView() {
		return null;
	}

	/**
	 * TODO 正在尋求更簡單的寫法... [淚目]
	 * @param component 就是 {@link #genComponent()} 的回傳值
	 * @return 最終出現在 ui.xml 的 attribute map，key 是 attribute name、value 是 attribute value。
	 * 	請注意最後產生字串時是取 value.toString()。
	 */
	protected HashMap<String, Object> genAttrMap(T component) {
		return null;
	}

	/**
	 * @return list 的內容是以行為單位的 context 字串，
	 * 	基本上只要考慮與 parent tag 的縮排深度即可，整體 XML 的縮排深度不用擔心。
	 */
	protected ArrayList<String> genContext() {
		return null;
	}
	
	public void showSetting(Widget settingWidget) {
		dialog.clear();
		dialog.add(settingWidget);
		dialog.show();
	}
}