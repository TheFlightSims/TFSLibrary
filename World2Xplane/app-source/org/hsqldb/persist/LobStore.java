package org.hsqldb.persist;

public interface LobStore {
  byte[] getBlockBytes(int paramInt1, int paramInt2);
  
  void setBlockBytes(byte[] paramArrayOfbyte, int paramInt1, int paramInt2);
  
  void setBlockBytes(byte[] paramArrayOfbyte, long paramLong, int paramInt1, int paramInt2);
  
  int getBlockSize();
  
  void setLength(long paramLong);
  
  long getLength();
  
  void close();
  
  void synch();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\LobStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */