package org.postgresql.core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import org.postgresql.PGStatement;

public interface BaseStatement extends PGStatement, Statement {
  ResultSet createDriverResultSet(Field[] paramArrayOfField, Vector paramVector) throws SQLException;
  
  ResultSet createResultSet(Query paramQuery, Field[] paramArrayOfField, Vector paramVector, ResultCursor paramResultCursor) throws SQLException;
  
  boolean executeWithFlags(String paramString, int paramInt) throws SQLException;
  
  boolean executeWithFlags(int paramInt) throws SQLException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\BaseStatement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */