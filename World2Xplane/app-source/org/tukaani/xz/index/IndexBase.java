package org.tukaani.xz.index;

import org.tukaani.xz.XZIOException;
import org.tukaani.xz.common.Util;

abstract class IndexBase {
  private final XZIOException invalidIndexException;
  
  long blocksSum = 0L;
  
  long uncompressedSum = 0L;
  
  long indexListSize = 0L;
  
  long recordCount = 0L;
  
  IndexBase(XZIOException paramXZIOException) {
    this.invalidIndexException = paramXZIOException;
  }
  
  private long getUnpaddedIndexSize() {
    return (1 + Util.getVLISize(this.recordCount)) + this.indexListSize + 4L;
  }
  
  public long getIndexSize() {
    return getUnpaddedIndexSize() + 3L & 0xFFFFFFFFFFFFFFFCL;
  }
  
  public long getStreamSize() {
    return 12L + this.blocksSum + getIndexSize() + 12L;
  }
  
  int getIndexPaddingSize() {
    return (int)(4L - getUnpaddedIndexSize() & 0x3L);
  }
  
  void add(long paramLong1, long paramLong2) throws XZIOException {
    this.blocksSum += paramLong1 + 3L & 0xFFFFFFFFFFFFFFFCL;
    this.uncompressedSum += paramLong2;
    this.indexListSize += (Util.getVLISize(paramLong1) + Util.getVLISize(paramLong2));
    this.recordCount++;
    if (this.blocksSum < 0L || this.uncompressedSum < 0L || getIndexSize() > 17179869184L || getStreamSize() < 0L)
      throw this.invalidIndexException; 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\index\IndexBase.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */