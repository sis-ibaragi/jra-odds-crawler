/**
 *
 */
package crawler.jra.page;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import crawler.jra.constant.Const;
import crawler.jra.util.ScrapingUtils;

/**
 * @author kilim
 *
 */
public class TopPage {

	/** . */
	private Document document;

	/**
	 * .
	 */
	public TopPage() {
		try {
			this.document = Jsoup.connect(Const.BASE_URL).timeout(Const.REQUEST_TIMEOUT_MS).get();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * OddsTopPage オブジェクトを返します。
	 * @return OddsTopPage オブジェクト
	 */
	public KaisaiSelectPage goKaisaiSelectPage() {
		Element element = this.document.selectFirst("#q_menu4 a");
		String cname = ScrapingUtils.getCnameFromDoAction(element.attr("onClick"));
		Document oddsDoc = new PostForm(cname).post();
		return new KaisaiSelectPage(oddsDoc);
	}
}
