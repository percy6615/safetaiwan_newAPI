package safetaiwan_CallBack;

public class mainCallBack {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Li li = new Li(wang,"C:\\workspaceJAVA\\safetaiwan_newAPI\\resources\\exampledata\\7912_201703311125.kml");
		AskSafeTaiwan askSafeTaiwan = new AskSafeTaiwan();
		askSafeTaiwan.pleaseDownloadKML();
//		test();
		
	}
	public static void test(){
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
	}
}
