package safetaiwan_CommTools;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

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
}