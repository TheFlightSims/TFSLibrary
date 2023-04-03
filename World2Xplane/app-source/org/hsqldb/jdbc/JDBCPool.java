package org.hsqldb.jdbc;

import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Wrapper;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.naming.StringRefAddr;
import javax.sql.ConnectionEvent;
import javax.sql.ConnectionEventListener;
import javax.sql.DataSource;
import javax.sql.PooledConnection;
import javax.sql.StatementEvent;
import javax.sql.StatementEventListener;
import org.hsqldb.jdbc.pool.JDBCPooledConnection;
import org.hsqldb.jdbc.pool.JDBCPooledDataSource;

public class JDBCPool extends JDBCCommonDataSource implements DataSource, Serializable, Referenceable, ConnectionEventListener, StatementEventListener, Wrapper {
  AtomicIntegerArray states;
  
  JDBCPooledConnection[] connections;
  
  JDBCPooledDataSource source = new JDBCPooledDataSource();
  
  volatile boolean closed;
  
  public Connection getConnection() throws SQLException {
    int i = 300;
    if (this.source.loginTimeout != 0)
      i = this.source.loginTimeout * 10; 
    if (this.closed)
      throw new SQLException("connection pool is closed"); 
    for (byte b = 0; b < i; b++) {
      for (byte b1 = 0; b1 < this.states.length(); b1++) {
        if (this.states.compareAndSet(b1, 1, 2))
          return this.connections[b1].getConnection(); 
        if (this.states.compareAndSet(b1, 0, 2))
          try {
            JDBCPooledConnection jDBCPooledConnection = (JDBCPooledConnection)this.source.getPooledConnection();
            jDBCPooledConnection.addConnectionEventListener(this);
            jDBCPooledConnection.addStatementEventListener(this);
            this.connections[b1] = jDBCPooledConnection;
            return this.connections[b1].getConnection();
          } catch (SQLException sQLException) {
            this.states.set(b1, 0);
          }  
      } 
      try {
        Thread.sleep(100L);
      } catch (InterruptedException interruptedException) {}
    } 
    throw JDBCUtil.invalidArgument();
  }
  
  public Connection getConnection(String paramString1, String paramString2) throws SQLException {
    return this.source.getPooledConnection(paramString1, paramString2).getConnection();
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
    reference.add(new StringRefAddr("database", this.source.getDatabase()));
    reference.add(new StringRefAddr("user", this.source.getUser()));
    reference.add(new StringRefAddr("password", this.source.password));
    reference.add(new StringRefAddr("loginTimeout", Integer.toString(this.source.loginTimeout)));
    reference.add(new StringRefAddr("poolSize", Integer.toString(this.connections.length)));
    return reference;
  }
  
  public void connectionClosed(ConnectionEvent paramConnectionEvent) {
    PooledConnection pooledConnection = (PooledConnection)paramConnectionEvent.getSource();
    for (byte b = 0; b < this.connections.length; b++) {
      if (this.connections[b] == pooledConnection) {
        this.states.set(b, 1);
        break;
      } 
    } 
  }
  
  public void connectionErrorOccurred(ConnectionEvent paramConnectionEvent) {
    PooledConnection pooledConnection = (PooledConnection)paramConnectionEvent.getSource();
    for (byte b = 0; b < this.connections.length; b++) {
      if (this.connections[b] == pooledConnection) {
        this.states.set(b, 2);
        this.connections[b] = null;
        this.states.set(b, 0);
        break;
      } 
    } 
  }
  
  public void statementClosed(StatementEvent paramStatementEvent) {}
  
  public void statementErrorOccurred(StatementEvent paramStatementEvent) {}
  
  public PrintWriter getLogWriter() throws SQLException {
    return this.source.getLogWriter();
  }
  
  public void setLogWriter(PrintWriter paramPrintWriter) throws SQLException {
    this.source.setLogWriter(paramPrintWriter);
  }
  
  public void setLoginTimeout(int paramInt) throws SQLException {
    this.source.setLoginTimeout(paramInt);
  }
  
  public int getLoginTimeout() throws SQLException {
    return this.source.getLoginTimeout();
  }
  
  public String getDescription() {
    return "org.hsqldb.jdbc.JDBCPool max size " + this.connections.length;
  }
  
  public String getDataSourceName() {
    return "org.hsqldb.jdbc.JDBCPool";
  }
  
  public String getDatabaseName() {
    return this.source.getUrl();
  }
  
  public String getDatabase() {
    return this.source.getDatabase();
  }
  
  public String getUrl() {
    return this.source.getUrl();
  }
  
  public String getUser() {
    return this.source.getUser();
  }
  
  public void setDatabaseName(String paramString) {
    this.source.setDatabaseName(paramString);
  }
  
  public void setDatabase(String paramString) {
    this.source.setDatabase(paramString);
  }
  
  public void setUrl(String paramString) {
    this.source.setUrl(paramString);
  }
  
  public void setPassword(String paramString) {
    this.source.setPassword(paramString);
  }
  
  public void setUser(String paramString) {
    this.source.setUser(paramString);
  }
  
  public void setProperties(Properties paramProperties) {
    this.source.setProperties(paramProperties);
  }
  
  public Logger getParentLogger() throws SQLFeatureNotSupportedException {
    throw (SQLFeatureNotSupportedException)JDBCUtil.notSupported();
  }
  
  public JDBCPool() {
    this(10);
  }
  
  public JDBCPool(int paramInt) {
    this.connections = new JDBCPooledConnection[paramInt];
    this.states = new AtomicIntegerArray(paramInt);
  }
  
  public void close(int paramInt) throws SQLException {
    if (paramInt < 0 || paramInt > 60)
      throw JDBCUtil.outOfRangeArgument(); 
    if (this.closed)
      return; 
    this.closed = true;
    try {
      Thread.sleep((1000 * paramInt));
    } catch (Throwable throwable) {}
    byte b;
    for (b = 0; b < this.connections.length; b++) {
      if (this.connections[b] != null)
        this.connections[b].release(); 
    } 
    for (b = 0; b < this.connections.length; b++)
      this.connections[b] = null; 
  }
  
  static interface RefState {
    public static final int empty = 0;
    
    public static final int available = 1;
    
    public static final int allocated = 2;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\jdbc\JDBCPool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */