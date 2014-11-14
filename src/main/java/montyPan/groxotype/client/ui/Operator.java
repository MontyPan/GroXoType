package montyPan.groxotype.client.ui;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.ResizeContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.menu.Menu;

/**
 * 處理 context menu 的 class，套 singleton pattern。
 * 每一個 {@link AddButton} 在 new 的時候都會呼叫 {@link #init(ResizeContainer)}，
 * 但是只有第一個 {@link AddButton} 的 {@link AddButton#getParent() container} 
 * 才會變成 {@link #root}。
 * 這也代表了在最初的 layout 上只能出現一個 {@link AddButton} 的限制。
 */
public class Operator {
	private static OperatorUiBinder uiBinder = GWT.create(OperatorUiBinder.class);
	interface OperatorUiBinder extends UiBinder<Widget, Operator> {}
	
	@UiField TextButton genBtn;
	
	//直接繼承 Menu，uiBinder 回傳 Menu，顯示會正常但是沒辦法 hide()... WTF?
	//所以只好另外再開一個 Menu 了... Orz 
	private Menu menu = new Menu();
	private ResizeContainer root;
	
	private Operator(ResizeContainer container) {
		this.root = container;
		menu.add(uiBinder.createAndBindUi(this));
		root.setContextMenu(menu);
	}
	
	@UiHandler("genBtn")
	void genCode(SelectEvent se) {
		menu.hide();
	}
	
	private static Operator instance;
	public static void init(ResizeContainer root) {
		if (instance == null) {
			instance = new Operator(root);
		}
	}
}