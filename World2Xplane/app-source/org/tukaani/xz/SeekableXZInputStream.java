package org.tukaani.xz;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.tukaani.xz.check.Check;
import org.tukaani.xz.common.DecoderUtil;
import org.tukaani.xz.common.StreamFlags;
import org.tukaani.xz.index.BlockInfo;
import org.tukaani.xz.index.IndexDecoder;

public class SeekableXZInputStream extends SeekableInputStream {
  private SeekableInputStream in;
  
  private final int memoryLimit;
  
  private int indexMemoryUsage = 0;
  
  private final ArrayList streams = new ArrayList();
  
  private IndexDecoder index;
  
  private int checkTypes = 0;
  
  private Check check;
  
  private BlockInputStream blockDecoder = null;
  
  private long uncompressedSize = 0L;
  
  private long largestBlockSize = 0L;
  
  private long curPos = 0L;
  
  private long seekPos;
  
  private boolean seekNeeded = false;
  
  private boolean endReached = false;
  
  private IOException exception = null;
  
  static final boolean $assertionsDisabled;
  
  public SeekableXZInputStream(SeekableInputStream paramSeekableInputStream) throws IOException {
    this(paramSeekableInputStream, -1);
  }
  
  public SeekableXZInputStream(SeekableInputStream paramSeekableInputStream, int paramInt) throws IOException {
    this.in = paramSeekableInputStream;
    DataInputStream dataInputStream = new DataInputStream(paramSeekableInputStream);
    paramSeekableInputStream.seek(0L);
    byte[] arrayOfByte1 = new byte[XZ.HEADER_MAGIC.length];
    dataInputStream.readFully(arrayOfByte1);
    if (!Arrays.equals(arrayOfByte1, XZ.HEADER_MAGIC))
      throw new XZFormatException(); 
    long l1 = paramSeekableInputStream.length();
    if ((l1 & 0x3L) != 0L)
      throw new CorruptedInputException("XZ file size is not a multiple of 4 bytes"); 
    byte[] arrayOfByte2 = new byte[12];
    long l2;
    for (l2 = 0L; l1 > 0L; l2 = 0L) {
      if (l1 < 12L)
        throw new CorruptedInputException(); 
      paramSeekableInputStream.seek(l1 - 12L);
      dataInputStream.readFully(arrayOfByte2);
      if (arrayOfByte2[8] == 0 && arrayOfByte2[9] == 0 && arrayOfByte2[10] == 0 && arrayOfByte2[11] == 0) {
        l2 += 4L;
        l1 -= 4L;
        continue;
      } 
      l1 -= 12L;
      StreamFlags streamFlags1 = DecoderUtil.decodeStreamFooter(arrayOfByte2);
      if (streamFlags1.backwardSize >= l1)
        throw new CorruptedInputException("Backward Size in XZ Stream Footer is too big"); 
      this.check = Check.getInstance(streamFlags1.checkType);
      this.checkTypes |= 1 << streamFlags1.checkType;
      paramSeekableInputStream.seek(l1 - streamFlags1.backwardSize);
      try {
        this.index = new IndexDecoder(paramSeekableInputStream, streamFlags1, l2, paramInt);
      } catch (MemoryLimitException memoryLimitException) {
        assert paramInt >= 0;
        throw new MemoryLimitException(memoryLimitException.getMemoryNeeded() + this.indexMemoryUsage, paramInt + this.indexMemoryUsage);
      } 
      this.indexMemoryUsage += this.index.getMemoryUsage();
      if (paramInt >= 0) {
        paramInt -= this.index.getMemoryUsage();
        assert paramInt >= 0;
      } 
      if (this.largestBlockSize < this.index.getLargestBlockSize())
        this.largestBlockSize = this.index.getLargestBlockSize(); 
      long l = this.index.getStreamSize() - 12L;
      if (l1 < l)
        throw new CorruptedInputException("XZ Index indicates too big compressed size for the XZ Stream"); 
      l1 -= l;
      paramSeekableInputStream.seek(l1);
      dataInputStream.readFully(arrayOfByte2);
      StreamFlags streamFlags2 = DecoderUtil.decodeStreamHeader(arrayOfByte2);
      if (!DecoderUtil.areStreamFlagsEqual(streamFlags2, streamFlags1))
        throw new CorruptedInputException("XZ Stream Footer does not match Stream Header"); 
      this.uncompressedSize += this.index.getUncompressedSize();
      if (this.uncompressedSize < 0L)
        throw new UnsupportedOptionsException("XZ file is too big"); 
      this.streams.add(this.index);
    } 
    assert l1 == 0L;
    this.memoryLimit = paramInt;
  }
  
  public int getCheckTypes() {
    return this.checkTypes;
  }
  
  public int getIndexMemoryUsage() {
    return this.indexMemoryUsage;
  }
  
  public long getLargestBlockSize() {
    return this.largestBlockSize;
  }
  
  public int read() throws IOException {
    byte[] arrayOfByte = new byte[1];
    return (read(arrayOfByte, 0, 1) == -1) ? -1 : (arrayOfByte[0] & 0xFF);
  }
  
  public int read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    if (paramInt1 < 0 || paramInt2 < 0 || paramInt1 + paramInt2 < 0 || paramInt1 + paramInt2 > paramArrayOfbyte.length)
      throw new IndexOutOfBoundsException(); 
    if (paramInt2 == 0)
      return 0; 
    if (this.in == null)
      throw new XZIOException("Stream closed"); 
    if (this.exception != null)
      throw this.exception; 
    int i = 0;
    try {
      if (this.seekNeeded)
        seek(); 
      if (this.endReached)
        return -1; 
      while (paramInt2 > 0) {
        if (this.blockDecoder == null) {
          seek();
          if (this.endReached)
            break; 
        } 
        int j = this.blockDecoder.read(paramArrayOfbyte, paramInt1, paramInt2);
        if (j > 0) {
          this.curPos += j;
          i += j;
          paramInt1 += j;
          paramInt2 -= j;
          continue;
        } 
        if (j == -1)
          this.blockDecoder = null; 
      } 
    } catch (IOException iOException) {
      if (iOException instanceof java.io.EOFException)
        iOException = new CorruptedInputException(); 
      this.exception = iOException;
      if (i == 0)
        throw iOException; 
    } 
    return i;
  }
  
  public int available() throws IOException {
    if (this.in == null)
      throw new XZIOException("Stream closed"); 
    if (this.exception != null)
      throw this.exception; 
    return (this.endReached || this.seekNeeded || this.blockDecoder == null) ? 0 : this.blockDecoder.available();
  }
  
  public void close() throws IOException {
    if (this.in != null)
      try {
        this.in.close();
      } finally {
        this.in = null;
      }  
  }
  
  public long length() {
    return this.uncompressedSize;
  }
  
  public long position() throws IOException {
    if (this.in == null)
      throw new XZIOException("Stream closed"); 
    return this.seekNeeded ? this.seekPos : this.curPos;
  }
  
  public void seek(long paramLong) throws IOException {
    if (this.in == null)
      throw new XZIOException("Stream closed"); 
    if (paramLong < 0L)
      throw new XZIOException("Negative seek position: " + paramLong); 
    this.seekPos = paramLong;
    this.seekNeeded = true;
  }
  
  private void seek() throws IOException {
    if (!this.seekNeeded) {
      if (this.index.hasNext()) {
        BlockInfo blockInfo = this.index.getNext();
        initBlockDecoder(blockInfo);
        return;
      } 
      this.seekPos = this.curPos;
    } 
    this.seekNeeded = false;
    if (this.seekPos >= this.uncompressedSize) {
      this.curPos = this.seekPos;
      this.blockDecoder = null;
      this.endReached = true;
      return;
    } 
    this.endReached = false;
    int i = this.streams.size();
    assert i >= 1;
    long l1 = 0L;
    long l2 = 0L;
    while (true) {
      this.index = this.streams.get(--i);
      if (l1 + this.index.getUncompressedSize() > this.seekPos) {
        BlockInfo blockInfo = this.index.locate(this.seekPos - l1);
        assert (blockInfo.compressedOffset & 0x3L) == 0L : blockInfo.compressedOffset;
        blockInfo.compressedOffset += l2;
        blockInfo.uncompressedOffset += l1;
        assert this.seekPos >= blockInfo.uncompressedOffset;
        assert this.seekPos < blockInfo.uncompressedOffset + blockInfo.uncompressedSize;
        if (this.curPos <= blockInfo.uncompressedOffset || this.curPos > this.seekPos) {
          this.in.seek(blockInfo.compressedOffset);
          this.check = Check.getInstance(blockInfo.streamFlags.checkType);
          initBlockDecoder(blockInfo);
          this.curPos = blockInfo.uncompressedOffset;
        } 
        if (this.seekPos > this.curPos) {
          long l = this.seekPos - this.curPos;
          if (this.blockDecoder.skip(l) != l)
            throw new CorruptedInputException(); 
        } 
        this.curPos = this.seekPos;
        return;
      } 
      l1 += this.index.getUncompressedSize();
      l2 += this.index.getStreamAndPaddingSize();
      assert (l2 & 0x3L) == 0L;
    } 
  }
  
  private void initBlockDecoder(BlockInfo paramBlockInfo) throws IOException {
    try {
      this.blockDecoder = null;
      this.blockDecoder = new BlockInputStream(this.in, this.check, this.memoryLimit, paramBlockInfo.unpaddedSize, paramBlockInfo.uncompressedSize);
    } catch (MemoryLimitException memoryLimitException) {
      assert this.memoryLimit >= 0;
      throw new MemoryLimitException(memoryLimitException.getMemoryNeeded() + this.indexMemoryUsage, this.memoryLimit + this.indexMemoryUsage);
    } catch (IndexIndicatorException indexIndicatorException) {
      throw new CorruptedInputException();
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\SeekableXZInputStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */