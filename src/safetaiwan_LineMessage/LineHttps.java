package safetaiwan_LineMessage;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import safetaiwan_CommTools.CommonTools;
import safetaiwan_CommTools.DBSource.DisasterNotificationDBfunction;

public class LineHttps {
	private final static String USER_AGENT = "Mozilla/5.0";
	private static String token = "pslrmKmSM30059ArgBD2wv4Sm6zRbyjqpdrYIHFZbtGZmqO76wuOBV5p2+re039F7umgZptlue+RUiv+k38Oin6v1DIt5wfS8myZ1Xw3h7RPRDczDJgakudp0I8EheQ+VLE77SiMvDtMGUxcg7nvXAdB04t89/1O/w1cDnyilFU=";
	// static String lineurl = "https://api.line.me/v2/bot/message/push";
	public static String lineurl = "https://api.line.me/v2/bot/message/multicast";
//	public static String lineurl = "https://api.line.me/v2/bot/profile/";
	// static String replyBody = String.format("{\"to\":\"%s\",
	// \"messages\":[{\"type\":\"text\", \"text\":\"line bot
	// send\"}]}","Uc7a46420c8125d4fcaa0312f2d47dc10");
	// static String replyBody = "{
	// \"to\":[\"Udd25c6f918b51a3840dff50eea1cf3af\",\"U314f428f4da380de2b4d21f6902dfb17\"],
	// \"messages\":[{\"type\":\"text\", \"text\":\"回報時間 : 04/18/2017
	// 14:01:40回報姓名 : 洪鎮鉉回報內容 : 上課(測試中)\"}]}";
	static String replyBody = "{ \"to\":[\"Udd25c6f918b51a3840dff50eea1cf3af\"], \"messages\":[{\"type\":\"image\",  \"originalContentUrl\": \"http://www.hchannel.tv/wp-content/uploads/2016/12/Topic.jpg\",\"previewImageUrl\": \"https://static.pexels.com/photos/27714/pexels-photo-27714.jpg\"}]}";

	public static void main(String[] args) {
		LineHttps LineHttps = new LineHttps();
//		LineHttps.sendGETProfile(lineurl,"U0f3f3ce09f2bed28d7e98a5c4d623e3a");
		DisasterNotificationDBfunction disasterNotificationDBfunction = new DisasterNotificationDBfunction();
		List<String> useridList = disasterNotificationDBfunction.selectUserId();  
		String content = "各位防救災的夥伴，大家好，演習已結束，恢復接受SafeTaiwan全台灣災情回報，謝謝。";
		String jsonContent = "{ \"to\":[" + LineHttps.connectUserid(useridList)
		+ "], \"messages\":[{\"type\":\"text\", \"text\":\"" + content
		+ "\"}]}";
		LineHttps.sendPOSTReturnToken(lineurl,jsonContent);
	}

	public  String sendPOSTReturnToken(String url, String replyBody) {
		CommonTools commonTools = new CommonTools();
		HttpsURLConnection con = commonTools.SSLHttpConnection(url);
		try {
			// optional default is GET
			con.setRequestMethod("POST");
			// String yourid = "Uc7a46420c8125d4fcaa0312f2d47dc10";
			// String replyBody = String.format("{\"to\":\"%s\",
			// \"messages\":[{\"type\":\"text\", \"text\":\"line bot send\"}]}",
			// yourid);
			// add request header
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Authorization", "Bearer " + token);
			con.setUseCaches(false);
			con.setAllowUserInteraction(false);
			con.setInstanceFollowRedirects(false);
			con.setDoInput(true);
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.write(replyBody.getBytes());
			wr.flush();
			wr.close();
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Post parameters : " + replyBody);
			System.out.println("Response Code : " + responseCode);
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			br.close();
			System.out.println("WEB return value is : " + sb);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return token;
	}

	public  void sendGETProfile(String url, String userid) {
		CommonTools commonTools = new CommonTools();
		String profileURL = url+userid;
		HttpsURLConnection con = commonTools.SSLHttpConnection(profileURL);
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Authorization", "Bearer " + token);
		con.setUseCaches(false);
		con.setAllowUserInteraction(false);
		con.setInstanceFollowRedirects(false);
		con.setDoInput(true);
		con.setDoOutput(true);

		int responseCode = 0;
		try {
			responseCode = con.getResponseCode();

			System.out.println("Response Code : " + responseCode);
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			br.close();
			System.out.println("WEB return value is : " + sb);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private  String connectUserid(List<String> useridList) {
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
}
