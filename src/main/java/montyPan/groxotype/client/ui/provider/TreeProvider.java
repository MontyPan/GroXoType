package montyPan.groxotype.client.ui.provider;

import montyPan.groxotype.client.ui.ComponentProvider;
import montyPan.groxotype.client.util.ProviderUtil;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.tree.Tree;

public class TreeProvider extends ComponentProvider<Tree<String, String>> {
	interface MyUiBinder extends UiBinder<Widget, TreeProvider> {}
	private MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	
	@UiField TextArea data;
	
	@Override
	public String getCategory() {
		return ProviderUtil.COMPONENT;
	}

	@Override
	protected String buttonText() {
		return "Tree";
	}
	
	@Override
	public Widget genSettingView() {
		Widget result = uiBinder.createAndBindUi(this);
		//寫在 ui.xml（即使用上 CDATA）會取不到值，原因不明，所以就只好先這樣了...
		StringBuffer sb = new StringBuffer();
		sb.append("root\n");
		sb.append("\tlevel1\n");
		sb.append("\t\tlevel1-1\n");
		sb.append("\t\tlevel1-2\n");
		sb.append("\t\tlevel1-3\n");
		sb.append("\tlevel2\n");
		sb.append("\t\tlevel2-1\n");
		sb.append("\t\tlevel2-2\n");
		sb.append("\tlevel3\n");
		sb.append("\t\tlevel3-1\n");
		data.setValue(sb.toString());
		return result;
	}

	@Override
	protected Tree<String, String> genComponent() {
		TreeStore<String> store = new TreeStore<>(new ModelKeyProvider<String>() {
			@Override
			public String getKey(String item) {
				return item;
			}
		});
		
		String[] allLine = data.getValue().replace("    ", "\t").split("\n");
		for (int i = 0; i < allLine.length; i++) {
			if (allLine[i].startsWith("\t")) { continue; }
			if (store.findModel(allLine[i]) == null) {
				store.add(allLine[i]);
			}
			i = build(allLine[i], 1, i + 1, store, allLine) - 1;	//-1 抵銷 i++
		}
		
		Tree<String, String> result = new Tree<>(
			store,
			new ValueProvider<String, String>() {
				@Override
				public String getValue(String object) {
					return object.trim();
				}
				@Override public void setValue(String object, String value) {}
				@Override public String getPath() { return null; }
			}
		);
		return result;
	}
	
	private static int build(String nowRoot, int level, int index, TreeStore<String> store, String[] allLine) {
		for (int i = index; i < allLine.length; i++) {
			String nowNode = allLine[i];
			
			//現在這個炸了，下次要從這個開始看起
			if (!nowNode.startsWith(genLineHeader(level))) { return i; }

			if (store.findModel(nowNode) == null) {
				store.add(nowRoot, nowNode);
			}
			
			//試試看下一行是不是小孩
			i = build(nowNode, level + 1, i + 1, store, allLine) - 1;	//-1 抵銷 i++
		}
		return allLine.length;
	}
	
	private static String genLineHeader(int level) {
		StringBuffer result = new StringBuffer(level);
		for (int i = 0; i < level; i++) {
			result.append('\t');
		}
		return result.toString();
	}
}