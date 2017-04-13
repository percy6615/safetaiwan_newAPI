package safetaiwan_CommTools.DBSource;

import java.sql.Connection;

public class DBFunction {

	public Connection getConnection() {
		DBSource CDBS = CommonDBSource.getInstance();
//		DBSource CDBS = new CommonDBSource();
		Connection conn = CDBS.getConnection();
		return conn;
	}

	public void SQLToDB(String sql) {

	}

}
