/**
 *
 */
package crawler.jra.dto;

import java.math.BigDecimal;
import java.util.Optional;

import lombok.Data;

/**
 * @author kilim
 *
 */
@Data
public class RaceTnpkNinDto {

	/** 人気 */
	private String ninkiNo;

	/** 枠番 */
	private String wakuNo;

	/** 馬番 */
	private String umaNo;

	/** 馬名 */
	private String umaNm;

	/** 騎手名 */
	private String jockeyNm;

	/** 単勝 */
	private String tanOddsStr;

	/** 複勝（下限） */
	private String fukuOddsMinStr;

	/** 複勝（上限） */
	private String fukuOddsMaxStr;

	public BigDecimal getTanOdds() {
		return getDecimalOdds(this.tanOddsStr);
	}

	public BigDecimal getFukuOddsMin() {
		return getDecimalOdds(this.fukuOddsMinStr);
	}

	public BigDecimal getFukuOddsMax() {
		return getDecimalOdds(this.fukuOddsMaxStr);
	}

	public BigDecimal getFukuOddsMax4Sort() {
		return Optional.ofNullable(getDecimalOdds(this.fukuOddsMaxStr)).orElse(BigDecimal.valueOf(9999L));
	}

	private static BigDecimal getDecimalOdds(String oddsStr) {
		if (oddsStr == null || oddsStr.isEmpty()) {
			return null;
		}
		try {
			return new BigDecimal(oddsStr);
		} catch (NumberFormatException e) {
			return null;
		}
	}
}
