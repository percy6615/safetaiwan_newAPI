package safetaiwan_CommTools.DBSource;

import java.sql.Connection;

public class DBFunction  {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	public Connection getConnection(){
		DBSource CDBS = CommonDBSource.getInstance();
		Connection conn = CDBS.getConnection();
		return conn;
	}

	
}
