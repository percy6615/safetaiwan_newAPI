package safetaiwan_Main;



import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;




public class MessageMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TextMessage textMessage = new TextMessage("hello");
		PushMessage pushMessage = new PushMessage(
		        "Uc7a46420c8125d4fcaa0312f2d47dc10",
		        textMessage
		);
String token = "OtBV3pMIvil0fmHJF2M6mgsRvAin9DDff8PFu8iBSQUdEBMPRIk3r48VnPISpEsxFwwR77B+gqRqF1yrxPVlgHStq75cPTAH5jyhjuOidRJ6PrlY1QdY1hVCxiB6CLMqldvdKtgTDfXexpf4IViFUwdB04t89/1O/w1cDnyilFU=";
//		Response<BotApiResponse> response = LineMessagingServiceBuilder.create(token).build().pushMessage();
//		System.out.println(response.code() + " " + response.message());

//                .create("<channel access token>")
//                .build()
//                .pushMessage(pushMessage)
//                .execute();
	}

}
