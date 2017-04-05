package safetaiwan_HTTPS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.net.ssl.HttpsURLConnection;

import safetaiwan_CommTools.CommonTools;

public class HttpsURLConnectionToken {

	final static String stAPIUrl = "https://www.safetaiwan.tw/api2d/api/";
	private static String YourAPIKey = "U7x567PAj7up//PkoAQZxC/kd5RAdGgxuEAZb5yhF4/Me5iRB4CpOF/MdK/Ed+xFvJ/cbAQZxC/k";
	private final static String USER_AGENT = "Mozilla/5.0";
	private String curlOptMain = stAPIUrl + "?apikey=" + YourAPIKey;
	private int layerid = 7912;
	String token = "";
	String tokenUrl = stAPIUrl + "?layerid=" + layerid + "&token=" + token;
	private final static String USER_AGENT_ver1 = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36";

//	public static void main(String[] args) {
//		int layeridMain = 7912;
//		String YourAPIKeyMain = "U7x567PAj7up//PkoAQZxC/kd5RAdGgxuEAZb5yhF4/Me5iRB4CpOF/MdK/Ed+xFvJ/cbAQZxC/k";
//		HttpsURLConnectionToken httpsURLConnectionToken = new HttpsURLConnectionToken(layeridMain,YourAPIKeyMain);
//		System.out.println(httpsURLConnectionToken.refleshTokenUrl());
//	}
	public HttpsURLConnectionToken(int layerid,String YourAPIKey){
		this.layerid = layerid;
		this.YourAPIKey = YourAPIKey;
	}
	private String sendGetReturnToken(String url) {
		String token = "";
		CommonTools commonTools = new CommonTools();
		HttpsURLConnection con = commonTools.SSLHttpConnection(url);
		try {
			// optional default is GET
			con.setRequestMethod("GET");

			// add request header
			con.setRequestProperty("User-Agent", USER_AGENT_ver1);

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			token = response.toString();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return token;
	}

	public String refleshTokenUrl() {
		setLayerid(layerid);
		setToken(sendGetReturnToken(this.curlOptMain));
		this.tokenUrl = stAPIUrl + "?layerid=" + getLayerid() + "&token=" + getToken();
		return this.tokenUrl;
	}

	public String getTokenUrl() {
		return tokenUrl;
	}

	public void setTokenUrl(String tokenUrl) {
		this.tokenUrl = tokenUrl;
	}

	public int getLayerid() {
		return layerid;
	}

	public void setLayerid(int layerid) {
		this.layerid = layerid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
