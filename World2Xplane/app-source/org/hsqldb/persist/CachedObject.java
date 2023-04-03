package org.hsqldb.persist;

import org.hsqldb.lib.LongLookup;
import org.hsqldb.rowio.RowInputInterface;
import org.hsqldb.rowio.RowOutputInterface;

public interface CachedObject {
  public static final CachedObject[] emptyArray = new CachedObject[0];
  
  boolean isMemory();
  
  void updateAccessCount(int paramInt);
  
  int getAccessCount();
  
  void setStorageSize(int paramInt);
  
  int getStorageSize();
  
  boolean isBlock();
  
  long getPos();
  
  void setPos(long paramLong);
  
  boolean isNew();
  
  boolean hasChanged();
  
  boolean isKeepInMemory();
  
  boolean keepInMemory(boolean paramBoolean);
  
  boolean isInMemory();
  
  void setInMemory(boolean paramBoolean);
  
  void restore();
  
  void destroy();
  
  int getRealSize(RowOutputInterface paramRowOutputInterface);
  
  void read(RowInputInterface paramRowInputInterface);
  
  int getDefaultCapacity();
  
  void write(RowOutputInterface paramRowOutputInterface);
  
  void write(RowOutputInterface paramRowOutputInterface, LongLookup paramLongLookup);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\CachedObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */