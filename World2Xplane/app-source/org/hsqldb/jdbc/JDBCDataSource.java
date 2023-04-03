package org.hsqldb.jdbc;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Wrapper;
import java.util.Properties;
import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.naming.StringRefAddr;
import javax.sql.DataSource;

public class JDBCDataSource extends JDBCCommonDataSource implements DataSource, Serializable, Referenceable, Wrapper {
  public Connection getConnection() throws SQLException {
    if (this.url == null)
      throw JDBCUtil.nullArgument("url"); 
    if (this.connectionProps == null) {
      if (this.user == null)
        throw JDBCUtil.invalidArgument("user"); 
      if (this.password == null)
        throw JDBCUtil.invalidArgument("password"); 
      return getConnection(this.user, this.password);
    } 
    return (this.connectionProps == null) ? getConnection(this.url, new Properties()) : getConnection(this.url, this.connectionProps);
  }
  
  public Connection getConnection(String paramString1, String paramString2) throws SQLException {
    if (paramString1 == null)
      throw JDBCUtil.invalidArgument("user"); 
    if (paramString2 == null)
      throw JDBCUtil.invalidArgument("password"); 
    Properties properties = new Properties();
    properties.setProperty("user", paramString1);
    properties.setProperty("password", paramString2);
    properties.setProperty("loginTimeout", Integer.toString(this.loginTimeout));
    return getConnection(this.url, properties);
  }
  
  private Connection getConnection(String paramString, Properties paramProperties) throws SQLException {
    if (!paramString.startsWith("jdbc:hsqldb:"))
      paramString = "jdbc:hsqldb:" + paramString; 
    return JDBCDriver.getConnection(paramString, paramProperties);
  }
  
  public <T> T unwrap(Class<T> paramClass) throws SQLException {
    if (isWrapperFor(paramClass))
      return (T)this; 
    throw JDBCUtil.invalidArgument("iface: " + paramClass);
  }
  
  public boolean isWrapperFor(Class<?> paramClass) throws SQLException {
    return (paramClass != null && paramClass.isAssignableFrom(getClass()));
  }
  
  public Reference getReference() throws NamingException {
    String str = "org.hsqldb.jdbc.JDBCDataSourceFactory";
    Reference reference = new Reference(getClass().getName(), str, null);
    reference.add(new StringRefAddr("database", getDatabase()));
    reference.add(new StringRefAddr("user", getUser()));
    reference.add(new StringRefAddr("password", this.password));
    reference.add(new StringRefAddr("loginTimeout", Integer.toString(this.loginTimeout)));
    return reference;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\jdbc\JDBCDataSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */