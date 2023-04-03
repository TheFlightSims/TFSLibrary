package org.hsqldb.persist;

import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.DoubleIntIndex;

public class TableSpaceManagerBlocks implements TableSpaceManager {
  DataSpaceManager spaceManager;
  
  private final int scale;
  
  int mainBlockSize;
  
  int spaceID;
  
  private DoubleIntIndex lookup;
  
  private final int capacity;
  
  private long releaseCount;
  
  private long requestCount;
  
  private long requestSize;
  
  boolean isModified;
  
  long freshBlockFreePos = 0L;
  
  long freshBlockLimit = 0L;
  
  public TableSpaceManagerBlocks(DataSpaceManager paramDataSpaceManager, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    this.spaceManager = paramDataSpaceManager;
    this.scale = paramInt4;
    this.spaceID = paramInt1;
    this.mainBlockSize = paramInt2;
    this.lookup = new DoubleIntIndex(paramInt3, true);
    this.lookup.setValuesSearchTarget();
    this.capacity = paramInt3;
  }
  
  public boolean hasFileRoom(long paramLong) {
    return (this.freshBlockLimit - this.freshBlockFreePos > paramLong);
  }
  
  public void addFileBlock(long paramLong1, long paramLong2) {
    int i = (int)(this.freshBlockLimit - this.freshBlockFreePos);
    if (i > 0)
      release(this.freshBlockFreePos / this.scale, i); 
    initialiseFileBlock(null, paramLong1, paramLong2);
  }
  
  public void initialiseFileBlock(DoubleIntIndex paramDoubleIntIndex, long paramLong1, long paramLong2) {
    this.freshBlockFreePos = paramLong1;
    this.freshBlockLimit = paramLong2;
    if (paramDoubleIntIndex != null)
      paramDoubleIntIndex.copyTo(this.lookup); 
  }
  
  boolean getNewMainBlock(long paramLong) {
    long l1 = (this.mainBlockSize + paramLong) / this.mainBlockSize;
    long l2 = l1 * this.mainBlockSize;
    long l3 = this.spaceManager.getFileBlocks(this.spaceID, (int)l1);
    if (l3 < 0L)
      return false; 
    if (l3 == this.freshBlockLimit) {
      this.freshBlockLimit += l2;
      return true;
    } 
    long l4 = this.freshBlockLimit - this.freshBlockFreePos;
    if (l4 > 0L)
      release(this.freshBlockFreePos / this.scale, (int)l4); 
    this.freshBlockFreePos = l3;
    this.freshBlockLimit = l3 + l2;
    return true;
  }
  
  long getNewBlock(long paramLong, boolean paramBoolean) {
    if (paramBoolean)
      paramLong = (int)ArrayUtil.getBinaryMultipleCeiling(paramLong, 4096L); 
    if (this.freshBlockFreePos + paramLong > this.freshBlockLimit) {
      boolean bool = getNewMainBlock(paramLong);
      if (!bool)
        throw Error.error(468); 
    } 
    long l = this.freshBlockFreePos;
    if (paramBoolean) {
      l = ArrayUtil.getBinaryMultipleCeiling(l, 4096L);
      long l1 = l - this.freshBlockFreePos;
      if (l1 > 0L) {
        release(this.freshBlockFreePos / this.scale, (int)l1);
        this.freshBlockFreePos = l;
      } 
    } 
    this.freshBlockFreePos += paramLong;
    return l / this.scale;
  }
  
  public int getSpaceID() {
    return this.spaceID;
  }
  
  public synchronized void release(long paramLong, int paramInt) {
    this.isModified = true;
    this.releaseCount++;
    if (this.lookup.size() == this.capacity)
      resetList(); 
    if (paramLong < 2147483647L)
      this.lookup.add(paramLong, paramInt); 
  }
  
  public synchronized long getFilePosition(long paramLong, boolean paramBoolean) {
    if (this.capacity == 0)
      return getNewBlock(paramLong, paramBoolean); 
    if (paramBoolean)
      paramLong = (int)ArrayUtil.getBinaryMultipleCeiling(paramLong, 4096L); 
    int i = -1;
    if (this.lookup.size() > 0)
      if (this.lookup.getValue(0) >= paramLong) {
        i = 0;
      } else if (paramLong > 2147483647L) {
        i = -1;
      } else {
        i = this.lookup.findFirstGreaterEqualKeyIndex((int)paramLong);
      }  
    if (i == -1)
      return getNewBlock(paramLong, paramBoolean); 
    if (paramBoolean) {
      while (i < this.lookup.size()) {
        long l = this.lookup.getKey(i);
        if (l % (4096 / this.scale) == 0L)
          break; 
        i++;
      } 
      if (i == this.lookup.size())
        return getNewBlock(paramLong, paramBoolean); 
    } 
    this.requestCount++;
    this.requestSize += paramLong;
    int j = this.lookup.getValue(i);
    int k = j - (int)paramLong;
    int m = this.lookup.getKey(i);
    this.lookup.remove(i);
    if (k > 0) {
      long l = m + paramLong / this.scale;
      this.lookup.add(l, k);
    } 
    return m;
  }
  
  public void reset() {
    this.spaceManager.freeTableSpace(this.lookup, this.freshBlockFreePos, this.freshBlockLimit, true);
    this.freshBlockFreePos = 0L;
    this.freshBlockLimit = 0L;
  }
  
  public long getLostBlocksSize() {
    return this.lookup.getTotalValues();
  }
  
  public boolean isDefaultSpace() {
    return (this.spaceID == 7);
  }
  
  private void resetList() {
    this.spaceManager.freeTableSpace(this.lookup, this.freshBlockFreePos, this.freshBlockFreePos, false);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\TableSpaceManagerBlocks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */