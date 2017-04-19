package safetaiwan_Main;



import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import de.micromata.opengis.kml.v_2_2_0.Kml;

public class paserDisasterNotification {

	public static void main(String[] args) {
//		Kml kml = Kml.unmarshal(new File("D:\\workspace_all\\workspaceline\\pasertmp\\resources\\kml\\test.kml"));
//		de.micromata.opengis.kml.v_2_2_0.Document document = (de.micromata.opengis.kml.v_2_2_0.Document) kml
//				.getFeature();
//		document.getStyleSelector().get(0).withId("PolyStyle00");
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
	}
	public static String html2text(String html) {
	    return Jsoup.parse(html).text();
	}
}
