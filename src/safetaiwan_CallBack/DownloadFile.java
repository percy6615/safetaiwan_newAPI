package safetaiwan_CallBack;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import safetaiwan_CommTools.KMLReceiveFromNet;
import safetaiwan_HTTPS.HttpsURLConnectionToken;

public class DownloadFile implements Runnable {
	CallBackParser call;
	String fileName;
	Date d;

	DownloadFile(CallBackParser call, Date d) {
		this.call = call;
		this.d = d;
	}

	DownloadFile(CallBackParser call, String fileName, Date d) {
		this.call = call;
		this.fileName = fileName;
		this.d = d;
	}

	@Override
	public void run() {
		downloadKML();
	}

	private void downloadKML() {
		// get token url
		int layeridMain = 7912;
		String YourAPIKeyMain = "U7x567PAj7up//PkoAQZxC/kd5RAdGgxuEAZb5yhF4/Me5iRB4CpOF/MdK/Ed+xFvJ/cbAQZxC/k";
		HttpsURLConnectionToken httpsURLConnectionToken = new HttpsURLConnectionToken(layeridMain, YourAPIKeyMain);
		String getURLToken = httpsURLConnectionToken.refleshTokenUrl();
		// download kml to file system
		Timestamp timestamp = new Timestamp(this.d.getTime());
		SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmm");
		String timeString = s.format(d);
		KMLReceiveFromNet kMLReceiveFromNet = new KMLReceiveFromNet();
		String fileName = layeridMain + "_" + timeString + ".kml";
		kMLReceiveFromNet.downloadKML(getURLToken, fileName);
		call.parser(fileName, timestamp);// call parser
	}

}
