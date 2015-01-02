package montyPan.groxotype.client.generator;

import java.util.HashMap;

import com.sencha.gxt.widget.core.client.TabItemConfig;

public class TabConfigHelper {
	private static final String NAME = "tabConfig";
	private int autoIndex = 0;
	private HashMap<String, TabItemConfig> configMap = new HashMap<>();

	public String config(TabItemConfig config) {
		//tabConfig 不可省略，所以不用像 H/VLayoutData 那樣判斷是否 default（判斷了也沒啥意義 XD）
		
		for (String key : configMap.keySet()) {
			if (isEqual(config, configMap.get(key))) {
				return key;
			}
		}
		
		String name = NAME + autoIndex++;
		configMap.put(name, config);
		return name;
	}
	
	public String uiWith() {
		StringBuffer result = new StringBuffer();
		for (String name : configMap.keySet()) {
			TabItemConfig config = configMap.get(name);
			result.append("\t" + GenUtil.uiWithHeader(name, TabItemConfig.class) + "\n");
			
			result.append("\t\t<ui:attributes ");
			result.append("text=\"" + config.getText() + "\" ");
			result.append("closable=\"" + config.isClosable() + "\" ");
			result.append("/>\n");
			
			result.append("\t</ui:with>\n");
		}
		
		return result.toString();
	}
	
	private boolean isEqual(TabItemConfig config1, TabItemConfig config2) {
		return config1.isClosable() == config2.isClosable() && config1.getText().equals(config2.getText());
	}
}