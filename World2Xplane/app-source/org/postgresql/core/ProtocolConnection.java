package org.postgresql.core;

import java.sql.SQLException;
import java.sql.SQLWarning;
import org.postgresql.PGNotification;

public interface ProtocolConnection {
  public static final int TRANSACTION_IDLE = 0;
  
  public static final int TRANSACTION_OPEN = 1;
  
  public static final int TRANSACTION_FAILED = 2;
  
  String getHost();
  
  int getPort();
  
  String getUser();
  
  String getDatabase();
  
  String getServerVersion();
  
  Encoding getEncoding();
  
  boolean getStandardConformingStrings();
  
  int getTransactionState();
  
  PGNotification[] getNotifications() throws SQLException;
  
  SQLWarning getWarnings();
  
  QueryExecutor getQueryExecutor();
  
  void sendQueryCancel() throws SQLException;
  
  void close();
  
  boolean isClosed();
  
  int getProtocolVersion();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\ProtocolConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */