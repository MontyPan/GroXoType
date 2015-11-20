package org.groxotype.client.core;

import java.util.ArrayList;
import java.util.HashMap;

import org.groxotype.client.provider.AbstractUiWith;

import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.container.ResizeContainer;

public class UiXmlGenerator {
	private ResizeContainer root;
	private HashMap<String, AbstractUiWith<?>> uiWithMap = new HashMap<>();

	private TagHelper nsHelper = new TagHelper();
//	private LayoutDataHelper ldHelper = new LayoutDataHelper();
//	private TabConfigHelper tabHelper = new TabConfigHelper();

	private ArrayList<String> code = new ArrayList<>();

	public UiXmlGenerator(ResizeContainer widget) {
		this.root = widget;
	}

	public String getCode() {
		addChild(
			1, (Component)root.getWidget(0)
		);

		StringBuffer result = new StringBuffer("<!DOCTYPE ui:UiBinder SYSTEM \"http://dl.google.com/gwt/DTD/xhtml.ent\"> \n");
		result.append("<ui:UiBinder ");
		result.append(nsHelper.xmlns() + ">\n" );

		for (AbstractUiWith<?> uiWith : uiWithMap.values()) {
			result.append(uiWith.genCode());
		}

		for (String line : code) {
			result.append(line + "\n");
		}

		result.append("</ui:UiBinder>");
		return result.toString();
	}

	public void addUiWith(AbstractUiWith<?> uiWith) {
		Object existUiWith = uiWithMap.get(uiWith.getHeader());
		//Refactory 驗證是否正確
		if (existUiWith != null) {
			if (existUiWith.getClass() != uiWith.getClass()) {
				throw new IllegalArgumentException("uiWith.getHeader() 重複");
			}
		}

		uiWithMap.put(uiWith.getHeader(), uiWith);
	}

	public void addStartTag(int level, Component component) {
		code.add(GenUtil.genTab(level) + nsHelper.header(component));
	}

	public void addEndTag(int level, Component component) {
		code.add(GenUtil.genTab(level) + nsHelper.tail(component));
	}

	public void addClosingTag(int level, Component component) {
		code.add(GenUtil.genTab(level) + nsHelper.all(component));
	}

	public <T extends Component> void addChild(int level, T child) {
		AbstractProvider<T> provider = ProviderCenter.getProvider(child);

		//預防沒有 provider 的萬用解
		if (provider == null) {
			this.addClosingTag(level, child);
			return;
		}

		//預防不是 provider 產生出來的 component
		//（例如 ContentPanel 的 button bar 裡頭的 TextButton）
		//所以要到這裡才真正 setAttribute()
		GenUtil.addAttribute(child, provider.genAttributeMap(child));

		try {
			provider.genCode(child, this, level);
		} catch (UnsupportedOperationException e) {
			//provider 沒有 override genCode，所以直接 gen tag
			this.addClosingTag(level, child);
		}
	}

	public void addFakeStartTag(int level, Component component, String name) {
		addFakeStartTag(level, component, name, null);
	}

	public void addFakeStartTag(int level, Component component, String name, HashMap<String, Object> attr) {
		code.add(GenUtil.genTab(level) +
			"<" + nsHelper.getNamespace(component) + ":" + name +
			" " + GenUtil.genAttribute(attr) + ">"
		);
	}

	public void addFakeEndTag(int level, Component component, String name) {
		code.add(GenUtil.genTab(level) + "</" + nsHelper.getNamespace(component) + ":" + name + ">");
	}
}