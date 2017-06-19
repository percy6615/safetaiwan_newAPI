package safetaiwan_CommTools.DBSourceO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.*;



public class DBSourceO implements InterfaceDBSource {
	private Properties props;
	private String URL;
	private String user;
	private String Password;
	private int max=1;
	private List<Connection> connections;
	
	public DBSourceO() throws FileNotFoundException, ClassNotFoundException, IOException {
		this("resources/cfg/jdbc_oracle.properties");
	}

	public DBSourceO(String configFile) throws FileNotFoundException, IOException, ClassNotFoundException {
		props = new Properties();
		props.load(new FileInputStream(configFile));
		URL=props.getProperty("DataBase.jdbc.url");
		user = props.getProperty("DataBase.jdbc.user");
		Password = props.getProperty("DataBase.jdbc.password");
		Class.forName(props.getProperty("DataBase.jdbc.driver"));
		max = Integer.parseInt(props.getProperty("DataBase.jdbc.maxpool"));
	}
	public Connection getConnection() throws SQLException{
		Connection conn = DriverManager.getConnection(URL,user,Password);
		return conn;
		
	}
	public void closeConnection(Connection conn)throws SQLException{
		conn.close();
		
	}
}
