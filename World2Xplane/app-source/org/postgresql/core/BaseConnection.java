package org.postgresql.core;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.postgresql.PGConnection;
import org.postgresql.jdbc2.TimestampUtils;

public interface BaseConnection extends PGConnection, Connection {
  void cancelQuery() throws SQLException;
  
  ResultSet execSQLQuery(String paramString) throws SQLException;
  
  ResultSet execSQLQuery(String paramString, int paramInt1, int paramInt2) throws SQLException;
  
  void execSQLUpdate(String paramString) throws SQLException;
  
  QueryExecutor getQueryExecutor();
  
  Object getObject(String paramString1, String paramString2) throws SQLException;
  
  Encoding getEncoding() throws SQLException;
  
  TypeInfo getTypeInfo();
  
  boolean haveMinimumCompatibleVersion(String paramString);
  
  boolean haveMinimumServerVersion(String paramString);
  
  byte[] encodeString(String paramString) throws SQLException;
  
  String escapeString(String paramString) throws SQLException;
  
  boolean getStandardConformingStrings();
  
  TimestampUtils getTimestampUtils();
  
  Logger getLogger();
  
  boolean getStringVarcharFlag();
  
  int getTransactionState();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\BaseConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */