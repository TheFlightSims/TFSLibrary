package org.hsqldb.jdbc;

import java.sql.SQLException;

public interface JDBCConnectionEventListener {
  void connectionClosed();
  
  void connectionErrorOccured(SQLException paramSQLException);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\jdbc\JDBCConnectionEventListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */