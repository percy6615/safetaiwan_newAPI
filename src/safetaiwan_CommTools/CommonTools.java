package safetaiwan_CommTools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.UUID;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class CommonTools {

	public static String APPLocation() {
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		return s;
	}

	public static void sysPrint2D(String[][] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[a.length - 1].length; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void sysPrint1D(String[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}

	public HttpsURLConnection SSLHttpConnection(String url) {
		HttpsURLConnection con = null;
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
			}
		} };
		try {
			SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, trustAllCerts, null);
			URL urlObj = new URL(url);
			con = (HttpsURLConnection) urlObj.openConnection();
			con.setSSLSocketFactory(sslContext.getSocketFactory());
			con.setHostnameVerifier(new HostnameVerifier() {
				@Override
				public boolean verify(String s, SSLSession sslSession) {
					return true;
				}
			});
			return con;
		} catch (KeyManagementException | NoSuchAlgorithmException | IOException e) {
			e.printStackTrace();
		}
		return con;
	}

	public String generateUUID() {
		String s = UUID.randomUUID().toString().replace("-", "").toLowerCase();
		return s;
	}

	public Date StringToDate(String stringDate) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
		Date d = null;
		try {
			d = format.parse(stringDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}

	public String DateToString(Date d) {
		// 設定日期格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 進行轉換
		String dateString = sdf.format(d);
		return dateString;
	}

	public Timestamp StringToTimestamp(String something) {

		SimpleDateFormat dateFormat = null;
		if (something.contains(".")) {
			dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		} else if (something.contains(",")) {
			dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss,SSS");
		} else {
			dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		}

		Timestamp timestamp = null;
		Date parsedDate;
		try {
			parsedDate = dateFormat.parse(something);
			timestamp = new java.sql.Timestamp(parsedDate.getTime());

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return timestamp;
	}

	public String currentTime() {
		Date d = new Date(System.currentTimeMillis());
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String timeString = s.format(d);
		return timeString;
	}

	public Properties getProperties(String fileName)   {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return props;

	}
}