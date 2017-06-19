package safetaiwan_test;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import safetaiwan_CommTools.DBSource.DisasterNotificationDBfunction;
import safetaiwan_messageObject.CoordinatesPoint;
import safetaiwan_messageObject.DisasterNotification;

public class simulatedata {
	public static void main(String[] args) {
		// System.out.println(!"Simulate1".contains("Simulate"));;
		DisasterNotificationDBfunction ds = new DisasterNotificationDBfunction();
		List f = fair();
		ds.insertDisasterNotificationList(f);
	}

	public final static java.sql.Timestamp string2Time(String dateString) {
		DateFormat dateFormat;
		dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.TAIWAN);// 設定格式
		// dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss",
		// Locale.ENGLISH);
		dateFormat.setLenient(false);
		java.util.Date timeDate = null;
		try {
			timeDate = dateFormat.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block //util類型
			e.printStackTrace();
		}
		java.sql.Timestamp dateTime = new java.sql.Timestamp(timeDate.getTime());// Timestamp類型,timeDate.getTime()返回一個long型
		return dateTime;
	}

	static List<DisasterNotification> fair() {
		CoordinatesPoint coordinatesPoint1 = new CoordinatesPoint(120.97955, 24.803362);
		CoordinatesPoint coordinatesPoint2 = new CoordinatesPoint(120.992646, 24.80419);
		CoordinatesPoint coordinatesPoint3 = new CoordinatesPoint(120.974402, 24.809019);
		CoordinatesPoint coordinatesPoint4 = new CoordinatesPoint(121.012878, 24.793171);
		CoordinatesPoint coordinatesPoint5 = new CoordinatesPoint(120.995654, 24.784014);
		CoordinatesPoint coordinatesPoint6 = new CoordinatesPoint(121.020752, 24.77575);
		CoordinatesPoint coordinatesPoint7 = new CoordinatesPoint(120.976977, 24.803765);
		CoordinatesPoint coordinatesPoint8 = new CoordinatesPoint(120.916527, 24.785493);
		CoordinatesPoint coordinatesPoint9 = new CoordinatesPoint(120.980634, 24.82139);
		CoordinatesPoint coordinatesPoint10 = new CoordinatesPoint(120.946255, 24.79053);
		CoordinatesPoint coordinatesPoint11 = new CoordinatesPoint(120.931753, 24.775002);
		CoordinatesPoint coordinatesPoint12 = new CoordinatesPoint(120.977781, 24.80367);
		CoordinatesPoint coordinatesPoint13 = new CoordinatesPoint(120.959191, 24.796824);
		CoordinatesPoint coordinatesPoint14 = new CoordinatesPoint(120.928521, 24.788194);
		CoordinatesPoint coordinatesPoint15 = new CoordinatesPoint(121.022487, 24.781687);
		List<CoordinatesPoint> coordinatesPoints1 = new ArrayList();
		List<CoordinatesPoint> coordinatesPoints2 = new ArrayList();
		List<CoordinatesPoint> coordinatesPoints3 = new ArrayList();
		List<CoordinatesPoint> coordinatesPoints4 = new ArrayList();
		List<CoordinatesPoint> coordinatesPoints5 = new ArrayList();
		List<CoordinatesPoint> coordinatesPoints6 = new ArrayList();
		List<CoordinatesPoint> coordinatesPoints7 = new ArrayList();
		List<CoordinatesPoint> coordinatesPoints8 = new ArrayList();
		List<CoordinatesPoint> coordinatesPoints9 = new ArrayList();
		List<CoordinatesPoint> coordinatesPoints10 = new ArrayList();
		List<CoordinatesPoint> coordinatesPoints11 = new ArrayList();
		List<CoordinatesPoint> coordinatesPoints12 = new ArrayList();
		List<CoordinatesPoint> coordinatesPoints13 = new ArrayList();
		List<CoordinatesPoint> coordinatesPoints14 = new ArrayList();
		List<CoordinatesPoint> coordinatesPoints15 = new ArrayList();
		coordinatesPoints1.add(coordinatesPoint1);
		coordinatesPoints2.add(coordinatesPoint2);
		coordinatesPoints3.add(coordinatesPoint3);
		coordinatesPoints4.add(coordinatesPoint4);
		coordinatesPoints5.add(coordinatesPoint5);
		coordinatesPoints6.add(coordinatesPoint6);
		coordinatesPoints7.add(coordinatesPoint7);
		coordinatesPoints8.add(coordinatesPoint8);
		coordinatesPoints9.add(coordinatesPoint9);
		coordinatesPoints10.add(coordinatesPoint10);
		coordinatesPoints11.add(coordinatesPoint11);
		coordinatesPoints12.add(coordinatesPoint12);
		coordinatesPoints13.add(coordinatesPoint13);
		coordinatesPoints14.add(coordinatesPoint14);
		coordinatesPoints15.add(coordinatesPoint15);
		Timestamp t = string2Time("2017-05-18 12:00:00");

		String description = "<table border=\'0\' padding=\'0\' ><tr><td>我很安全</td>2017-04-17 07:27:18</tr></table>";
		DisasterNotification d1 = new DisasterNotification("模擬災情1", coordinatesPoints1, description, "", t, t);
		DisasterNotification d2 = new DisasterNotification("模擬災情2", coordinatesPoints2, description, "", t, t);
		DisasterNotification d3 = new DisasterNotification("模擬災情3", coordinatesPoints3, description, "", t, t);
		DisasterNotification d4 = new DisasterNotification("模擬災情4", coordinatesPoints4, description, "", t, t);
		DisasterNotification d5 = new DisasterNotification("模擬災情5", coordinatesPoints5, description, "", t, t);
		DisasterNotification d6 = new DisasterNotification("模擬災情6", coordinatesPoints6, description, "", t, t);
		DisasterNotification d7 = new DisasterNotification("模擬災情7", coordinatesPoints7, description, "", t, t);
		DisasterNotification d8 = new DisasterNotification("模擬災情8", coordinatesPoints8, description, "", t, t);
		DisasterNotification d9 = new DisasterNotification("模擬災情9", coordinatesPoints9, description, "", t, t);
		DisasterNotification d10 = new DisasterNotification("模擬災情10", coordinatesPoints10, description, "", t, t);
		DisasterNotification d11 = new DisasterNotification("模擬災情11", coordinatesPoints11, description, "", t, t);
		DisasterNotification d12 = new DisasterNotification("模擬災情12", coordinatesPoints12, description, "", t, t);
		DisasterNotification d13 = new DisasterNotification("模擬災情13", coordinatesPoints13, description, "", t, t);
		DisasterNotification d14 = new DisasterNotification("模擬災情14", coordinatesPoints14, description, "", t, t);
		DisasterNotification d15 = new DisasterNotification("模擬災情15", coordinatesPoints15, description, "", t, t);
		List DisasterNotifications = new ArrayList();
		DisasterNotifications.add(d1);
		DisasterNotifications.add(d2);
		DisasterNotifications.add(d3);
		DisasterNotifications.add(d4);
		DisasterNotifications.add(d5);
		DisasterNotifications.add(d6);
		DisasterNotifications.add(d7);
		DisasterNotifications.add(d8);
		DisasterNotifications.add(d9);
		DisasterNotifications.add(d10);
		DisasterNotifications.add(d11);
		DisasterNotifications.add(d12);
		DisasterNotifications.add(d13);
		DisasterNotifications.add(d14);
		DisasterNotifications.add(d15);
//		DisasterNotificationDBfunction ds = new DisasterNotificationDBfunction();
//		ds.insertDisasterNotificationfair(d1);
//		ds.insertDisasterNotificationfair(d2);
//		ds.insertDisasterNotificationfair(d3);
//		ds.insertDisasterNotificationfair(d4);
//		ds.insertDisasterNotificationfair(d5);
//		ds.insertDisasterNotificationfair(d6);
//		ds.insertDisasterNotificationfair(d7);
//		ds.insertDisasterNotificationfair(d8);
//		ds.insertDisasterNotificationfair(d9);
//		ds.insertDisasterNotificationfair(d10);
//		ds.insertDisasterNotificationfair(d11);
//		ds.insertDisasterNotificationfair(d12);
//		ds.insertDisasterNotificationfair(d13);
//		ds.insertDisasterNotificationfair(d14);
//		ds.insertDisasterNotificationfair(d15);
		return DisasterNotifications;
	}
}
