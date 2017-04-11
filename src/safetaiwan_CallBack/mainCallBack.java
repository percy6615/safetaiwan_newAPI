package safetaiwan_CallBack;

import java.util.Timer;
import java.util.TimerTask;

public class mainCallBack {

	public static void main(String[] args) {
		 Timer t =new Timer();
		 
		
		TimerTask showtime= new TimerTask(){//也可以用匿名類別的方式，

			@Override
			public void run() {
				AskSafeTaiwan askSafeTaiwan = new AskSafeTaiwan();
				askSafeTaiwan.pleaseDownloadKML();
			}	
		};
		t.schedule(showtime, 50,60000);
	}

}
