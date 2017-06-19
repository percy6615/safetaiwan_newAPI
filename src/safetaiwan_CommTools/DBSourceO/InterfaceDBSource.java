package safetaiwan_CommTools.DBSourceO;
import java.sql.Connection;
import java.sql.SQLException;

public interface InterfaceDBSource {
	public void closeConnection(Connection conn) throws SQLException;
	public Connection getConnection() throws SQLException;
}
