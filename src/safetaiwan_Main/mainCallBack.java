package safetaiwan_Main;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import safetaiwan_CallBack.AskSafeTaiwan;

public class mainCallBack {

	public static void main(String[] args) {
		Timer t = new Timer();
		TimerTask showtime = new TimerTask() {// 也可以用匿名類別的方式，
			@Override
			public void run() {
				Date d = new Date(System.currentTimeMillis());
				AskSafeTaiwan askSafeTaiwan = new AskSafeTaiwan();
				askSafeTaiwan.pleaseDownloadKML(d);
			}
		};
		t.schedule(showtime, 50, 20000);
	}

}
