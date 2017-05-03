package safetaiwan_Main;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import de.micromata.opengis.kml.v_2_2_0.Kml;
import safetaiwan_CommTools.CommonTools;
import safetaiwan_messageObject.DisasterNotification;

public class paserDisasterNotification {

	public static void main(String[] args) {
		List<String> useridList = new ArrayList<String>();
		useridList.add("a");
		useridList.add("b");
		String flag = "<table border='0' padding='0' ><tr><td>安全臺灣SafeTaiwan@實機操作示範</br>2017-04-18 11:54:29</td></tr><tr><td><a href='http://link.safetaiwan.tw/mobile_app/report/pictures/20170418115242_357220077438888.jpg' border='0'><img src='http://link.safetaiwan.tw/mobile_app/report/pictures/20170418115242_357220077438888.jpg' width='300px' /></a></td></tr></table>";
		descriptionParser(flag);
		
	}

	public static String connectUserid(List<String> useridList) {
		String returnString = "";
		if (useridList.size() != 0) {
			returnString = "\"";
		}

		for (int i = 0, iend = useridList.size(); i < iend; i++) {

			if (i == iend - 1) {
				returnString = returnString + useridList.get(i) + "\"";
			} else {
				returnString = returnString + useridList.get(i) + "\",\"";
			}

		}
		return returnString;
	}

	public static String textContent(DisasterNotification listParser) {
		String returnString = "回報時間 : " + new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(listParser.getReportDate())
				+ "\n回報姓名 : " + listParser.getName() + "\n回報內容 : " + listParser.getReportContent();
		return returnString;
	}

	public static void parserhtml() {
		// Kml kml = Kml.unmarshal(new
		// File("D:\\workspace_all\\workspaceline\\pasertmp\\resources\\kml\\test.kml"));
		// de.micromata.opengis.kml.v_2_2_0.Document document =
		// (de.micromata.opengis.kml.v_2_2_0.Document) kml
		// .getFeature();
		// document.getStyleSelector().get(0).withId("PolyStyle00");
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

	public static void descriptionParser(String description) {
		System.out.println();
		Document doc = Jsoup.parse(description);
		Elements link = doc.select("tr");
		CommonTools commonTools = new CommonTools();
		int linkNum = link.size();
		if (linkNum >= 2) {
			Element e1 = link.get(0).select("td").first();
			String[] content = e1.html().split("<br>");
			System.out.println(content[0]);
			System.out.println(commonTools.StringToTimestamp(content[1]));
			
			Element e2 = link.get(1).select("td").first();
			Element a = e2.select("a").first();
			String imgPath = a.attr("href");
			System.out.println(imgPath);
			
			File f = new File(imgPath);
			System.out.println(f.getName());
			

		} else if (linkNum < 2) {
			Element e = link.get(0);
			String[] splitChar = e.html().split("</td>");
			Element e1 = e.select("td").first();
			String recallContent = e1.text();
			System.out.println(recallContent);
			System.out.println(commonTools.StringToTimestamp(splitChar[1]));
			
			

		}
	}
}
