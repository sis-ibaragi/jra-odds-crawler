/**
 *
 */
package crawler.jra.page;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import crawler.jra.constant.Const;
import lombok.Data;
import lombok.NonNull;

/**
 * @author kilim
 *
 */
@Data
public class PostForm {

	/** cname */
	@NonNull
	private String cname;

	/**
	 *
	 * @return
	 */
	public Document post() {
		try {
			return Jsoup.connect(Const.ODDS_URL).data("cname", cname).post();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
