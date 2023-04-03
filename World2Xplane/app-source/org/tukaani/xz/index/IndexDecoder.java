package org.tukaani.xz.index;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import org.tukaani.xz.CorruptedInputException;
import org.tukaani.xz.MemoryLimitException;
import org.tukaani.xz.SeekableInputStream;
import org.tukaani.xz.UnsupportedOptionsException;
import org.tukaani.xz.XZIOException;
import org.tukaani.xz.common.DecoderUtil;
import org.tukaani.xz.common.StreamFlags;

public class IndexDecoder extends IndexBase {
  private final BlockInfo info = new BlockInfo();
  
  private final long streamPadding;
  
  private final int memoryUsage;
  
  private final long[] unpadded;
  
  private final long[] uncompressed;
  
  private long largestBlockSize = 0L;
  
  private int pos = -1;
  
  static final boolean $assertionsDisabled;
  
  public IndexDecoder(SeekableInputStream paramSeekableInputStream, StreamFlags paramStreamFlags, long paramLong, int paramInt) throws IOException {
    super((XZIOException)new CorruptedInputException("XZ Index is corrupt"));
    this.info.streamFlags = paramStreamFlags;
    this.streamPadding = paramLong;
    long l1 = paramSeekableInputStream.position() + paramStreamFlags.backwardSize - 4L;
    CRC32 cRC32 = new CRC32();
    CheckedInputStream checkedInputStream = new CheckedInputStream((InputStream)paramSeekableInputStream, cRC32);
    if (checkedInputStream.read() != 0)
      throw new CorruptedInputException("XZ Index is corrupt"); 
    try {
      long l = DecoderUtil.decodeVLI(checkedInputStream);
      if (l >= paramStreamFlags.backwardSize / 2L)
        throw new CorruptedInputException("XZ Index is corrupt"); 
      if (l > 2147483647L)
        throw new UnsupportedOptionsException("XZ Index has over 2147483647 Records"); 
      this.memoryUsage = 1 + (int)((16L * l + 1023L) / 1024L);
      if (paramInt >= 0 && this.memoryUsage > paramInt)
        throw new MemoryLimitException(this.memoryUsage, paramInt); 
      this.unpadded = new long[(int)l];
      this.uncompressed = new long[(int)l];
      byte b1 = 0;
      for (int j = (int)l; j > 0; j--) {
        long l3 = DecoderUtil.decodeVLI(checkedInputStream);
        long l4 = DecoderUtil.decodeVLI(checkedInputStream);
        if (paramSeekableInputStream.position() > l1)
          throw new CorruptedInputException("XZ Index is corrupt"); 
        this.unpadded[b1] = this.blocksSum + l3;
        this.uncompressed[b1] = this.uncompressedSum + l4;
        b1++;
        add(l3, l4);
        assert b1 == this.recordCount;
        if (this.largestBlockSize < l4)
          this.largestBlockSize = l4; 
      } 
    } catch (EOFException eOFException) {
      throw new CorruptedInputException("XZ Index is corrupt");
    } 
    int i = getIndexPaddingSize();
    if (paramSeekableInputStream.position() + i != l1)
      throw new CorruptedInputException("XZ Index is corrupt"); 
    while (i-- > 0) {
      if (checkedInputStream.read() != 0)
        throw new CorruptedInputException("XZ Index is corrupt"); 
    } 
    long l2 = cRC32.getValue();
    for (byte b = 0; b < 4; b++) {
      if ((l2 >>> b * 8 & 0xFFL) != paramSeekableInputStream.read())
        throw new CorruptedInputException("XZ Index is corrupt"); 
    } 
  }
  
  public BlockInfo locate(long paramLong) {
    assert paramLong < this.uncompressedSum;
    int i = 0;
    int j;
    for (j = this.unpadded.length - 1; i < j; j = k) {
      int k = i + (j - i) / 2;
      if (this.uncompressed[k] <= paramLong) {
        i = k + 1;
        continue;
      } 
    } 
    this.pos = i;
    return getInfo();
  }
  
  public int getMemoryUsage() {
    return this.memoryUsage;
  }
  
  public long getStreamAndPaddingSize() {
    return getStreamSize() + this.streamPadding;
  }
  
  public long getUncompressedSize() {
    return this.uncompressedSum;
  }
  
  public long getLargestBlockSize() {
    return this.largestBlockSize;
  }
  
  public boolean hasNext() {
    return ((this.pos + 1) < this.recordCount);
  }
  
  public BlockInfo getNext() {
    this.pos++;
    return getInfo();
  }
  
  private BlockInfo getInfo() {
    if (this.pos == 0) {
      this.info.compressedOffset = 0L;
      this.info.uncompressedOffset = 0L;
    } else {
      this.info.compressedOffset = this.unpadded[this.pos - 1] + 3L & 0xFFFFFFFFFFFFFFFCL;
      this.info.uncompressedOffset = this.uncompressed[this.pos - 1];
    } 
    this.info.unpaddedSize = this.unpadded[this.pos] - this.info.compressedOffset;
    this.info.uncompressedSize = this.uncompressed[this.pos] - this.info.uncompressedOffset;
    this.info.compressedOffset += 12L;
    return this.info;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\index\IndexDecoder.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */