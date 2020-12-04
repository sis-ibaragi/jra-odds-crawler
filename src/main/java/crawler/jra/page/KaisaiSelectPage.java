/**
 *
 */
package crawler.jra.page;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.jsoup.nodes.Document;

import crawler.jra.dto.KaisaiCnameDto;
import crawler.jra.util.ScrapingUtils;
import lombok.Data;
import lombok.NonNull;

/**
 * @author kilim
 *
 */
@Data
public class KaisaiSelectPage {

	/** Document */
	@NonNull
	private Document document;

	/** . */
	private Map<String, KaisaiCnameDto> kaisaiCnameMap;

	/**
	 * .
	 * @return .
	 */
	public KaisaiSelectPage parse() {
		return parse(false);
	}

	/**
	 * .
	 * @param force .
	 * @return .
	 */
	public KaisaiSelectPage parse(boolean force) {
		if (!force && this.kaisaiCnameMap != null) {
			return this;
		}
		this.kaisaiCnameMap = new LinkedHashMap<>();

		document.select("div.thisweek div.link_list div").stream().forEach(element -> {
			String kaisaiName = Optional.ofNullable(element.selectFirst("a")).map(m -> m.text()).orElse(null);
			String kaisaiOnclick = Optional.ofNullable(element.selectFirst("a")).map(m -> m.attr("onClick"))
					.orElse(null);
			if (kaisaiName != null && !kaisaiName.isEmpty() && kaisaiOnclick != null && !kaisaiOnclick.isEmpty()) {
				KaisaiCnameDto dto = new KaisaiCnameDto();
				dto.setKaisaiNm(kaisaiName);
				dto.setKaisaiCname(ScrapingUtils.getCnameFromDoAction(kaisaiOnclick));
				this.kaisaiCnameMap.put(kaisaiName, dto);
			}
		});
		return this;

	}

	/**
	 * RaceSelectPage オブジェクトを返します。
	 * @param kaisaiName .
	 * @return RaceSelectPage オブジェクト
	 */
	public RaceSelectPage goRaceSelectPage(String kaisaiName) {
		String cname = this.kaisaiCnameMap.get(kaisaiName).getKaisaiCname();
		return new RaceSelectPage(new PostForm(cname).post());
	}

}
