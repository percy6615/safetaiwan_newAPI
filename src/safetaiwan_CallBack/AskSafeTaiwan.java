package safetaiwan_CallBack;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import safetaiwan_CommTools.DBSource.DisasterNotificationDBfunction;
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
		List<DisasterNotification> list = disasterNotificationParser.disasterNotificationParserList(disasterNotificationParser.getKml(),
				timestamp);
		//print look
		List<String> descriptionList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			String description = ((DisasterNotification) list.get(i)).getReportContent();
			System.out.println(description);
			descriptionList.add(description);
		}
		//Image file download
		DownloadImageFile downloadImageFile = new DownloadImageFile(list);
		new Thread(downloadImageFile).start();;
		//line
		
		//database
		DisasterNotificationDBfunction disasterNotificationDBfunction = new DisasterNotificationDBfunction();
		disasterNotificationDBfunction.insertDisasterNotificationList(list);
		
		System.out.println(
				"end:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
	}
	private List<String> sendMessage(List<DisasterNotification> listParser){
		
		return null;
		
	}

}
