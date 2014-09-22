package montyPan.groxotype.client.ui;

import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.DialogHideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

public abstract class ComponentProvider {
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
					ComponentSelector.replace(genComponent());
				}
			}
		});
		
		dialog.addDialogHideHandler(new DialogHideHandler() {
			@Override
			public void onDialogHide(DialogHideEvent event) {
				if (event.getHideButton() == PredefinedButton.OK) {
					ComponentSelector.replace(genComponent());
				}		
			}
		});
	}

	public abstract String getCategory();
	protected abstract String buttonText();
	protected abstract Widget genComponent();

	public TextButton getButton() {
		button.setText(buttonText());
		return button;
	}
		
	protected Widget genSettingView() {
		return null;
	}
	
	public void showSetting(Widget settingWidget) {
		dialog.clear();
		dialog.add(settingWidget);
		dialog.show();
	}
}