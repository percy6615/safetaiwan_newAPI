package safetaiwan_Main;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import safetaiwan_CommTools.CommonTools;
import safetaiwan_CommTools.KMLReceiveFromNet;
import safetaiwan_HTTPS.HttpsURLConnectionToken;
import safetaiwan_Parser.DisasterNotificationParser;
import safetaiwan_messageObject.DisasterNotification;

public class Main {

	public static void main(String[] args) {
		// get token url
		int layeridMain = 7912;
		String YourAPIKeyMain = "U7x567PAj7up//PkoAQZxC/kd5RAdGgxuEAZb5yhF4/Me5iRB4CpOF/MdK/Ed+xFvJ/cbAQZxC/k";
		HttpsURLConnectionToken httpsURLConnectionToken = new HttpsURLConnectionToken(layeridMain, YourAPIKeyMain);
		String getURLToken = httpsURLConnectionToken.refleshTokenUrl();

		// download kml to file system
		Date d = new Date(System.currentTimeMillis());
		SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmm");
		String timeString = s.format(d);
		KMLReceiveFromNet kMLReceiveFromNet = new KMLReceiveFromNet();
		String fileName = layeridMain + "_" + timeString + ".kml";
		kMLReceiveFromNet.downloadKML(getURLToken, fileName);
		System.out.println(fileName);

		// parser
		Timestamp currentTimeStamp = new Timestamp(d.getTime());
		DisasterNotificationParser disasterNotificationParser = new DisasterNotificationParser();
		disasterNotificationParser.setKml(fileName);
		List<?> list = disasterNotificationParser.disasterNotificationParserList(disasterNotificationParser.getKml(),
				currentTimeStamp);
		List<String> descriptionList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			String description = ((DisasterNotification) list.get(i)).getReportContent();
			System.out.println(description);
			descriptionList.add(description);
		}

		// send to line
		
		// save to db

	}

}
