package montyPan.groxotype.client.generator;

import java.util.ArrayList;

import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.container.ResizeContainer;

public class UiXmlGenerator {
	private ResizeContainer root;
	private TagHelper nsHelper = new TagHelper();
	private LayoutDataHelper ldHelper = new LayoutDataHelper();
	
	public UiXmlGenerator(ResizeContainer widget) {
		this.root = widget;
	}
	
	private String travel(ResizeContainer rootNode, int level) {
		StringBuffer code = new StringBuffer();
		code.append(GenUtil.genTab(level) + nsHelper.header(rootNode) + "\n");
		
		StringBuffer childContext = new StringBuffer();
		for (int i = 0; i < rootNode.getWidgetCount(); i++) {
			Component child = (Component) rootNode.getWidget(i);
			ldHelper.process(child);
			if (child instanceof ResizeContainer) {
				childContext.append(travel((ResizeContainer)child, level + 1));
			} else {
				childContext.append(GenUtil.genTab(level + 1) + nsHelper.all(child) + "\n");
			}
		}
		
		@SuppressWarnings("unchecked")
		ArrayList<String> context = (ArrayList<String>)rootNode.getData(GenUtil.CONTEXT);
		if (context != null) {
			for (String line : context) {
				if (line.contains(GenUtil.NAMESPACE)) {
					line = line.replace(GenUtil.NAMESPACE, nsHelper.getNamespace(rootNode));
				}
				if (line.contains(GenUtil.CHILD)) {
					//TODO 縮排對齊的問題
					line = line.replace(GenUtil.CHILD, childContext);
				}
				line = GenUtil.genTab(level) + line + "\n";
				code.append(line);
			}
		} else {
			code.append(childContext);
		}
		
		code.append(GenUtil.genTab(level) + nsHelper.tail(rootNode) + "\n");
		return code.toString();
	}
	
	public String getCode() {
		String componentContext = travel((ResizeContainer)root.getWidget(0), 1);
		//要先跑過一遍，tagHelper 才會有資料
		
		StringBuffer result = new StringBuffer("<!DOCTYPE ui:UiBinder SYSTEM \"http://dl.google.com/gwt/DTD/xhtml.ent\"> \n");
		result.append("<ui:UiBinder ");
		result.append(nsHelper.xmlns() + ">\n" );
		result.append(ldHelper.uiWith());
		result.append(componentContext);
		result.append("</ui:UiBinder>");
		return result.toString();
	}
}