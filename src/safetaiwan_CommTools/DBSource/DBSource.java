package safetaiwan_CommTools.DBSource;

import java.sql.Connection;

public interface DBSource {
    public Connection getConnection();
    public void closeConnection(Connection conn) ;
}