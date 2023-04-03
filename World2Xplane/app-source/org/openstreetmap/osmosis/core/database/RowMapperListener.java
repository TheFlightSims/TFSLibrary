package org.openstreetmap.osmosis.core.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapperListener<T> {
  void process(T paramT, ResultSet paramResultSet) throws SQLException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\database\RowMapperListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */