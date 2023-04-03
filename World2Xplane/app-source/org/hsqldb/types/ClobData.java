package org.hsqldb.types;

import java.io.Reader;
import org.hsqldb.SessionInterface;

public interface ClobData extends LobData {
  char[] getChars(SessionInterface paramSessionInterface, long paramLong, int paramInt);
  
  long length(SessionInterface paramSessionInterface);
  
  String getSubString(SessionInterface paramSessionInterface, long paramLong, int paramInt);
  
  ClobData getClob(SessionInterface paramSessionInterface, long paramLong1, long paramLong2);
  
  ClobData duplicate(SessionInterface paramSessionInterface);
  
  void truncate(SessionInterface paramSessionInterface, long paramLong);
  
  Reader getCharacterStream(SessionInterface paramSessionInterface);
  
  void setString(SessionInterface paramSessionInterface, long paramLong, String paramString);
  
  void setClob(SessionInterface paramSessionInterface, long paramLong1, ClobData paramClobData, long paramLong2, long paramLong3);
  
  void setChars(SessionInterface paramSessionInterface, long paramLong, char[] paramArrayOfchar, int paramInt1, int paramInt2);
  
  void setCharacterStream(SessionInterface paramSessionInterface, long paramLong, Reader paramReader);
  
  long position(SessionInterface paramSessionInterface, String paramString, long paramLong);
  
  long position(SessionInterface paramSessionInterface, ClobData paramClobData, long paramLong);
  
  long nonSpaceLength(SessionInterface paramSessionInterface);
  
  Reader getCharacterStream(SessionInterface paramSessionInterface, long paramLong1, long paramLong2);
  
  long getId();
  
  void setId(long paramLong);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\types\ClobData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */