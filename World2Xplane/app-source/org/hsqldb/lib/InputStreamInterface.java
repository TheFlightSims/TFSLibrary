package org.hsqldb.lib;

import java.io.IOException;

public interface InputStreamInterface {
  int read() throws IOException;
  
  int read(byte[] paramArrayOfbyte) throws IOException;
  
  int read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
  
  long skip(long paramLong) throws IOException;
  
  int available() throws IOException;
  
  void close() throws IOException;
  
  void setSizeLimit(long paramLong);
  
  long getSizeLimit();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\InputStreamInterface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */