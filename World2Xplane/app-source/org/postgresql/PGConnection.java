package org.postgresql;

import java.sql.SQLException;
import org.postgresql.copy.CopyManager;
import org.postgresql.fastpath.Fastpath;
import org.postgresql.largeobject.LargeObjectManager;

public interface PGConnection {
  PGNotification[] getNotifications() throws SQLException;
  
  CopyManager getCopyAPI() throws SQLException;
  
  LargeObjectManager getLargeObjectAPI() throws SQLException;
  
  Fastpath getFastpathAPI() throws SQLException;
  
  void addDataType(String paramString1, String paramString2);
  
  void addDataType(String paramString, Class paramClass) throws SQLException;
  
  void setPrepareThreshold(int paramInt);
  
  int getPrepareThreshold();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\PGConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */