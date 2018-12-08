/**
 *
 */
package crawler.jra.page;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import crawler.jra.util.ScrapingUtils;
import lombok.Data;
import lombok.NonNull;

/**
 * @author kilim
 *
 */
@Data
public class RaceTnpkUmaPage {

	/** Document */
	@NonNull
	private Document document;

	/**
	 * RaceTnpkNinPage オブジェクトを返します。
	 * @return RaceTnpkNinPage オブジェクト
	 */
	public RaceTnpkNinPage goRaceTnpkNinPage() {
		Element element = document.selectFirst("ul.type li.current").nextElementSibling();
		String cname = ScrapingUtils.getCnameFromDoAction(element.selectFirst("a").attr("onClick"));
		return new RaceTnpkNinPage(new PostForm(cname).post());
	}

}
