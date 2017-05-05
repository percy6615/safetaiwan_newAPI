package safetaiwan_CallBack;

import java.io.FileInputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import safetaiwan_CommTools.CommonTools;
import safetaiwan_CommTools.DBSource.DisasterNotificationDBfunction;
import safetaiwan_LineMessage.LineHttps;
import safetaiwan_Parser.DisasterNotificationParser;
import safetaiwan_messageObject.CoordinatesPoint;
import safetaiwan_messageObject.DisasterNotification;
import safetaiwan_messageObject.HsinChuGeoJson;
import safetaiwan_messageObject.PointFilterPolygon;

public class AskSafeTaiwan implements CallBackParser {

	private DownloadKMLFile downloadFile;
	private String fileName;
	private Properties props = null;
	private String imgHostUrl = "https://bba60fcf.ngrok.io//";
	private String googleHostUrl = "http://60.250.226.78:29";
	private String zoom = "17";
	private String propertyfile =  "resources/cfg/jdbc.properties";
	public AskSafeTaiwan() {
		CommonTools commonTools= new CommonTools();
		props = commonTools.getProperties(propertyfile);
	}

	// public AskSafeTaiwan(DownloadKMLFile downloadFile) {
	// this.downloadFile = downloadFile;
	//
	// }

	public void pleaseDownloadKML(Date d) {
		System.out.println("start:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d));
		DownloadKMLFile downloadFile = new DownloadKMLFile(AskSafeTaiwan.this, d);
		this.downloadFile = downloadFile;
		new Thread(downloadFile).start();
		toDoSomething();
	}

	public void toDoSomething() {
		System.out.println("toDoSomething");
	}

	@Override
	public void parser(String fileName, Timestamp timestamp) {
		this.fileName = fileName;
		// parser
		DisasterNotificationParser disasterNotificationParser = DisasterNotificationParser.getInstance();
		disasterNotificationParser.setKml(fileName);
		List<DisasterNotification> list = disasterNotificationParser
				.disasterNotificationParserList(disasterNotificationParser.getKml(), timestamp);
//		filter DisasterNotification list
		PointFilterPolygon pointFilterPolygon = new PointFilterPolygon();
		List<HsinChuGeoJson> p = pointFilterPolygon.parserJson("");
		List<CoordinatesPoint> getCoordinatesPoint = pointFilterPolygon.getCityCoordinatesPoints(p,"新竹市");
		pointFilterPolygon.preCalcValues(getCoordinatesPoint);
		for(int i = 0 ; i < list.size();i++){
			CoordinatesPoint test = list.get(i).getCoordinatesPoints().get(0);
			boolean TF= pointFilterPolygon.pointInPolygon(test);
			if(!TF){
				list.remove(i);
			}
		}
		// database insert
		DisasterNotificationDBfunction disasterNotificationDBfunction = new DisasterNotificationDBfunction();
		disasterNotificationDBfunction.insertDisasterNotificationList(list);

		// print look
		List<String> descriptionList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			String description = ((DisasterNotification) list.get(i)).getReportContent();
			System.out.println(list.get(i).getName() + " " + description
					+ list.get(i).getCoordinatesPoints().get(0).getLatitudeCoord() + "," + description
					+ list.get(i).getCoordinatesPoints().get(0).getLongitudeCoord());
			descriptionList.add(description);
		}
		// Image file download
		DownloadImageFile downloadImageFile = new DownloadImageFile(list);
		new Thread(downloadImageFile).start();
		// line
		List<String> useridList = disasterNotificationDBfunction.selectUserId();
		sendMessage(list, useridList);
		System.out.println(
				"end:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
	}

	private void sendMessage(List<DisasterNotification> listParser, List<String> useridList) {
		LineHttps lineHttps = new LineHttps();
		for (int i = 0, iend = listParser.size(); i < iend; i++) {
			DisasterNotification disasterNotification = listParser.get(i);
			String f = disasterNotification.getFileName();
			String jsonContent = "";

			if (f != null) {
				jsonContent = "{ \"to\":[" + connectUserid(useridList) + "],"
						+ " \"messages\":[ {\"type\":\"text\", \"text\":\"" + textContent(disasterNotification)
						+ "\\n\"}, " + " { \"type\":\"image\",\"originalContentUrl\":\"" + imageUrl(f)
						+ "\",\"previewImageUrl\": \"" + imageUrl(f) + "\" } ]}";

			} else {
				jsonContent = "{ \"to\":[" + connectUserid(useridList)
						+ "], \"messages\":[{\"type\":\"text\", \"text\":\"" + textContent(disasterNotification)
						+ "\"}]}";
			}
			lineHttps.sendPOSTReturnToken(LineHttps.lineurl, jsonContent);
		}
		//
	}

	private String connectUserid(List<String> useridList) {
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

	public String textContent(DisasterNotification listParser) {
		CoordinatesPoint coordinatesPoints = listParser.getCoordinatesPoints().get(0);
		String c = String.valueOf(coordinatesPoints.getLatitudeCoord());
		String l = String.valueOf(coordinatesPoints.getLongitudeCoord());
		String returnString = "回報時間 : " + new SimpleDateFormat("YYYY/MM/dd HH:mm").format(listParser.getReportDate())
				+ "\\n回報姓名 : " + listParser.getName() + "\\n回報內容 : " + listParser.getReportContent() 
				+ "\\n回報地點 : " + googleMapUrl(c, l, this.zoom)
				;
		return returnString;
	}

	private String imageUrl(String imageFileName) {
		String url = imgHostUrl + "img//" + imageFileName;
		return url;

	}

	private String googleMapUrl(String lat, String lng, String zoom) {
//		String url = hostUrl + "googlemapfile.html?" + "lat=" + lat + "&lng=" + lng + "&zoom=" + zoom;
		String url = googleHostUrl + "/?" + "lat=" + lat + "&lng=" + lng + "&zoom=" + zoom;
		return url;

	}
	
	private String uuidUrl(String uuid,String zoom) {
//		String url = hostUrl + "googlemapfile.html?" + "lat=" + lat + "&lng=" + lng + "&zoom=" + zoom;
		String url = googleHostUrl + "/sql.php?" + "id="+uuid+"&zoom="+zoom;
		return url;

	}

}
