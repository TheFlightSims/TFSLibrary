package org.hsqldb.jdbc.pool;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.naming.StringRefAddr;
import javax.sql.CommonDataSource;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.PooledConnection;
import org.hsqldb.jdbc.JDBCCommonDataSource;
import org.hsqldb.jdbc.JDBCConnection;
import org.hsqldb.jdbc.JDBCDriver;

public class JDBCPooledDataSource extends JDBCCommonDataSource implements ConnectionPoolDataSource, Serializable, Referenceable, CommonDataSource {
  public PooledConnection getPooledConnection() throws SQLException {
    JDBCConnection jDBCConnection = (JDBCConnection)JDBCDriver.getConnection(this.url, this.connectionProps);
    return new JDBCPooledConnection(jDBCConnection);
  }
  
  public PooledConnection getPooledConnection(String paramString1, String paramString2) throws SQLException {
    Properties properties = new Properties();
    properties.setProperty("user", paramString1);
    properties.setProperty("password", paramString2);
    JDBCConnection jDBCConnection = (JDBCConnection)JDBCDriver.getConnection(this.url, properties);
    return new JDBCPooledConnection(jDBCConnection);
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


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\jdbc\pool\JDBCPooledDataSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */