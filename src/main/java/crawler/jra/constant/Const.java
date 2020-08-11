/**
 *
 */
package crawler.jra.constant;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kilim
 *
 */
public class Const {

	/** JRA トップページ URL */
	public static final String BASE_URL = "https://www.jra.go.jp";

	/** JRA オッズページ URL */
	public static final String ODDS_URL = BASE_URL + "/JRADB/accessO.html";

	public static final int REQUEST_TIMEOUT_MS = 60_000;

	/** 競馬場とコードの Map */
	public static Map<String, String> keibajoNmCdMap;
	static {

		Map<String, String> map = new HashMap<>();
		map.put("東京", "TK");
		map.put("中山", "NK");
		map.put("京都", "KY");
		map.put("阪神", "HN");
		map.put("福島", "FK");
		map.put("新潟", "NG");
		map.put("中京", "CK");
		map.put("小倉", "KK");
		map.put("札幌", "SP");
		map.put("函館", "HK");
		keibajoNmCdMap = Collections.unmodifiableMap(map);
	}

}
