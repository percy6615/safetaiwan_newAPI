package safetaiwan_CallBack;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import safetaiwan_CommTools.DBSource.DisasterNotificationDBfunction;
import safetaiwan_LineMessage.LineHttps;
import safetaiwan_Parser.DisasterNotificationParser;
import safetaiwan_messageObject.DisasterNotification;

public class AskSafeTaiwan implements CallBackParser {

	private DownloadKMLFile downloadFile;
	private String fileName;

	public AskSafeTaiwan() {

	}

	public AskSafeTaiwan(DownloadKMLFile downloadFile) {
		this.downloadFile = downloadFile;
	}

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
		// database
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
		List<String> useridList=disasterNotificationDBfunction.selectUserId();
		sendMessage(list,useridList);
		System.out.println(
				"end:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
	}

	private void sendMessage(List<DisasterNotification> listParser,List<String> useridList) {
		LineHttps lineHttps = new LineHttps();
		for(int i = 0 , iend = listParser.size();i<iend;i++){
			String jsonContent = "{ \"to\":["+connectUserid(useridList)+"], \"messages\":[{\"type\":\"text\", \"text\":\""+textContent(listParser.get(i))+"\"}]}";
			lineHttps.sendPOSTReturnToken(LineHttps.lineurl,jsonContent);
		}
//		
	}


	private String connectUserid(List<String> useridList){
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
		String returnString= "回報時間 : "+new SimpleDateFormat("YYYY/MM/dd HH:mm").format(listParser.getReportDate())+",\\n回報姓名 : "+ listParser.getName()+",\\n回報內容 : "+listParser.getReportContent();
		return returnString;
	}
}
