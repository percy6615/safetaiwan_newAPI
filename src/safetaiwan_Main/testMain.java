package safetaiwan_Main;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class testMain {
	public static void main(String[] args) {
		StringToTimestamp("2017-03-23 14:54:00");
	}
	public static Timestamp StringToTimestamp(String something) {

		SimpleDateFormat dateFormat = null;
		if (something.contains(".")) {
			dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		}
		else if (something.contains(",")) {
			dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss,SSS");
		}else{
			dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		}
		
		Timestamp timestamp = null;
		Date parsedDate;
		try {
			parsedDate = dateFormat.parse(something);
			timestamp = new java.sql.Timestamp(parsedDate.getTime());

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return timestamp;
	}
}
