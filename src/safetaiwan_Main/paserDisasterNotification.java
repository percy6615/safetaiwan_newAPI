package safetaiwan_Main;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import de.micromata.opengis.kml.v_2_2_0.Kml;

public class paserDisasterNotification {

	public static void main(String[] args) {

		String htmla = "<table border='0' padding='0' ><tr><td>我很安全</td>2017-03-23 14:54:00</tr></table>";
		htmla = "<table border='0' padding='0' ><tr><td>這是測試，不要驚</br>2017-03-28 17:16:05</td></tr><tr><td><a href='http://link.safetaiwan.tw/mobile_app/report/pictures/20170328171615_355693062270908.jpg' border='0'><img src='http://link.safetaiwan.tw/mobile_app/report/pictures/20170328171615_355693062270908.jpg' width='300px' /></a></td></tr></table>";
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm", Locale.ENGLISH);
		Document doc = Jsoup.parse(htmla);
		Elements link = doc.select("tr");
		int linkNum = link.size();
		if (linkNum >= 2) {
			Element e1 = link.get(0).select("td").first();
			String[] content = e1.html().split("<br>");
			System.out.println(content[0]);
			System.out.println(content[1]);
			Element e2 = link.get(1).select("td").first();
			Element a = e2.select("a").first();
			String imgPath = a.attr("href");
			System.out.println(imgPath);
			try {
				Date date = format.parse(content[1]);
				System.out.println(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (linkNum < 2) {
			Element e = link.get(0);
			String[] splitChar = e.html().split("</td>");
			Element e1 = e.select("td").first();
			String recallContent = e1.text();
			System.out.println(recallContent);
			System.out.println(splitChar[1]);
		}

	}

	public static String html2text(String html) {
		return Jsoup.parse(html).text();
	}

	public void htmlparser() {
		String a = "&lt;table border='0' padding='0' &gt;&lt;tr&gt;&lt;td&gt;這是測試，不要驚&lt;/br&gt;2017-03-28 17:16:05&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;&lt;a href='http://link.safetaiwan.tw/mobile_app/report/pictures/20170328171615_355693062270908.jpg' border='0'&gt;&lt;img src='http://link.safetaiwan.tw/mobile_app/report/pictures/20170328171615_355693062270908.jpg' width='300px' /&gt;&lt;/a&gt;&lt;/td&gt;&lt;/tr&gt;&lt;/table&gt;";
		String atag = html2text(a);
		System.out.println(a);
		System.out.println();
		System.out.println(atag);
		String safe = Jsoup.clean(atag, Whitelist.basic());
		System.out.println();
		System.out.println(safe);
		String noHTMLString = atag.replaceAll("\\<.*?\\>", "");
		System.out.println();
		System.out.println(noHTMLString);
		String noHTMLString1 = atag.replaceAll("<[/]?a[^>]*>", "");
		System.out.println();
		System.out.println(noHTMLString1);
		System.out.println(Jsoup.parse(atag).text());

		// System.out.println(htmla);
		String htmla = "<table border='0' padding='0' ><tr><td>這是測試，不要驚</br>2017-03-28 17:16:05</td></tr><tr><td><a href='http://link.safetaiwan.tw/mobile_app/report/pictures/20170328171615_355693062270908.jpg' border='0'><img src='http://link.safetaiwan.tw/mobile_app/report/pictures/20170328171615_355693062270908.jpg' width='300px' /></a></td></tr></table>";

		Document doca = Jsoup.parse(htmla);
		Element linka = doca.select("tr").first();
		String contenta = linka.html();
		System.out.println(contenta);
		String htmlb = "<table border='0' padding='0' ><tr><td>我很安全</td>2017-03-23 14:54:00</tr></table>";
		Document docb = Jsoup.parse(htmlb);
		Elements linkb = docb.select("tr>td");
		String contentb = linkb.html();
		String[] splitb = contentb.split("</td>");
		System.out.println(contentb);
		System.out.println("split:" + splitb[0]);

		// String html = "<p>An <a href='http://example.com/'><b>example</b></a>
		// link.</p>";
		Document doc = Jsoup.parse(htmla);
		Element link = doc.select("a").first();

		String text = doc.body().text(); // "An example link"
		String linkHref = link.attr("href"); // "http://example.com/"
		String linkText = link.text(); // "example""

		String linkOuterH = link.outerHtml();
		// "<a href="http://example.com"><b>example</b></a>"
		String linkInnerH = link.html(); // "<b>example</b>"
		System.out.println(text);
		System.out.println(linkHref);
		System.out.println(linkText);
		System.out.println(linkOuterH);
		System.out.println(linkInnerH);
	}
}
