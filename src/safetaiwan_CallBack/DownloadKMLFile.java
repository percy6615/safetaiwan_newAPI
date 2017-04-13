package safetaiwan_CallBack;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import safetaiwan_CommTools.KMLReceiveFromNet;
import safetaiwan_HTTPS.HttpsURLConnectionToken;

public class DownloadKMLFile implements Runnable {
	CallBackParser call;
	String fileName;
	Date d;
	private String propertyPath = "resources/cfg/keyid.properties";
	private Properties props;
	DownloadKMLFile(CallBackParser call, Date d) {
		this.call = call;
		this.d = d;
	}

	DownloadKMLFile(CallBackParser call, String fileName, Date d) {
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
		props = new Properties();
		try {
			props.load(new FileInputStream(this.propertyPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int layeridMain = Integer.valueOf(props.getProperty("safetaiwan.layerid"));
		String YourAPIKeyMain = props.getProperty("safetaiwan.token");
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
