package org.hsqldb.persist;

import org.hsqldb.lib.DoubleIntIndex;

public class DataSpaceManagerSimple implements DataSpaceManager {
  DataFileCache cache;
  
  TableSpaceManager defaultSpaceManager;
  
  int fileBlockSize = 4096;
  
  long totalFragmentSize;
  
  int spaceIdSequence = 8;
  
  DoubleIntIndex lookup;
  
  DataSpaceManagerSimple(DataFileCache paramDataFileCache) {
    this.cache = paramDataFileCache;
    if (paramDataFileCache instanceof DataFileCacheSession) {
      this.defaultSpaceManager = new TableSpaceManagerSimple(paramDataFileCache);
    } else if (paramDataFileCache instanceof TextCache) {
      this.defaultSpaceManager = new TableSpaceManagerSimple(paramDataFileCache);
    } else {
      int i = paramDataFileCache.database.logger.propMaxFreeBlocks;
      this.defaultSpaceManager = new TableSpaceManagerBlocks(this, 7, this.fileBlockSize, i, paramDataFileCache.dataFileScale);
      initialiseSpaces();
      paramDataFileCache.spaceManagerPosition = 0L;
    } 
  }
  
  public TableSpaceManager getDefaultTableSpace() {
    return this.defaultSpaceManager;
  }
  
  public TableSpaceManager getTableSpace(int paramInt) {
    if (paramInt >= this.spaceIdSequence)
      this.spaceIdSequence = paramInt + 1; 
    return this.defaultSpaceManager;
  }
  
  public int getNewTableSpaceID() {
    return this.spaceIdSequence++;
  }
  
  public long getFileBlocks(int paramInt1, int paramInt2) {
    return this.cache.enlargeFileSpace(paramInt2 * this.fileBlockSize);
  }
  
  public void freeTableSpace(int paramInt) {}
  
  public void freeTableSpace(DoubleIntIndex paramDoubleIntIndex, long paramLong1, long paramLong2, boolean paramBoolean) {
    this.totalFragmentSize += paramDoubleIntIndex.getTotalValues();
    if (paramBoolean) {
      if (this.cache.fileFreePosition == paramLong2) {
        this.cache.writeLock.lock();
        try {
          this.cache.fileFreePosition = paramLong1;
        } finally {
          this.cache.writeLock.unlock();
        } 
      } else {
        this.totalFragmentSize += paramLong2 - paramLong1;
      } 
      if (paramDoubleIntIndex.size() != 0) {
        this.lookup = new DoubleIntIndex(paramDoubleIntIndex.size(), true);
        paramDoubleIntIndex.copyTo(this.lookup);
      } 
    } else {
      compactLookup(paramDoubleIntIndex, this.cache.dataFileScale);
      paramDoubleIntIndex.setValuesSearchTarget();
      paramDoubleIntIndex.sort();
      int i = paramDoubleIntIndex.size() - paramDoubleIntIndex.capacity() / 2;
      if (i > 0) {
        paramDoubleIntIndex.removeRange(0, i);
        this.totalFragmentSize -= paramDoubleIntIndex.getTotalValues();
      } 
    } 
  }
  
  public long getLostBlocksSize() {
    return this.totalFragmentSize + this.defaultSpaceManager.getLostBlocksSize();
  }
  
  public int getFileBlockSize() {
    return Integer.MAX_VALUE;
  }
  
  public boolean isModified() {
    return true;
  }
  
  public void initialiseSpaces() {
    long l1 = this.cache.getFileFreePos();
    long l2 = (l1 + this.fileBlockSize) / this.fileBlockSize;
    long l3 = this.cache.enlargeFileSpace(l2 * this.fileBlockSize - l1);
    this.defaultSpaceManager.initialiseFileBlock(this.lookup, l3, this.cache.getFileFreePos());
    if (this.lookup != null) {
      this.totalFragmentSize -= this.lookup.getTotalValues();
      this.lookup = null;
    } 
  }
  
  public void reset() {
    this.defaultSpaceManager.reset();
  }
  
  public boolean isMultiSpace() {
    return false;
  }
  
  static boolean compactLookup(DoubleIntIndex paramDoubleIntIndex, int paramInt) {
    paramDoubleIntIndex.setKeysSearchTarget();
    paramDoubleIntIndex.sort();
    if (paramDoubleIntIndex.size() == 0)
      return false; 
    int[] arrayOfInt1 = paramDoubleIntIndex.getKeys();
    int[] arrayOfInt2 = paramDoubleIntIndex.getValues();
    byte b = 0;
    int i;
    for (i = 1; i < paramDoubleIntIndex.size(); i++) {
      int j = arrayOfInt1[b];
      int k = arrayOfInt2[b];
      if (j + k / paramInt == arrayOfInt1[i]) {
        arrayOfInt2[b] = arrayOfInt2[b] + arrayOfInt2[i];
      } else if (++b != i) {
        arrayOfInt1[b] = arrayOfInt1[i];
        arrayOfInt2[b] = arrayOfInt2[i];
      } 
    } 
    for (i = b + 1; i < paramDoubleIntIndex.size(); i++) {
      arrayOfInt1[i] = 0;
      arrayOfInt2[i] = 0;
    } 
    if (paramDoubleIntIndex.size() != b + 1) {
      paramDoubleIntIndex.setSize(b + 1);
      return true;
    } 
    return false;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\DataSpaceManagerSimple.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */