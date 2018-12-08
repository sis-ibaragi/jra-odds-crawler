/**
 *
 */
package crawler.jra.dto;

import java.time.LocalDate;

import crawler.jra.util.ScrapingUtils;
import lombok.Data;

/**
 * @author kilim
 *
 */
@Data
public class KaisaiDto {

	/** . */
	private String kaisaiCd;

	/** . */
	private String kaisaiNm;

	/** . */
	private String kaisaiDtStr;

	/**
	 * .
	 * @return .
	 */
	public String getKaisaiCd() {
		if (this.kaisaiCd != null && !this.kaisaiCd.isEmpty()) {
			return this.kaisaiCd;
		}
		if (this.kaisaiNm == null || this.kaisaiNm.isEmpty() || this.kaisaiDtStr == null
				|| this.kaisaiDtStr.isEmpty()) {
			return null;
		}
		this.kaisaiCd = ScrapingUtils.getKaisaiCdFromKaisaiNm(kaisaiDtStr.substring(0, 4), kaisaiNm);
		return this.kaisaiCd;
	}

	/**
	 * .
	 * @return .
	 */
	public LocalDate getKaisaiDt() {
		return ScrapingUtils.getKaisaiDt(this.kaisaiDtStr);
	}

}
