package safetaiwan_test;

/**
 * 这个就是小李啦
 * 
 * @author xiaanming
 *
 */
public class Li implements Runnable {
	CallBack call;
	String fileName;

	Li() {

	}

	Li(CallBack call, String fileName) {
		this.call = call;
		this.fileName = fileName;
	}

	/**
	 * 相当于B类有参数为CallBack callBack的f()---->背景三
	 * 
	 * @param callBack
	 * @param question
	 *            小王问的问题
	 */
	public void executeMessage() {

		System.out.println("小王问的问题--->" + fileName);
		// 模拟小李办自己的事情需要很长时间

		double jj = 0;
		for (int i = 0; i < 10000000; i++) {
			for (int j = 0; j < 1000; j++) {
				for (int k = 0; k < 1; k++) {
					
//					System.out.println(jj++);
					jj++;
				}
			}
			
		}
		System.out.println(Integer.MAX_VALUE);
		System.out.println(jj);
		/**
		 * 小李办完自己的事情之后想到了答案是2
		 */
		String result = "答案是2";
		/**
		 * 于是就打电话告诉小王，调用小王中的方法 这就相当于B类反过来调用A的方法D
		 */
		call.parser(result);
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		executeMessage();
	}

}
