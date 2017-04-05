package safetaiwan_messageObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import safetaiwan_CommTools.DBSource.DBFunction;

public class DisasterNotificationDBfunction extends DBFunction{
	
	public void insertDisasterNotificationToDB(DisasterNotification disasterNotification){
		Connection conn = getConnection();
		String insertSQL = "insert into disaster.safetaiwan_disasternotification values (?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(insertSQL);
			pstmt.setString(1, disasterNotification.getUUID());
			pstmt.setString(2, disasterNotification.getName());
			pstmt.setDouble(3, disasterNotification.getCoordinatesPoints().get(0).getLongitudeCoord());
			pstmt.setString(4, disasterNotification.getName());
			pstmt.setString(5, disasterNotification.getName());
			pstmt.setString(6, disasterNotification.getName());
			pstmt.setString(7, disasterNotification.getName());
			pstmt.setString(8, disasterNotification.getName());
			pstmt.setString(9, disasterNotification.getName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
