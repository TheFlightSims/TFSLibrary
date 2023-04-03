package org.hsqldb.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;
import org.hsqldb.DatabaseURL;
import org.hsqldb.persist.HsqlProperties;

public class JDBCDriver implements Driver {
  public static JDBCDriver driverInstance;
  
  public final ThreadLocal<JDBCConnection> threadConnection = new ThreadLocal<JDBCConnection>();
  
  public Connection connect(String paramString, Properties paramProperties) throws SQLException {
    if (paramString.regionMatches(true, 0, "jdbc:default:connection", 0, "jdbc:default:connection".length())) {
      JDBCConnection jDBCConnection = this.threadConnection.get();
      return (jDBCConnection == null) ? null : jDBCConnection;
    } 
    return getConnection(paramString, paramProperties);
  }
  
  public static Connection getConnection(String paramString, Properties paramProperties) throws SQLException {
    final HsqlProperties props = DatabaseURL.parseURL(paramString, true, false);
    if (hsqlProperties == null)
      throw JDBCUtil.invalidArgument(); 
    if (hsqlProperties.isEmpty())
      return null; 
    long l1 = 0L;
    if (paramProperties != null)
      l1 = HsqlProperties.getIntegerProperty(paramProperties, "loginTimeout", 0); 
    hsqlProperties.addProperties(paramProperties);
    if (l1 == 0L)
      l1 = DriverManager.getLoginTimeout(); 
    if (l1 == 0L)
      return new JDBCConnection(hsqlProperties); 
    String str = hsqlProperties.getProperty("connection_type");
    if (DatabaseURL.isInProcessDatabaseType(str))
      return new JDBCConnection(hsqlProperties); 
    final JDBCConnection[] conn = new JDBCConnection[1];
    final SQLException[] ex = new SQLException[1];
    Thread thread = new Thread() {
        public void run() {
          try {
            conn[0] = new JDBCConnection(props);
          } catch (SQLException sQLException) {
            ex[0] = sQLException;
          } 
        }
      };
    thread.start();
    long l2 = System.currentTimeMillis();
    try {
      thread.join(1000L * l1);
    } catch (InterruptedException interruptedException) {}
    try {
      thread.stop();
    } catch (Exception exception) {
      try {
        thread.setContextClassLoader(null);
      } catch (Throwable throwable) {}
    } finally {
      try {
        thread.setContextClassLoader(null);
      } catch (Throwable throwable) {}
    } 
    if (arrayOfSQLException[0] != null)
      throw arrayOfSQLException[0]; 
    if (arrayOfJDBCConnection[0] != null)
      return arrayOfJDBCConnection[0]; 
    throw JDBCUtil.sqlException(1351);
  }
  
  public boolean acceptsURL(String paramString) {
    return (paramString == null) ? false : (paramString.regionMatches(true, 0, "jdbc:hsqldb:", 0, "jdbc:hsqldb:".length()) ? true : (paramString.regionMatches(true, 0, "jdbc:default:connection", 0, "jdbc:default:connection".length())));
  }
  
  public DriverPropertyInfo[] getPropertyInfo(String paramString, Properties paramProperties) {
    if (!acceptsURL(paramString))
      return new DriverPropertyInfo[0]; 
    String[] arrayOfString = { "true", "false" };
    DriverPropertyInfo[] arrayOfDriverPropertyInfo = new DriverPropertyInfo[6];
    if (paramProperties == null)
      paramProperties = new Properties(); 
    DriverPropertyInfo driverPropertyInfo = new DriverPropertyInfo("user", null);
    driverPropertyInfo.value = paramProperties.getProperty("user");
    driverPropertyInfo.required = true;
    arrayOfDriverPropertyInfo[0] = driverPropertyInfo;
    driverPropertyInfo = new DriverPropertyInfo("password", null);
    driverPropertyInfo.value = paramProperties.getProperty("password");
    driverPropertyInfo.required = true;
    arrayOfDriverPropertyInfo[1] = driverPropertyInfo;
    driverPropertyInfo = new DriverPropertyInfo("get_column_name", null);
    driverPropertyInfo.value = paramProperties.getProperty("get_column_name", "true");
    driverPropertyInfo.required = false;
    driverPropertyInfo.choices = arrayOfString;
    arrayOfDriverPropertyInfo[2] = driverPropertyInfo;
    driverPropertyInfo = new DriverPropertyInfo("ifexists", null);
    driverPropertyInfo.value = paramProperties.getProperty("ifexists", "false");
    driverPropertyInfo.required = false;
    driverPropertyInfo.choices = arrayOfString;
    arrayOfDriverPropertyInfo[3] = driverPropertyInfo;
    driverPropertyInfo = new DriverPropertyInfo("default_schema", null);
    driverPropertyInfo.value = paramProperties.getProperty("default_schema", "false");
    driverPropertyInfo.required = false;
    driverPropertyInfo.choices = arrayOfString;
    arrayOfDriverPropertyInfo[4] = driverPropertyInfo;
    driverPropertyInfo = new DriverPropertyInfo("shutdown", null);
    driverPropertyInfo.value = paramProperties.getProperty("shutdown", "false");
    driverPropertyInfo.required = false;
    driverPropertyInfo.choices = arrayOfString;
    arrayOfDriverPropertyInfo[5] = driverPropertyInfo;
    return arrayOfDriverPropertyInfo;
  }
  
  public int getMajorVersion() {
    return 2;
  }
  
  public int getMinorVersion() {
    return 3;
  }
  
  public boolean jdbcCompliant() {
    return true;
  }
  
  public Logger getParentLogger() throws SQLFeatureNotSupportedException {
    throw (SQLFeatureNotSupportedException)JDBCUtil.notSupported();
  }
  
  static {
    try {
      driverInstance = new JDBCDriver();
      DriverManager.registerDriver(driverInstance);
    } catch (Exception exception) {}
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\jdbc\JDBCDriver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */