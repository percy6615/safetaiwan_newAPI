package safetaiwan_CommTools.DBSource;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBSource {
    public Connection getConnection();
    public void closeConnection(Connection conn) ;
}