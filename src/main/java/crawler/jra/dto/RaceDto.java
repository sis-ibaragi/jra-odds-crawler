/**
 *
 */
package crawler.jra.dto;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import crawler.jra.util.ScrapingUtils;
import lombok.Data;

/**
 * @author kilim
 *
 */
@Data
public class RaceDto {

	/** . */
	private String kaisaiCd;

	/** . */
	private String kaisaiDtStr;

	/** . */
	private String kaisaiNm;

	/** . */
	private String raceNoStr;

	/** . */
	private String oddsTmStr;

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
	public String getRaceNo() {
		return Optional.ofNullable(this.raceNoStr).orElse(this.raceNoStr).replaceAll("レース", "");
	}

	/**
	 * .
	 * @return .
	 */
	public LocalTime getOddsTm() {
		if (this.oddsTmStr == null || this.oddsTmStr.isEmpty()) {
			return null;
		}
		return LocalTime.parse(oddsTmStr,DateTimeFormatter.ofPattern("H:m"));
	}
}
