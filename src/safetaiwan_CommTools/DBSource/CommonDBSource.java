package safetaiwan_CommTools.DBSource;

import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.dbms.OracleUtils;
public class CommonDBSource implements DBSource {
	private Properties props;
	private String url;
	private String user;
	private String passwd;
	private ComboPooledDataSource cpds;
	private static volatile  CommonDBSource datasource;
	private static String propertyPath = "resources/cfg/jdbc.properties";
	public static CommonDBSource getInstance() {
		
		if (datasource == null) {
			datasource = new CommonDBSource();
			return datasource;
		} else {
			
			return datasource;
		}
	}

	public CommonDBSource() {
		this(CommonDBSource.propertyPath);
	}

	public CommonDBSource(String configFile) {
		//close config log
		System.setProperty("com.mchange.v2.log.FallbackMLog.DEFAULT_CUTOFF_LEVEL", "WARNING");
		System.setProperty("com.mchange.v2.log.MLog", "com.mchange.v2.log.FallbackMLog");
		cpds = new ComboPooledDataSource();
		props = new Properties();
		try {
			props.load(new FileInputStream(configFile));
			cpds.setDriverClass(props.getProperty("onlyfun.caterpillar.driver"));
		} catch (IOException | PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		url = props.getProperty("onlyfun.caterpillar.url");
		user = props.getProperty("onlyfun.caterpillar.user");
		passwd = props.getProperty("onlyfun.caterpillar.password");

		cpds.setJdbcUrl(url);
		cpds.setUser(user);
		cpds.setPassword(passwd);

		// the settings below are optional -- c3p0 can work with defaults
		// http://www.blogjava.net/Alpha/archive/2015/03/09/262789.html
		String minpoolsize = props.getProperty("onlyfun.caterpillar.minpoolsize");
		String acquireincrement = props.getProperty("onlyfun.caterpillar.acquireincrement");
		String maxpoolsize = props.getProperty("onlyfun.caterpillar.maxpoolsize");
		String maxstatements = props.getProperty("onlyfun.caterpillar.maxstatements");
		String PreferredTestQuery = props.getProperty("onlyfun.caterpillar.PreferredTestQuery");
		String IdleConnectionTestPeriod = props.getProperty("onlyfun.caterpillar.IdleConnectionTestPeriod");
		String MaxIdleTime = props.getProperty("onlyfun.caterpillar.MaxIdleTime");
		String TestConnectionOnCheckout = props.getProperty("onlyfun.caterpillar.TestConnectionOnCheckout");
		cpds.setMinPoolSize(Integer.valueOf(minpoolsize));
		cpds.setAcquireIncrement(Integer.valueOf(acquireincrement));
		cpds.setMaxPoolSize(Integer.valueOf(maxpoolsize));
		cpds.setMaxStatements(Integer.valueOf(maxstatements));
		cpds.setPreferredTestQuery(PreferredTestQuery);
		cpds.setIdleConnectionTestPeriod(Integer.valueOf(IdleConnectionTestPeriod));
		cpds.setMaxIdleTime(Integer.valueOf(MaxIdleTime));
		cpds.setTestConnectionOnCheckout(Boolean.valueOf(TestConnectionOnCheckout));
//		set to 'SELECT 1'      
//		preferredTestQuery = 'SELECT 1 '   
		//set to something much less than wait_timeout, prevents connections from going stale   
//		idleConnectionTestPeriod = 18000      
		//set to something slightly less than wait_timeout, preventing 'stale' connections from being //handed out   
//		maxIdleTime = 25000   
		//if you can take the performance 'hit', set to "true"   
//		testConnectionOnCheckout = true   
	}

	public Connection getConnection()  {
		try {
			return this.cpds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void closeConnection(Connection conn)  {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}