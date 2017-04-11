package safetaiwan_CallBack;


public class DownloadFile implements Runnable {
	CallBackParser call;
	String fileName;

	DownloadFile() {

	}

	DownloadFile(CallBackParser call, String fileName) {
		this.call = call;
		this.fileName = fileName;
	}

	@Override
	public void run() {
		downloadKML();
	}

	public void downloadKML() {

		double jj = 0;
		for (int i = 0; i < 10000000; i++) {
			for (int j = 0; j < 1000; j++) {
				for (int k = 0; k < 1; k++) {
					jj++;
				}
			}

		}
		System.out.println(Integer.MAX_VALUE);
		System.out.println(jj);

		String fileName = "FileName";
		call.parser(fileName);
	}

}
