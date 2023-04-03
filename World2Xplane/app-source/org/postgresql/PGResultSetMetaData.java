package org.postgresql;

import java.sql.SQLException;

public interface PGResultSetMetaData {
  String getBaseColumnName(int paramInt) throws SQLException;
  
  String getBaseTableName(int paramInt) throws SQLException;
  
  String getBaseSchemaName(int paramInt) throws SQLException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\PGResultSetMetaData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */