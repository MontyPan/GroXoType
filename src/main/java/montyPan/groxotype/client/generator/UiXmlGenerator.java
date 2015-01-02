package montyPan.groxotype.client.generator;

import java.util.ArrayList;

import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.container.ResizeContainer;

public class UiXmlGenerator {
	private ResizeContainer root;
	private TagHelper nsHelper = new TagHelper();
	private LayoutDataHelper ldHelper = new LayoutDataHelper();
	private TabConfigHelper tabHelper = new TabConfigHelper();
	
	public UiXmlGenerator(ResizeContainer widget) {
		this.root = widget;
	}

	private String processChild(Component child, int level) {
		ldHelper.process(child);
		if (child instanceof ResizeContainer) {
			return processContainer((ResizeContainer)child, level);
		} else if (child instanceof TabPanel) {
			return processTabPanel((TabPanel) child, level);
		} else {
			return GenUtil.genTab(level) + nsHelper.all(child) + "\n";
		}
	}
	
	private String processContainer(ResizeContainer rootNode, int level) {
		StringBuffer code = new StringBuffer();
		code.append(GenUtil.genTab(level) + nsHelper.header(rootNode) + "\n");
		
		StringBuffer childContext = new StringBuffer();
		for (int i = 0; i < rootNode.getWidgetCount(); i++) {
			childContext.append(
				processChild(
					(Component) rootNode.getWidget(i), level + 1
				)
			);
		}
		
		@SuppressWarnings("unchecked")
		ArrayList<String> context = (ArrayList<String>)rootNode.getData(GenUtil.CONTEXT);
		if (context != null) {
			for (String line : context) {
				//XXX 這裡假設偽 element 跟 CHILD 不會在同一行
				if (line.contains(GenUtil.NAMESPACE)) {
					line = line.replace(GenUtil.NAMESPACE, nsHelper.getNamespace(rootNode));
					//讓 genContent() 每一行開頭可以少打一個 \t，所以這裡幫忙補上
					code.append(GenUtil.genTab(level + 1) + line + "\n");
					continue;
				}
				
				int childIndex = line.indexOf(GenUtil.CHILD);
				if (childIndex != -1) {
					String lineStart = line.substring(0, childIndex);
					for (String childLine : childContext.toString().split("\n")) {
						//因為 child 的 level 本來就會比 parent 多一個，這裡就扣回來
						code.append(GenUtil.genTab(level - 1) + lineStart + childLine + "\n");
					}
				}
			}
		} else {
			code.append(childContext);
		}
		
		code.append(GenUtil.genTab(level) + nsHelper.tail(rootNode) + "\n");
		return code.toString();
	}
	
	private String processTabPanel(TabPanel tabPanel, int level) {
		StringBuffer result = new StringBuffer();
		result.append(GenUtil.genTab(level) + nsHelper.header(tabPanel) + "\n");
		for (int i = 0; i < tabPanel.getWidgetCount(); i++) {
			Component child = (Component)tabPanel.getWidget(i);
			result.append(GenUtil.genTab(level + 1) + "<" + nsHelper.getNamespace(tabPanel) + ":child config=\"{" + tabHelper.config(tabPanel.getConfig(child)) + "}\">\n");
			result.append(processChild(child, level + 2));
			result.append(GenUtil.genTab(level + 1) + "</" + nsHelper.getNamespace(tabPanel) + ":child>\n");
		}
		result.append(GenUtil.genTab(level) + nsHelper.tail(tabPanel) + "\n");
		return result.toString();
	}
	
	public String getCode() {
		String componentContext = processChild(
			(Component)root.getWidget(0), 1
		);
		//要先跑過一遍，tagHelper 才會有資料
		
		StringBuffer result = new StringBuffer("<!DOCTYPE ui:UiBinder SYSTEM \"http://dl.google.com/gwt/DTD/xhtml.ent\"> \n");
		result.append("<ui:UiBinder ");
		result.append(nsHelper.xmlns() + ">\n" );
		
		result.append(ldHelper.uiWith());
		result.append(tabHelper.uiWith());
		
		result.append(componentContext);
		result.append("</ui:UiBinder>");
		return result.toString();
	}
}