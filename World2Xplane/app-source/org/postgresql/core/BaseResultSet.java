package org.postgresql.core;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface BaseResultSet extends ResultSet {
  String getFixedString(int paramInt) throws SQLException;
  
  Array createArray(int paramInt) throws SQLException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\BaseResultSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */