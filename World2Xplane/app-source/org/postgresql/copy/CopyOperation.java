package org.postgresql.copy;

import java.sql.SQLException;

public interface CopyOperation {
  int getFieldCount();
  
  int getFormat();
  
  int getFieldFormat(int paramInt);
  
  boolean isActive();
  
  void cancelCopy() throws SQLException;
  
  long getHandledRowCount();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\copy\CopyOperation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */