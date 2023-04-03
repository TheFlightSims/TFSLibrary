package org.postgresql.core;

import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.Vector;

public interface ResultHandler {
  void handleResultRows(Query paramQuery, Field[] paramArrayOfField, Vector paramVector, ResultCursor paramResultCursor);
  
  void handleCommandStatus(String paramString, int paramInt, long paramLong);
  
  void handleWarning(SQLWarning paramSQLWarning);
  
  void handleError(SQLException paramSQLException);
  
  void handleCompletion() throws SQLException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\ResultHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */