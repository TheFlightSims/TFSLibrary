package org.hsqldb.persist;

import org.hsqldb.lib.DoubleIntIndex;

public interface DataSpaceManager {
  public static final int tableIdEmpty = 0;
  
  public static final int tableIdDirectory = 1;
  
  public static final int tableIdDefault = 7;
  
  public static final int tableIdFirst = 8;
  
  public static final int fixedBlockSizeUnit = 4096;
  
  TableSpaceManager getDefaultTableSpace();
  
  TableSpaceManager getTableSpace(int paramInt);
  
  int getNewTableSpaceID();
  
  long getFileBlocks(int paramInt1, int paramInt2);
  
  void freeTableSpace(int paramInt);
  
  void freeTableSpace(DoubleIntIndex paramDoubleIntIndex, long paramLong1, long paramLong2, boolean paramBoolean);
  
  long getLostBlocksSize();
  
  boolean isModified();
  
  int getFileBlockSize();
  
  void initialiseSpaces();
  
  void reset();
  
  boolean isMultiSpace();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\DataSpaceManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */