package montyPan.groxotype.client.generator;

import java.util.HashMap;

import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;

public class LayoutDataHelper {
	private static final String VLD = "vLD";
	private int vldIndex = 0;
	private HashMap<String, VerticalLayoutData> vldMap = new HashMap<>();
	
	private static final String HLD = "hLD";
	private int hldIndex = 0;
	private HashMap<String, HorizontalLayoutData> hldMap = new HashMap<>();
	
	private static final String BLD = "bLD";
	private int bldIndex = 0;
	private HashMap<String, BorderLayoutData> bldMap = new HashMap<>();

	public void process(Component component) {
		Object ld = component.getLayoutData();
		if (ld == null) { return; }

		HashMap<String, Object> attrMap = component.getData(GenUtil.ATTRIBUTE);
		if (attrMap == null) {
			attrMap = new HashMap<>();
			GenUtil.setAttribute(component, attrMap);
		}
		
		String ldValue = null;
		if (ld instanceof VerticalLayoutData) {
			ldValue = vertical((VerticalLayoutData)ld);
		} else if (ld instanceof HorizontalLayoutData) {
			ldValue = horizontal((HorizontalLayoutData)ld);
		}
		//BorderLayoutData 無法在這裡直接處理
		
		if (ldValue == null) { return; }
		attrMap.put("layoutData", "{" + ldValue + "}");
	}

	private String vertical(VerticalLayoutData ld) {
		if (isDefaultSetting(ld)) { return null; }
		
		for (String key : vldMap.keySet()) {
			if (isEqual(ld, vldMap.get(key))) {
				return key;
			}
		}
		
		String name = VLD + vldIndex++;
		vldMap.put(name, ld);
		return name;
	}
	
	private String horizontal(HorizontalLayoutData ld) {
		if (isDefaultSetting(ld)) { return null; }
		
		for (String key : hldMap.keySet()) {
			if (isEqual(ld, hldMap.get(key))) {
				return key;
			}
		}
		
		String name = HLD + hldIndex++;
		hldMap.put(name, ld);
		return name;
	}
	
	public String border(BorderLayoutData ld) {
		//BorderLayoutData 基本上一定要給，所以不判斷 default、也不判斷重複
		String name = BLD + bldIndex++;
		bldMap.put(name, ld);
		return name;
	}

	public String uiWith() {
		StringBuffer result = new StringBuffer();
		for (String name : vldMap.keySet()) {
			VerticalLayoutData ld = vldMap.get(name);
			result.append("\t" + GenUtil.uiWithHeader(name, ld.getClass()) + "\n");

			result.append("\t\t<ui:attributes ");
			result.append(ld.getWidth() == -1 ? "" : "width=\"" + ld.getWidth() + "\" ");
			result.append(ld.getHeight() == -1 ? "" : "height=\"" + ld.getHeight() + "\" ");
			result.append("/>\n");
			
			result.append("\t</ui:with>\n");
		}
		
		for (String name : hldMap.keySet()) {
			HorizontalLayoutData ld = hldMap.get(name);
			result.append("\t" + GenUtil.uiWithHeader(name, ld.getClass()) + "\n");
			
			result.append("\t\t<ui:attributes ");
			result.append(ld.getWidth() == -1 ? "" : "width=\"" + ld.getWidth() + "\" ");
			result.append(ld.getHeight() == -1 ? "" : "height=\"" + ld.getHeight() + "\" ");
			result.append("/>\n");
			
			result.append("\t</ui:with>\n");
		}
		
		for (String name : bldMap.keySet()) {
			BorderLayoutData ld = bldMap.get(name);
			result.append("\t" + GenUtil.uiWithHeader(name, ld.getClass()) + "\n");
			
			result.append("\t\t<ui:attributes ");
			result.append("size=\"" + ld.getSize() + "\" ");
			result.append("/>\n");
			
			result.append("\t</ui:with>\n");
		}
		
		return result.toString();
	}
		
	private static final VerticalLayoutData defaultVLD = new VerticalLayoutData(-1, -1);
	private static boolean isEqual(VerticalLayoutData vld1, VerticalLayoutData vld2) {
		//XXX 因為目前也沒有給 margin，所以就跳過 margin 的判斷  [茶]
		return vld1.getWidth() == vld2.getWidth() && vld1.getHeight() == vld2.getHeight();
	}
	
	private static boolean isDefaultSetting(VerticalLayoutData vld) {
		return isEqual(vld, defaultVLD);
	}

	private static final HorizontalLayoutData defaultHLD = new HorizontalLayoutData(-1, -1);
	private static boolean isEqual(HorizontalLayoutData hld1, HorizontalLayoutData hld2) {
		//XXX 因為目前也沒有給 margin，所以就跳過 margin 的判斷  [茶]
		return hld1.getWidth() == hld2.getWidth() && hld1.getHeight() == hld2.getHeight();
	}

	private static boolean isDefaultSetting(HorizontalLayoutData hld) {
		return isEqual(hld, defaultHLD);
	}
}