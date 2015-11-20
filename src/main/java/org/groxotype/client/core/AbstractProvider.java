package org.groxotype.client.core;

import java.util.HashMap;
import java.util.List;

import org.groxotype.client.ui.ComponentSelector;

import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.DialogHideHandler;

public abstract class AbstractProvider<T extends Component> {
	private final Dialog dialog = new Dialog();

	protected AbstractProvider() {
		dialog.setHideOnButtonClick(true);
		dialog.setModal(true);
		dialog.addDialogHideHandler(new DialogHideHandler() {
			@Override
			public void onDialogHide(DialogHideEvent event) {
				if (event.getHideButton() == PredefinedButton.OK) {
					ComponentSelector.replace(genComponent());
				}
			}
		});
	}

	public final void whenSelect() {
		ComponentSelector.hide();
		Widget settingView = genSettingView();
		if (settingView != null) {
			showSetting(settingView);
		} else {
			ComponentSelector.replace(genComponent());
		}
	}

	public abstract Class<T> getClazz();
	public abstract String getCategory();
	protected abstract T genComponent();

	public void genCode(T component, UiXmlGenerator generator, int level) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @return 最終出現在 ui.xml 的 attribute map，
	 * 	key 是 attribute name、value 是 attribute value。
	 * 	請注意最後產生字串時是取 value.toString()。
	 */
	public HashMap<String, Object> genAttributeMap(T component) {
		return null;
	}

	public String getName() {
		return ClassUtil.className(getClazz().getName());
	}

	protected Widget genSettingView() {
		return null;
	}

	public final void showSetting(Widget settingWidget) {
		dialog.clear();
		dialog.add(settingWidget);
		dialog.show();
	}

	/**
	 * 如果一個 provider 可以產生兩個以上的 component（例如 ContentPanel / FramedPanel），
	 * 則用 override 以註記額外的 component class。
	 */
	public List<Class<? extends T>> getExClass() {
		return null;
	}
}