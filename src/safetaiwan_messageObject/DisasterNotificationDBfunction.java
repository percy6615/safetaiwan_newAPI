package safetaiwan_messageObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import safetaiwan_CommTools.DBSource.DBFunction;

public class DisasterNotificationDBfunction extends DBFunction {

	public void insertDisasterNotificationToDB(DisasterNotification disasterNotification) {
		Connection conn = getConnection();
		String insertSQL = "insert into disaster.safetaiwan_disasternotification values (?,?,?,?,?,?,?,?,?)";
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
			pstmt.executeUpdate();
			pstmt.clearParameters();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void createDisasterNotification(){
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String createSQL = "CREATE TABLE `safetaiwan_disasternotification` ( "+
				"`uuid` varchar(64) NOT NULL, "+
				"`name` varchar(32) DEFAULT NULL, "+
				"`longitudeCoord` decimal(16,0) DEFAULT NULL, "+
				"`latitudeCoord` varchar(16) DEFAULT NULL, "+
				"`description` varchar(256) DEFAULT NULL, "+
				"`iconStyleID` varchar(32) DEFAULT NULL, "+
				"`reportContent` varchar(128) DEFAULT NULL, "+
				"`reportDate` timestamp(6) NULL DEFAULT NULL, "+
				"`imgURL` varchar(128) DEFAULT NULL, "+
				"`filename` varchar(64) DEFAULT NULL, "+
				"PRIMARY KEY (`uuid`) "+
				") ENGINE=InnoDB DEFAULT CHARSET=utf8 ";
		try {
			pstmt = conn.prepareStatement(createSQL);
			pstmt.executeUpdate();
			pstmt.clearParameters();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}