package org.hsqldb.rowio;

import org.hsqldb.Row;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.HsqlByteArrayOutputStream;
import org.hsqldb.types.Type;

public interface RowOutputInterface extends Cloneable {
  void writeEnd();
  
  void writeSize(int paramInt);
  
  void writeType(int paramInt);
  
  void writeString(String paramString);
  
  void writeByte(int paramInt);
  
  void writeChar(int paramInt);
  
  void writeShort(int paramInt);
  
  void writeInt(int paramInt);
  
  void writeIntData(int paramInt1, int paramInt2);
  
  void writeLong(long paramLong);
  
  void writeData(Row paramRow, Type[] paramArrayOfType);
  
  void writeData(int paramInt, Type[] paramArrayOfType, Object[] paramArrayOfObject, HashMappedList paramHashMappedList, int[] paramArrayOfint);
  
  int getSize(Row paramRow);
  
  int getStorageSize(int paramInt);
  
  HsqlByteArrayOutputStream getOutputStream();
  
  byte[] getBuffer();
  
  void reset();
  
  void reset(int paramInt);
  
  void reset(byte[] paramArrayOfbyte);
  
  int size();
  
  RowOutputInterface duplicate();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\rowio\RowOutputInterface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */