package safetaiwan_CallBack;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AskSafeTaiwan implements CallBackParser {

	private DownloadFile downloadFile;
	private String fileName;

	public AskSafeTaiwan() {

	}

	public AskSafeTaiwan(DownloadFile downloadFile) {
		this.downloadFile = downloadFile;
	}

	public void pleaseDownloadKML() {
		System.out.println("start:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));

		DownloadFile downloadFile = new DownloadFile(AskSafeTaiwan.this,"C:\\workspaceJAVA\\safetaiwan_newAPI\\resources\\exampledata\\7912_201703311125.kml");
		this.downloadFile = downloadFile;
		new Thread(downloadFile).start();
		toDoSomething();
	}

	public void toDoSomething() {
		System.out.println("toDoSomething");
	}

	@Override
	public void parser(String fileName) {
		System.out.println(fileName);
		System.out.println("end:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
	}

}
