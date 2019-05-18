/**
 *
 */
package crawler.jra.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import crawler.jra.constant.Const;

/**
 * @author kilim
 *
 */
public class ScrapingUtils {

	/** . */
	private static Pattern doActionPtn = Pattern.compile("[a-z\\s]*doAction\\('.+',\\s*'(.+)'");

	/** . */
	private static Pattern kaisaiNmPtn = Pattern.compile("(\\d+)回(\\D+)(\\d+)日");

	/**
	 * .
	 */
	private ScrapingUtils() {
		// コンストラクタを隠蔽
	}

	public static String getCnameFromDoAction(String doAction) {
		Matcher m = doActionPtn.matcher(doAction);
		if (!m.find()) {
			throw new IllegalArgumentException(String.format("`doAction` was unexpected... doAction = %s", doAction));
		}
		return m.group(1);
	}

	public static String getKaisaiCdFromKaisaiNm(String yyyy, String kaisaiNm) {
		Matcher m = kaisaiNmPtn.matcher(kaisaiNm);
		if (!m.find()) {
			throw new IllegalArgumentException(
					String.format("Kaisai name was unexpected... yyyy = %s, kaisaiNm = %s  ", yyyy, kaisaiNm));
		}
		return yyyy
				+ String.format("%02d", Integer.valueOf(m.group(1)))
				+ Const.keibajoNmCdMap.get(m.group(2))
				+ String.format("%02d", Integer.valueOf(m.group(3)));
	}

	public static LocalDate getKaisaiDt(String kaisaiDtStr) {
		if (kaisaiDtStr == null || kaisaiDtStr.isEmpty()) {
			return null;
		}
		return LocalDate.parse(kaisaiDtStr.replaceAll("（.）", ""), DateTimeFormatter.ofPattern("yyyy年M月d日"));
	}
}
