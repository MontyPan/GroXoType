> # GroXoType = GXT + Prototype #

GroXoType？
-----------
GroXoType 是以 GXT 為基底的一個 prototype 系統。
利用 GroXoType 可以快速拼湊出畫面，而不需要寫半行程式碼。
（學理上是這樣講，實際上還是需要有人寫 provider [遠目]）


如何使用？
----------
1. 在 `wtf.gwt.xml` 加入

		<inherits name="montyPan.groxotype.GroXoType" />
		
1. `EntryPoint` 可以參考 `GroXoTypeEP`，像這樣：

		public class GroXoTypeEP implements EntryPoint {
			@Override
			public void onModuleLoad() {
				ProviderUtil.addAll();
				
				Viewport vp = new Viewport();
				vp.add(new AddButton(vp));
				RootPanel.get().add(vp);
			}
		}

	重點是用 `ProviderUtil.addAll()` 加掛 GroXoType 提供的 GXT component provider，
	然後在指定的 GXT layout container 加上 `AddButton`，
	後續就交給 `AddButton` 處理。


GroXoType 的運作原理
--------------------
1. 當 `AddButton` 被按下時，會跳出 `ComponentSelector` 的視窗。
1. `ComponentSelector` 會顯示有載入 `ComponentProvider` 的按鈕。
1. 當 `ComponentProvider` 的按鈕被按下時，程式會將一開始的 `AddButton` 替換成 `ComponentProvider.genComponent()` 的回傳值。

<strike>按照慣例，這些步驟中包含了一些黑魔法......</strike>


如何撰寫 provider？
-------------------
1. 新增一個繼承 `ComponentProvider` 的 class，假設叫做 `WtfProvider`。
1. 實作：
	* `getCategory()`：分類名稱。在 `ComponentSelector` 當中，相通的分類名稱會擺在同一個 accordion layout 下。
	* `buttonText()`：按鈕名稱。
	* `genComponent()`：實際產生出來、替換 `AddButton` 的 component instance。
1. 如果 `genComponent()` 需要一些參數設定，則另外要 override `genSettingView()`。
	如果 `genSettingView()` 的回傳值不是 null，則會跳出一個 `Dialog` 顯示 `getSettingView()` 所產生的 widget。
1. 在 `AddButton` 開始運作之前，把 `WtfProvider` 加到 `ComponentSelector` 中：

		ComponentSelector.addProvider(new WtfProvider());
