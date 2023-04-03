package org.postgresql.copy;

import java.sql.SQLException;

public interface CopyIn extends CopyOperation {
  void writeToCopy(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws SQLException;
  
  void flushCopy() throws SQLException;
  
  long endCopy() throws SQLException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\copy\CopyIn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */