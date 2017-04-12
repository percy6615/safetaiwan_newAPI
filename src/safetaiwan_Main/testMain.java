package safetaiwan_Main;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import de.micromata.opengis.kml.v_2_2_0.Kml;
import safetaiwan_CommTools.CommonTools;
import safetaiwan_CommTools.DBSource.DisasterNotificationDBfunction;
import safetaiwan_Parser.DisasterNotificationParser;
import safetaiwan_messageObject.DisasterNotification;

public class testMain {
	public static void main(String[] args) {
		// DisasterNotificationParser disasterNotificationParser = new
		// DisasterNotificationParser();
		// disasterNotificationParser.setKml("7912_201703311125.kml");
		// CommonTools commonTools = new CommonTools();
		// String currentTime = commonTools.currentTime();
		// Timestamp currentTimeStamp =
		// commonTools.StringToTimestamp(currentTime);
		// List<?> list =
		// disasterNotificationParser.disasterNotificationParserList(disasterNotificationParser.getKml(),
		// currentTimeStamp);
		// List<String> descriptionList = new ArrayList<String>();
		// for (int i = 0; i < list.size(); i++) {
		// String description = ((DisasterNotification)
		// list.get(i)).getReportContent();
		// System.out.println(description);
		// descriptionList.add(description);
		// }
		getKml();
	}

	public static void getKml() {
		String str = "";
		str = StringUtils.replace(str, "xmlns=\"http://earth.google.com/kml/2.2\"",
				"xmlns=\"http://www.opengis.net/kml/2.2\" xmlns:gx=\"http://www.google.com/kml/ext/2.2\"");

		System.out.println(str);
	}

	public void db() {
		DisasterNotificationDBfunction DisasterNotificationDBfunction = new DisasterNotificationDBfunction();
		DisasterNotification disasterNotification = new DisasterNotification();
		// DisasterNotificationDBfunction.createDisasterNotification();
		DisasterNotificationDBfunction.insertDisasterNotification(disasterNotification);
	}

	public void testpage() {
		// capture rendered page
		WebClient webClient = new WebClient();
		HtmlPage myPage = null;
		try {
			myPage = webClient.getPage("http://xmovies.to/watch/snis-872-cheap-iki-92-times-7301.html");
		} catch (FailingHttpStatusCodeException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// convert to jsoup dom
		Document doc = Jsoup.parse(myPage.asXml());
		String ddd = doc.html();
		System.out.println(ddd);

		String s = CommonTools.APPLocation();
		String outFilePathALL = s + "/resources/exampledata/" + "1.txt";
		Path file = Paths.get(outFilePathALL);

		List<String> lines = Arrays.asList(ddd);
		try {
			Files.write(file, lines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// extract data using jsoup selectors
		// Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
		// for (Element image : images) {
		// System.out.println("src : " + image.attr("src"));
		// }

		// clean up resources
		webClient.close();
	}
}
