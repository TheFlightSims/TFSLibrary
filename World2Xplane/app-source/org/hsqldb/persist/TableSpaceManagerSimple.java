package org.hsqldb.persist;

import org.hsqldb.error.Error;
import org.hsqldb.lib.DoubleIntIndex;

public class TableSpaceManagerSimple implements TableSpaceManager {
  DataFileCache cache;
  
  final int scale;
  
  public TableSpaceManagerSimple(DataFileCache paramDataFileCache) {
    this.cache = paramDataFileCache;
    this.scale = paramDataFileCache.getDataFileScale();
  }
  
  public int getSpaceID() {
    return 7;
  }
  
  public void release(long paramLong, int paramInt) {}
  
  public long getFilePosition(long paramLong, boolean paramBoolean) {
    this.cache.writeLock.lock();
    try {
      long l1 = this.cache.getFileFreePos() / this.scale;
      long l2 = this.cache.getFileFreePos() + paramLong;
      if (l2 > this.cache.maxDataFileSize) {
        this.cache.logSevereEvent("data file reached maximum size " + this.cache.dataFileName, null);
        throw Error.error(468);
      } 
      this.cache.fileFreePosition = l2;
      return l1;
    } finally {
      this.cache.writeLock.unlock();
    } 
  }
  
  public boolean hasFileRoom(long paramLong) {
    return true;
  }
  
  public void addFileBlock(long paramLong1, long paramLong2) {}
  
  public void initialiseFileBlock(DoubleIntIndex paramDoubleIntIndex, long paramLong1, long paramLong2) {}
  
  public void reset() {}
  
  public long getLostBlocksSize() {
    return 0L;
  }
  
  public boolean isDefaultSpace() {
    return true;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\TableSpaceManagerSimple.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */