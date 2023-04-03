package org.hsqldb.rowio;

import java.io.IOException;
import org.hsqldb.types.Type;

public interface RowInputInterface {
  long getPos();
  
  int getSize();
  
  int readType() throws IOException;
  
  String readString() throws IOException;
  
  byte readByte() throws IOException;
  
  char readChar() throws IOException;
  
  short readShort() throws IOException;
  
  int readInt() throws IOException;
  
  long readLong() throws IOException;
  
  Object[] readData(Type[] paramArrayOfType) throws IOException;
  
  void resetRow(long paramLong, int paramInt);
  
  void resetBlock(long paramLong, int paramInt);
  
  byte[] getBuffer();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\rowio\RowInputInterface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */