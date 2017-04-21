package safetaiwan_LineMessage;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.net.ssl.HttpsURLConnection;

import safetaiwan_CommTools.CommonTools;



public class LineHttps {
	private final static String USER_AGENT = "Mozilla/5.0";
//	static String lineurl = "https://api.line.me/v2/bot/message/push";
	public static String lineurl = "https://api.line.me/v2/bot/message/multicast";
//	static String lineurl = "https://safetaiwan.herokuapp.com/callback";
//	static String replyBody = String.format("{\"to\":\"%s\", \"messages\":[{\"type\":\"text\", \"text\":\"line bot send\"}]}", "Uc7a46420c8125d4fcaa0312f2d47dc10");
//	static String replyBody = "{ \"to\":[\"Udd25c6f918b51a3840dff50eea1cf3af\",\"U314f428f4da380de2b4d21f6902dfb17\"], \"message\":[{\"type\":\"text\", \"text\":\"回報時間 : 04/18/2017 14:01:40回報姓名 : 洪鎮鉉回報內容 : 上課(測試中)\"}]}";
	static String replyBody = "{ \"to\":[\"Udd25c6f918b51a3840dff50eea1cf3af\"], \"messages\":[{\"type\":\"text\", \"text\":\"line bot send\"}]}";
	public static void main(String[] args) {
		sendGetReturnToken(lineurl,replyBody);
	}
	public static String sendGetReturnToken(String url,String replyBody) {
		String token = "pslrmKmSM30059ArgBD2wv4Sm6zRbyjqpdrYIHFZbtGZmqO76wuOBV5p2+re039F7umgZptlue+RUiv+k38Oin6v1DIt5wfS8myZ1Xw3h7RPRDczDJgakudp0I8EheQ+VLE77SiMvDtMGUxcg7nvXAdB04t89/1O/w1cDnyilFU=";
		CommonTools commonTools = new CommonTools();
		HttpsURLConnection con = commonTools.SSLHttpConnection(url);
		try {
			// optional default is GET
			con.setRequestMethod("POST");

			String yourid = "Uc7a46420c8125d4fcaa0312f2d47dc10";

//			String replyBody = String.format("{\"to\":\"%s\", \"messages\":[{\"type\":\"text\", \"text\":\"line bot send\"}]}", yourid);

			// add request header

			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Authorization", "Bearer " + token);  
			con.setUseCaches(false);
			con.setAllowUserInteraction(false);
			con.setInstanceFollowRedirects( false );
			con.setDoInput(true);
			con.setDoOutput(true);
			
//1
//			OutputStream os = con.getOutputStream();
//			BufferedWriter writer = new BufferedWriter(
//			        new OutputStreamWriter(os, "UTF-8"));
//			writer.write(sendjson);
//			writer.flush();
//			writer.close();
//			os.close();
//			con.connect();
//2			
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
                sb.append(line+"\n");
            }
            
            br.close();
            
            System.out.println("WEB return value is : " + sb);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return token;
	}

}
