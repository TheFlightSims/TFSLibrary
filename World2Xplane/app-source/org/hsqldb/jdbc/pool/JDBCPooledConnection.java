package org.hsqldb.jdbc.pool;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.ConnectionEvent;
import javax.sql.ConnectionEventListener;
import javax.sql.PooledConnection;
import javax.sql.StatementEventListener;
import org.hsqldb.jdbc.JDBCConnection;
import org.hsqldb.jdbc.JDBCConnectionEventListener;
import org.hsqldb.lib.OrderedHashSet;

public class JDBCPooledConnection implements PooledConnection, JDBCConnectionEventListener {
  protected OrderedHashSet listeners = new OrderedHashSet();
  
  protected JDBCConnection connection;
  
  protected JDBCConnection userConnection;
  
  protected boolean isInUse;
  
  public synchronized Connection getConnection() throws SQLException {
    if (this.isInUse)
      throw new SQLException("Connection in use"); 
    this.isInUse = true;
    this.userConnection = new JDBCConnection(this.connection, this);
    return (Connection)this.userConnection;
  }
  
  public void close() throws SQLException {
    if (this.connection != null) {
      this.connection.closeFully();
      this.connection = null;
    } 
  }
  
  public void addConnectionEventListener(ConnectionEventListener paramConnectionEventListener) {
    this.listeners.add(paramConnectionEventListener);
  }
  
  public void removeConnectionEventListener(ConnectionEventListener paramConnectionEventListener) {
    this.listeners.remove(paramConnectionEventListener);
  }
  
  public void addStatementEventListener(StatementEventListener paramStatementEventListener) {}
  
  public void removeStatementEventListener(StatementEventListener paramStatementEventListener) {}
  
  public synchronized void connectionClosed() {
    ConnectionEvent connectionEvent = new ConnectionEvent(this);
    this.userConnection = null;
    reset();
    for (byte b = 0; b < this.listeners.size(); b++) {
      ConnectionEventListener connectionEventListener = (ConnectionEventListener)this.listeners.get(b);
      connectionEventListener.connectionClosed(connectionEvent);
    } 
  }
  
  public synchronized void connectionErrorOccured(SQLException paramSQLException) {
    ConnectionEvent connectionEvent = new ConnectionEvent(this, paramSQLException);
    reset();
    for (byte b = 0; b < this.listeners.size(); b++) {
      ConnectionEventListener connectionEventListener = (ConnectionEventListener)this.listeners.get(b);
      connectionEventListener.connectionErrorOccurred(connectionEvent);
    } 
  }
  
  public synchronized boolean isInUse() {
    return this.isInUse;
  }
  
  public synchronized void reset() {
    if (this.userConnection != null)
      try {
        this.userConnection.close();
      } catch (SQLException sQLException) {} 
    try {
      this.connection.reset();
    } catch (SQLException sQLException) {}
    this.isInUse = false;
  }
  
  public synchronized void release() {
    if (this.userConnection != null)
      try {
        this.userConnection.close();
      } catch (SQLException sQLException) {} 
    try {
      this.connection.close();
    } catch (SQLException sQLException) {}
    this.isInUse = false;
  }
  
  public JDBCPooledConnection(JDBCConnection paramJDBCConnection) {
    this.connection = paramJDBCConnection;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\jdbc\pool\JDBCPooledConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */