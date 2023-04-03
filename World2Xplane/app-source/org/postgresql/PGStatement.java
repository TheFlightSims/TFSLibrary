package org.postgresql;

import java.sql.SQLException;

public interface PGStatement {
  public static final long DATE_POSITIVE_INFINITY = 9223372036825200000L;
  
  public static final long DATE_NEGATIVE_INFINITY = -9223372036832400000L;
  
  long getLastOID() throws SQLException;
  
  void setUseServerPrepare(boolean paramBoolean) throws SQLException;
  
  boolean isUseServerPrepare();
  
  void setPrepareThreshold(int paramInt) throws SQLException;
  
  int getPrepareThreshold();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\PGStatement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */