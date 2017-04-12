package safetaiwan_CommTools.DBSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import safetaiwan_messageObject.DisasterNotification;

public class DisasterNotificationDBfunction extends DBFunction {
	
	
	private static volatile  DisasterNotificationDBfunction disasterNotificationDBfunction;

	public static DisasterNotificationDBfunction getInstance() {
		if (disasterNotificationDBfunction == null) {
			disasterNotificationDBfunction = new DisasterNotificationDBfunction();
			return disasterNotificationDBfunction;
		} else {
			return disasterNotificationDBfunction;
		}
	}
	
	public void insertDisasterNotificationList(List<DisasterNotification> disasterNotification) {
		Connection conn = getConnection();
		String insertSQL = "insert into safetaiwan_disasternotification values (?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(insertSQL);
			for (int i = 0, iend = disasterNotification.size(); i < iend; i++) {

				pstmt.setString(1, disasterNotification.get(i).getUUID());
				pstmt.setString(2, disasterNotification.get(i).getName());
				pstmt.setDouble(3, disasterNotification.get(i).getCoordinatesPoints().get(0).getLongitudeCoord());
				pstmt.setDouble(4, disasterNotification.get(i).getCoordinatesPoints().get(0).getLatitudeCoord());
				pstmt.setString(5, disasterNotification.get(i).getDescription());
				pstmt.setString(6, disasterNotification.get(i).getIconStyleID());
				pstmt.setString(7, disasterNotification.get(i).getReportContent());
				pstmt.setTimestamp(8, disasterNotification.get(i).getReportDate());
				pstmt.setString(9, disasterNotification.get(i).getImgURL());
				pstmt.setString(10, disasterNotification.get(i).getFileName());
				pstmt.setTimestamp(11, disasterNotification.get(i).getKMLTime());
				pstmt.setInt(12, disasterNotification.get(i).getFlag());
				pstmt.addBatch();
				
			}
			pstmt.executeBatch();
			pstmt.clearParameters();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertDisasterNotification(DisasterNotification disasterNotification) {
		Connection conn = getConnection();
		String insertSQL = "insert into safetaiwan_disasternotification values (?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(insertSQL);
			pstmt.setString(1, disasterNotification.getUUID());
			pstmt.setString(2, disasterNotification.getName());
			pstmt.setDouble(3, disasterNotification.getCoordinatesPoints().get(0).getLongitudeCoord());
			pstmt.setDouble(4, disasterNotification.getCoordinatesPoints().get(0).getLatitudeCoord());
			pstmt.setString(5, disasterNotification.getDescription());
			pstmt.setString(6, disasterNotification.getIconStyleID());
			pstmt.setString(7, disasterNotification.getReportContent());
			pstmt.setTimestamp(8, disasterNotification.getReportDate());
			pstmt.setString(9, disasterNotification.getImgURL());
			pstmt.setString(10, disasterNotification.getFileName());
			pstmt.setTimestamp(11, disasterNotification.getKMLTime());
			pstmt.setInt(12, disasterNotification.getFlag());
			pstmt.executeUpdate();
			pstmt.clearParameters();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createDisasterNotification() {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String selectSQL = "SELECT 1 as tf FROM information_schema.tables where table_name = 'safetaiwan_disasternotification'";
		String createSQL = "CREATE TABLE `safetaiwan_disasternotification` ( " + "`uuid` varchar(64) NOT NULL, "
				+ "`name` varchar(32) DEFAULT NULL, " + "`longitudeCoord` decimal(16,0) DEFAULT NULL, "
				+ "`latitudeCoord` decimal(16,0) DEFAULT NULL, " + "`description` varchar(256) DEFAULT NULL, "
				+ "`iconStyleID` varchar(32) DEFAULT NULL, " + "`reportContent` varchar(128) DEFAULT NULL, "
				+ "`reportDate` timestamp(6) NULL DEFAULT NULL, " + "`imgURL` varchar(128) DEFAULT NULL, "
				+ "`filename` varchar(64) DEFAULT NULL, `kmltime` timestamp(6) NULL DEFAULT NULL, `flag` int(11) DEFAULT NULL,"
				+ "PRIMARY KEY (`uuid`) " + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 ";
		try {
			ResultSet r = conn.prepareStatement(selectSQL).executeQuery();
			if (r.next()) {
				System.out.println(r.getInt("tf"));
				return;
			} else {
				pstmt = conn.prepareStatement(createSQL);
				pstmt.executeUpdate();
				pstmt.clearParameters();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	
	
	public Timestamp getDbReportTime(){
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String SQL = "select max(reportdate) as reportdate from disaster.safetaiwan_disasternotification ";
		Timestamp t = null;
		try {
			ResultSet r = conn.prepareStatement(SQL).executeQuery();
			if(r.next()) {
				t = r.getTimestamp("reportdate");
			}
			r.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;	
	}
}
