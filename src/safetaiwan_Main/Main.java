package safetaiwan_Main;

import java.text.SimpleDateFormat;
import java.util.Date;

import safetaiwan_CommTools.KMLReceiveFromNet;
import safetaiwan_HTTPS.HttpsURLConnectionToken;

public class Main {

	public static void main(String[] args) {
		Date d = new Date(System.currentTimeMillis());
		SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmm");
		String timeString = s.format(d);
		// get token url
		int layeridMain = 7912;
		String YourAPIKeyMain = "U7x567PAj7up//PkoAQZxC/kd5RAdGgxuEAZb5yhF4/Me5iRB4CpOF/MdK/Ed+xFvJ/cbAQZxC/k";
		HttpsURLConnectionToken httpsURLConnectionToken = new HttpsURLConnectionToken(layeridMain,YourAPIKeyMain);
		String getURLToken = httpsURLConnectionToken.refleshTokenUrl();
		//download kml to file system
		KMLReceiveFromNet kMLReceiveFromNet = new KMLReceiveFromNet();
		String fileName = layeridMain+"_"+timeString+".kml";
		kMLReceiveFromNet.downloadKML(getURLToken, fileName);
	}

}
