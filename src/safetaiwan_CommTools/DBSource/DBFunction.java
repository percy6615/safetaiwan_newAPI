package safetaiwan_CommTools.DBSource;

import java.sql.Connection;

public class DBFunction {

	public Connection getConnection() {
		DBSource CDBS = CommonDBSource.getInstance();
		Connection conn = CDBS.getConnection();
		return conn;
	}
}
