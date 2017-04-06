package safetaiwan_Main;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import safetaiwan_CommTools.CommonTools;
import safetaiwan_CommTools.DBSource.DisasterNotificationDBfunction;
import safetaiwan_messageObject.DisasterNotification;

public class testMain {
	public static void main(String[] args)  {
		DisasterNotificationDBfunction DisasterNotificationDBfunction = new DisasterNotificationDBfunction();
		DisasterNotification disasterNotification = new DisasterNotification();
//		DisasterNotificationDBfunction.createDisasterNotification();
		DisasterNotificationDBfunction.insertDisasterNotificationToDB(disasterNotification);
	}
	public void testpage(){
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
//				Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
//				for (Element image : images) {
//				    System.out.println("src : " + image.attr("src"));
//				}

				// clean up resources
				webClient.close();
	}
}
