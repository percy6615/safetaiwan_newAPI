package safetaiwan_CallBack;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.micromata.opengis.kml.v_2_2_0.SimpleData;

/**
 * 这个是小王
 * @author xiaanming
 * 实现了一个回调接口CallBack，相当于----->背景一
 */
public class AskSafeTaiwan implements CallBackParser {
	/**
	 * 小李对象的引用
	 * 相当于----->背景二
	 */
	private DownloadFile downloadFile; 

	/**
	 * 小王的构造方法，持有小李的引用
	 * @param li
	 */
	public AskSafeTaiwan(){
		
	}
	public AskSafeTaiwan(DownloadFile downloadFile){
		this.downloadFile = downloadFile;
	}
	
	/**
	 * 小王通过这个方法去问小李的问题
	 * @param question  就是小王要问的问题,1 + 1 = ?
	 */
	public void pleaseDownloadKML(){
		System.out.println("start:"+new SimpleDateFormat("yyyy-MM-dd HH:ss:mm").format(new Date(System.currentTimeMillis())));
		//这里用一个线程就是异步，
		DownloadFile downloadFile = new DownloadFile(AskSafeTaiwan.this,"C:\\workspaceJAVA\\safetaiwan_newAPI\\resources\\exampledata\\7912_201703311125.kml");
		this.downloadFile = downloadFile;
		new Thread(downloadFile).start();
		
		//小网问完问题挂掉电话就去干其他的事情了，诳街去了
		play();
	}

	public void play(){
		System.out.println("我要逛街去了");
	}

	/**
	 * 小李知道答案后调用此方法告诉小王，就是所谓的小王的回调方法
	 */

	@Override
	public void parser(String fileName) {
		// TODO Auto-generated method stub
		System.out.println(fileName);
		System.out.println("end:"+new SimpleDateFormat("yyyy-MM-dd HH:ss:mm").format(new Date(System.currentTimeMillis())));
	}
	
}
