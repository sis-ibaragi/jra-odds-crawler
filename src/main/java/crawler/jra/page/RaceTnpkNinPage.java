/**
 *
 */
package crawler.jra.page;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jsoup.nodes.Document;

import crawler.jra.dto.RaceDto;
import crawler.jra.dto.RaceTnpkNinDto;
import lombok.Data;
import lombok.NonNull;

/**
 * @author kilim
 *
 */
@Data
public class RaceTnpkNinPage {

	/** Document */
	@NonNull
	private Document document;

	/** レース情報 */
	private RaceDto raceDto;

	/** 単勝・複勝リスト */
	private List<RaceTnpkNinDto> raceTnpkNinList;

	/**
	 * .
	 * @return このオブジェクト
	 */
	public RaceTnpkNinPage parse() {
		return parse(false);
	}

	/**
	 * .
	 * @param force 強制的に解析するか
	 * @return このオブジェクト
	 */
	public RaceTnpkNinPage parse(boolean force) {
		if (!force && raceTnpkNinList != null) {
			return this;
		}
		String[] headerStr = document.selectFirst("div.header_line span.opt").text().trim().split("（.曜）|\\s");
		this.raceDto = new RaceDto();
		this.raceDto.setKaisaiDtStr(headerStr[0]);
		this.raceDto.setKaisaiNm(headerStr[1]);
		this.raceDto.setRaceNoStr(headerStr[2]);
		this.raceDto.setOddsTmStr(document.selectFirst("div.refresh_line div.time").text()
				.replaceAll("[^0-9時]", "").replaceAll("時", ":"));

		this.raceTnpkNinList = new ArrayList<>();
		document.select("div#odds_list table.tanpuku tbody tr").forEach(element -> {
			RaceTnpkNinDto dto = new RaceTnpkNinDto();
			dto.setNinkiNo(element.selectFirst("td.pop").text());
			dto.setWakuNo(element.selectFirst("td.waku img").attr("alt").replaceAll("[^0-9]", ""));
			dto.setUmaNo(element.selectFirst("td.num").text());
			dto.setUmaNm(element.selectFirst("td.horse").text());
			dto.setTanOddsStr(element.selectFirst("td.odds_tan").text());
			dto.setFukuOddsMinStr(
					Optional.ofNullable(element.selectFirst("td.odds_fuku span.min")).map(e -> e.text()).orElse(null));
			dto.setFukuOddsMaxStr(
					Optional.ofNullable(element.selectFirst("td.odds_fuku span.max")).map(e -> e.text()).orElse(null));
			dto.setJockeyNm(element.selectFirst("td.jockey").text());
			this.raceTnpkNinList.add(dto);
		});
		return this;
	}
}