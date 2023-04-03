package org.postgresql.copy;

import java.sql.SQLException;

public interface CopyOut extends CopyOperation {
  byte[] readFromCopy() throws SQLException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\copy\CopyOut.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */