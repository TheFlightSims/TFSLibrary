package org.hsqldb.persist;

import org.hsqldb.lib.DoubleIntIndex;

public interface TableSpaceManager {
  int getSpaceID();
  
  void release(long paramLong, int paramInt);
  
  long getFilePosition(long paramLong, boolean paramBoolean);
  
  boolean hasFileRoom(long paramLong);
  
  void addFileBlock(long paramLong1, long paramLong2);
  
  void initialiseFileBlock(DoubleIntIndex paramDoubleIntIndex, long paramLong1, long paramLong2);
  
  void reset();
  
  long getLostBlocksSize();
  
  boolean isDefaultSpace();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\TableSpaceManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */