package org.hsqldb.types;

import java.io.InputStream;
import org.hsqldb.SessionInterface;

public interface BlobData extends LobData {
  byte[] getBytes();
  
  byte[] getBytes(SessionInterface paramSessionInterface, long paramLong, int paramInt);
  
  BlobData getBlob(SessionInterface paramSessionInterface, long paramLong1, long paramLong2);
  
  InputStream getBinaryStream(SessionInterface paramSessionInterface);
  
  InputStream getBinaryStream(SessionInterface paramSessionInterface, long paramLong1, long paramLong2);
  
  long length(SessionInterface paramSessionInterface);
  
  long bitLength(SessionInterface paramSessionInterface);
  
  boolean isBits();
  
  void setBytes(SessionInterface paramSessionInterface, long paramLong, byte[] paramArrayOfbyte, int paramInt1, int paramInt2);
  
  void setBytes(SessionInterface paramSessionInterface, long paramLong, byte[] paramArrayOfbyte);
  
  void setBytes(SessionInterface paramSessionInterface, long paramLong1, BlobData paramBlobData, long paramLong2, long paramLong3);
  
  void setBinaryStream(SessionInterface paramSessionInterface, long paramLong, InputStream paramInputStream);
  
  void truncate(SessionInterface paramSessionInterface, long paramLong);
  
  BlobData duplicate(SessionInterface paramSessionInterface);
  
  long position(SessionInterface paramSessionInterface, byte[] paramArrayOfbyte, long paramLong);
  
  long position(SessionInterface paramSessionInterface, BlobData paramBlobData, long paramLong);
  
  long nonZeroLength(SessionInterface paramSessionInterface);
  
  long getId();
  
  void setId(long paramLong);
  
  void free();
  
  boolean isClosed();
  
  void setSession(SessionInterface paramSessionInterface);
  
  int getStreamBlockSize();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\types\BlobData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */