/**
 *
 */
package crawler.jra.dto;

import java.math.BigDecimal;

import lombok.Data;

/**
 * @author kilim
 *
 */
@Data
public class RaceUmrnNinDto {

	private String ninkiNo;

	private String kumiNo;

	private String oddsStr;

	private String umaNo1;

	private String umaNo2;

	public String getUmaNo1() {
		if(this.umaNo1 != null && !this.umaNo1.isEmpty()) {
			return this.umaNo1;
		}
		if(this.kumiNo == null || this.kumiNo.isEmpty()) {
			return null;
		}
		String[] umaNos = this.kumiNo.split("-");
		this.umaNo1 = umaNos[0];
		this.umaNo2 = umaNos[1];
		return this.umaNo1;
	}

	public String getUmaNo2() {
		if(this.umaNo2 != null && !this.umaNo2.isEmpty()) {
			return this.umaNo2;
		}
		if(this.kumiNo == null || this.kumiNo.isEmpty()) {
			return null;
		}
		String[] umaNos = this.kumiNo.split("-");
		this.umaNo1 = umaNos[0];
		this.umaNo2 = umaNos[1];
		return this.umaNo1;
	}

	public BigDecimal getOdds() {
		if (this.oddsStr == null || this.oddsStr.isEmpty()) {
			return null;
		}
		try {
			return new BigDecimal(this.oddsStr);
		} catch (NumberFormatException e) {
			return null;
		}
	}
}
