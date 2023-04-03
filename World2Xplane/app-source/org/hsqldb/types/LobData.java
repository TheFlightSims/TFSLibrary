package org.hsqldb.types;

import org.hsqldb.SessionInterface;

public interface LobData {
  long length(SessionInterface paramSessionInterface);
  
  long getId();
  
  void setId(long paramLong);
  
  void setSession(SessionInterface paramSessionInterface);
  
  boolean isBinary();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\types\LobData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */