/**
 *
 */
package crawler.jra.page;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;

import crawler.jra.dto.RaceDto;
import crawler.jra.dto.RaceUmrnNinDto;
import lombok.Data;
import lombok.NonNull;

/**
 * @author kilim
 *
 */
@Data
public class RaceUmrnNinPage {

	/** Document */
	@NonNull
	private Document document;

	/** レース情報 */
	private RaceDto raceDto;

	/** 馬連リスト */
	private List<RaceUmrnNinDto> raceUmrnNinList;

	public RaceUmrnNinPage parse() {
		return parse(false);
	}

	/**
	 * .
	 * @param force .
	 * @return .
	 */
	public RaceUmrnNinPage parse(boolean force) {
		if (!force && raceUmrnNinList != null) {
			return this;
		}
		String[] headerStr = document.selectFirst("div.header_line span.opt").text().trim().split("（.曜）|\\s");
		this.raceDto = new RaceDto();
		this.raceDto.setKaisaiDtStr(headerStr[0]);
		this.raceDto.setKaisaiNm(headerStr[1]);
		this.raceDto.setRaceNoStr(headerStr[2]);
		this.raceDto.setOddsTmStr(document.selectFirst("div.refresh_line div.time").text()
				.replaceAll("[^0-9時]", "").replaceAll("時", ":"));

		// 馬連 - 人気順
		this.raceUmrnNinList = new ArrayList<>();
		document.select("div#odds_list ul.umaren_list li table tbody tr").forEach(element -> {
			RaceUmrnNinDto dto = new RaceUmrnNinDto();
			dto.setNinkiNo(element.selectFirst("th.pop").text());
			dto.setKumiNo(element.selectFirst("td.num").text());
			dto.setOddsStr(element.selectFirst("td.odds").text());
			this.raceUmrnNinList.add(dto);
		});
		return this;
	}
}
