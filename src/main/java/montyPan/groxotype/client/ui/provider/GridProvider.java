package montyPan.groxotype.client.ui.provider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import montyPan.groxotype.client.ui.ComponentProvider;
import montyPan.groxotype.client.util.ProviderUtil;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class GridProvider extends ComponentProvider {
	interface MyUiBinder extends UiBinder<Widget, GridProvider> {}
	private MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	
	@UiField TextArea csvData;
	
	@Override
	public String getCategory() {
		return ProviderUtil.COMPONENT;
	}

	@Override
	protected String buttonText() {
		return "Grid";
	}
	
	@Override
	public Widget genSettingView() {
		Widget result = uiBinder.createAndBindUi(this);
		//寫在 ui.xml（即使用上 CDATA）會取不到值，原因不明，所以就只好先這樣了...
		StringBuffer sb = new StringBuffer("header1, hader2, header3\n");
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 4; j++) {
				sb.append("data" + i + "-" + j + ",");	
			}
			sb.append("\n");
		}
		csvData.setValue(sb.toString());
		return result;
	}

	@Override
	protected Widget genComponent() {
		String[] allLine = csvData.getValue().split("\n");
		ArrayList<CsvModel> data = new ArrayList<>(allLine.length - 1);
		for (int i = 1; i < allLine.length; i++) {
			data.add(
				new CsvModel(i, Arrays.asList(allLine[i].split(",")))
			);
		}
		
		Grid<CsvModel> result = new Grid<>(
			genListStore(), 
			genColumnModel(Arrays.asList(allLine[0].split(",")))
		);
		result.getStore().addAll(data);
		return result;
	}
	
	private static ListStore<CsvModel> genListStore() {
		return new ListStore<>(new ModelKeyProvider<CsvModel>() {
			@Override
			public String getKey(CsvModel item) {
				return "" + item.getIndex();
			}
		});
	}
	
	private static ColumnModel<CsvModel> genColumnModel(List<String> header) {
		ArrayList<ColumnConfig<CsvModel, ?>> configList = new ArrayList<>();
		for (int i = 0; i < header.size(); i++) {
			final int index = i;
			configList.add(new ColumnConfig<CsvModel, String>(new ValueProvider<CsvModel, String>() {
				@Override
				public String getValue(CsvModel object) {
					return object.getData().size() > index ?
							object.getData().get(index) :
							"";
				}

				@Override
				public void setValue(CsvModel object, String value) {}

				@Override
				public String getPath() { return null; }
			}, 100, header.get(i)));
		}
		
		return new ColumnModel<>(configList);
	}
		
	private class CsvModel {
		private int index;
		private List<String> data;
		public CsvModel(int index, List<String> data) {
			this.index = index;
			this.data = data;
		}
		public int getIndex() { return index; }
		public List<String> getData() { return data; }
	}
}