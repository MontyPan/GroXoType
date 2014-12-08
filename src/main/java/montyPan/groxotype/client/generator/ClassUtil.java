package montyPan.groxotype.client.generator;

/**
 * 處理 {@link Class#getName()} 的 utility
 */
public class ClassUtil {
	/**
	 * @return 最後一個「.」以後的 substring
	 */
	public static String className(String fullName) {
		fullName = fullName(fullName);
		return fullName.substring(fullName.lastIndexOf('.') + 1);
	}
	
	/**
	 * @return 最後一個「.」以前的 substring
	 */
	public static String packageName(String fullName) {
		fullName = fullName(fullName);
		return fullName.substring(0, fullName.lastIndexOf('.'));
	}
	
	/**
	 * @return 將有 $ 的 class name 轉成標準的 class name
	 */
	public static String fullName(String fullName) {
		return fullName.replace("$", ".");
	}
}