package safetaiwan_Main;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import de.micromata.opengis.kml.v_2_2_0.Kml;
import safetaiwan_messageObject.DisasterNotification;

public class paserDisasterNotification {

	public static void main(String[] args) {
		List<String> useridList = new ArrayList<String>();
		useridList.add("a");
		useridList.add("b");
		;
//		String jsonContent = "{ \"to\":["+connectUserid(useridList)+"], \"message\":[{\"type\":\"text\", \"text\":\""+textContent()+"\"}]}";
//		System.out.println(jsonContent);;
	}
	public static String connectUserid(List<String> useridList){
		String returnString = "";
		if(useridList.size() != 0){
			 returnString = "\"";
		}
		
		for(int i = 0 , iend = useridList.size();i<iend;i++){
			
			if(i==iend-1){
				returnString = returnString+useridList.get(i)+"\"";
			}else{
				returnString = returnString+useridList.get(i)+"\",\"";
			}
			
		}		
		return returnString;	
	}
	
	public static String textContent(DisasterNotification listParser){
		String returnString= "回報時間 : "+new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(listParser.getReportDate())+"\n回報姓名 : "+ listParser.getName()+"\n回報內容 : "+listParser.getReportContent();
		return returnString;
	}
	public static void parserhtml(){
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
