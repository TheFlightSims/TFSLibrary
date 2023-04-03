package org.hsqldb.lib;

import java.io.IOException;

public interface Storage {
  long length() throws IOException;
  
  void seek(long paramLong) throws IOException;
  
  long getFilePointer() throws IOException;
  
  int read() throws IOException;
  
  void read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
  
  void write(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
  
  int readInt() throws IOException;
  
  void writeInt(int paramInt) throws IOException;
  
  long readLong() throws IOException;
  
  void writeLong(long paramLong) throws IOException;
  
  void close() throws IOException;
  
  boolean isReadOnly();
  
  boolean wasNio();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\Storage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */