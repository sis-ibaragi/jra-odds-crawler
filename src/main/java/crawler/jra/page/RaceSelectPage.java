/**
 *
 */
package crawler.jra.page;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jsoup.nodes.Document;

import crawler.jra.dto.KaisaiDto;
import crawler.jra.dto.RacePageCnameDto;
import crawler.jra.util.ScrapingUtils;
import lombok.Data;
import lombok.NonNull;

/**
 * @author kilim
 *
 */
@Data
public class RaceSelectPage {

	/** Document */
	@NonNull
	private Document document;

	/** . */
	private KaisaiDto kaisaiDto;

	/** . */
	private LocalDate filterKaisaiDt;

	/** . */
	private Map<String, RacePageCnameDto> racePageCnameMap;

	public RaceSelectPage parse() {
		return parse(false);
	}

	public RaceSelectPage parse(boolean force) {
		if (!force && this.racePageCnameMap != null) {
			return this;
		}
		// ヘッダー部の開催日、開催名を取得
		String[] header = this.document.selectFirst("table#race_list div.main").text().trim().split("（.曜）");
		// 開催日に指定ありの場合、指定の開催日以外はスクレイピングしない
		if (this.filterKaisaiDt != null
				&& filterKaisaiDt.compareTo(ScrapingUtils.getKaisaiDt(header[0])) != 0) {
			return this;
		}
		this.kaisaiDto = new KaisaiDto();
		this.kaisaiDto.setKaisaiDtStr(header[0]);
		this.kaisaiDto.setKaisaiNm(header[1]);

		this.racePageCnameMap = new LinkedHashMap<>();
		this.document.select("table#race_list tbody tr").stream().forEach(element -> {
			// レース番号
			String raceNo = element.selectFirst(" th.race_num a img").attr("alt");
			// 単複
			String tnpkOnclick = element.selectFirst("td.odds ul.btn_list li.tanpuku a").attr("onClick");
			// 馬連
			String umrnOnclick = element.selectFirst("td.odds ul.btn_list li.umaren a").attr("onClick");
			RacePageCnameDto dto = new RacePageCnameDto();
			dto.setRaceNo(raceNo);
			dto.setTnpkCname(ScrapingUtils.getCnameFromDoAction(tnpkOnclick));
			dto.setUmrnCname(ScrapingUtils.getCnameFromDoAction(umrnOnclick));
			this.racePageCnameMap.put(raceNo, dto);
		});
		return this;
	}

	/**
	 * RaceTnpkUmaPage
	 * @param raceNo
	 * @return RaceTnpkUmaPage
	 */
	public RaceTnpkUmaPage goRaceTnpkUmaPage(String raceNo) {
		String cname = this.racePageCnameMap.get(raceNo).getTnpkCname();
		return new RaceTnpkUmaPage(new PostForm(cname).post());
	}

	/**
	 * RaceUmrnUmaPage
	 * @param raceNo
	 * @return RaceUmrnUmaPage
	 */
	public RaceUmrnUmaPage goRaceUmrnUmaPage(String raceNo) {
		String cname = this.racePageCnameMap.get(raceNo).getUmrnCname();
		return new RaceUmrnUmaPage(new PostForm(cname).post());
	}
}
