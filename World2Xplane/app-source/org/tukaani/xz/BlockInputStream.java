package org.tukaani.xz;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.tukaani.xz.check.Check;
import org.tukaani.xz.common.DecoderUtil;

class BlockInputStream extends InputStream {
  private final InputStream in;
  
  private final DataInputStream inData;
  
  private final CountingInputStream inCounted;
  
  private InputStream filterChain;
  
  private final Check check;
  
  private long uncompressedSizeInHeader = -1L;
  
  private long compressedSizeInHeader = -1L;
  
  private long compressedSizeLimit;
  
  private final int headerSize;
  
  private long uncompressedSize = 0L;
  
  private boolean endReached = false;
  
  public BlockInputStream(InputStream paramInputStream, Check paramCheck, int paramInt, long paramLong1, long paramLong2) throws IOException, IndexIndicatorException {
    this.in = paramInputStream;
    this.check = paramCheck;
    this.inData = new DataInputStream(paramInputStream);
    byte[] arrayOfByte = new byte[1024];
    this.inData.readFully(arrayOfByte, 0, 1);
    if (arrayOfByte[0] == 0)
      throw new IndexIndicatorException(); 
    this.headerSize = 4 * ((arrayOfByte[0] & 0xFF) + 1);
    this.inData.readFully(arrayOfByte, 1, this.headerSize - 1);
    if (!DecoderUtil.isCRC32Valid(arrayOfByte, 0, this.headerSize - 4, this.headerSize - 4))
      throw new CorruptedInputException("XZ Block Header is corrupt"); 
    if ((arrayOfByte[1] & 0x3C) != 0)
      throw new UnsupportedOptionsException("Unsupported options in XZ Block Header"); 
    int i = (arrayOfByte[1] & 0x3) + 1;
    long[] arrayOfLong = new long[i];
    byte[][] arrayOfByte1 = new byte[i][];
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(arrayOfByte, 2, this.headerSize - 6);
    try {
      this.compressedSizeLimit = 9223372036854775804L - this.headerSize - paramCheck.getSize();
      if ((arrayOfByte[1] & 0x40) != 0) {
        this.compressedSizeInHeader = DecoderUtil.decodeVLI(byteArrayInputStream);
        if (this.compressedSizeInHeader == 0L || this.compressedSizeInHeader > this.compressedSizeLimit)
          throw new CorruptedInputException(); 
        this.compressedSizeLimit = this.compressedSizeInHeader;
      } 
      if ((arrayOfByte[1] & 0x80) != 0)
        this.uncompressedSizeInHeader = DecoderUtil.decodeVLI(byteArrayInputStream); 
      for (byte b = 0; b < i; b++) {
        arrayOfLong[b] = DecoderUtil.decodeVLI(byteArrayInputStream);
        long l = DecoderUtil.decodeVLI(byteArrayInputStream);
        if (l > byteArrayInputStream.available())
          throw new CorruptedInputException(); 
        arrayOfByte1[b] = new byte[(int)l];
        byteArrayInputStream.read(arrayOfByte1[b]);
      } 
    } catch (IOException iOException) {
      throw new CorruptedInputException("XZ Block Header is corrupt");
    } 
    int j;
    for (j = byteArrayInputStream.available(); j > 0; j--) {
      if (byteArrayInputStream.read() != 0)
        throw new UnsupportedOptionsException("Unsupported options in XZ Block Header"); 
    } 
    if (paramLong1 != -1L) {
      j = this.headerSize + paramCheck.getSize();
      if (j >= paramLong1)
        throw new CorruptedInputException("XZ Index does not match a Block Header"); 
      long l = paramLong1 - j;
      if (l > this.compressedSizeLimit || (this.compressedSizeInHeader != -1L && this.compressedSizeInHeader != l))
        throw new CorruptedInputException("XZ Index does not match a Block Header"); 
      if (this.uncompressedSizeInHeader != -1L && this.uncompressedSizeInHeader != paramLong2)
        throw new CorruptedInputException("XZ Index does not match a Block Header"); 
      this.compressedSizeLimit = l;
      this.compressedSizeInHeader = l;
      this.uncompressedSizeInHeader = paramLong2;
    } 
    FilterDecoder[] arrayOfFilterDecoder = new FilterDecoder[arrayOfLong.length];
    int k;
    for (k = 0; k < arrayOfFilterDecoder.length; k++) {
      if (arrayOfLong[k] == 33L) {
        arrayOfFilterDecoder[k] = new LZMA2Decoder(arrayOfByte1[k]);
      } else if (arrayOfLong[k] == 3L) {
        arrayOfFilterDecoder[k] = new DeltaDecoder(arrayOfByte1[k]);
      } else if (BCJDecoder.isBCJFilterID(arrayOfLong[k])) {
        arrayOfFilterDecoder[k] = new BCJDecoder(arrayOfLong[k], arrayOfByte1[k]);
      } else {
        throw new UnsupportedOptionsException("Unknown Filter ID " + arrayOfLong[k]);
      } 
    } 
    RawCoder.validate((FilterCoder[])arrayOfFilterDecoder);
    if (paramInt >= 0) {
      k = 0;
      for (byte b = 0; b < arrayOfFilterDecoder.length; b++)
        k += arrayOfFilterDecoder[b].getMemoryUsage(); 
      if (k > paramInt)
        throw new MemoryLimitException(k, paramInt); 
    } 
    this.inCounted = new CountingInputStream(paramInputStream);
    this.filterChain = this.inCounted;
    for (k = arrayOfFilterDecoder.length - 1; k >= 0; k--)
      this.filterChain = arrayOfFilterDecoder[k].getInputStream(this.filterChain); 
  }
  
  public int read() throws IOException {
    byte[] arrayOfByte = new byte[1];
    return (read(arrayOfByte, 0, 1) == -1) ? -1 : (arrayOfByte[0] & 0xFF);
  }
  
  public int read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    if (this.endReached)
      return -1; 
    int i = this.filterChain.read(paramArrayOfbyte, paramInt1, paramInt2);
    if (i > 0) {
      this.check.update(paramArrayOfbyte, paramInt1, i);
      this.uncompressedSize += i;
      long l = this.inCounted.getSize();
      if (l < 0L || l > this.compressedSizeLimit || this.uncompressedSize < 0L || (this.uncompressedSizeInHeader != -1L && this.uncompressedSize > this.uncompressedSizeInHeader))
        throw new CorruptedInputException(); 
      if (i < paramInt2 || this.uncompressedSize == this.uncompressedSizeInHeader) {
        if (this.filterChain.read() != -1)
          throw new CorruptedInputException(); 
        validate();
        this.endReached = true;
      } 
    } else if (i == -1) {
      validate();
      this.endReached = true;
    } 
    return i;
  }
  
  private void validate() throws IOException {
    long l = this.inCounted.getSize();
    if ((this.compressedSizeInHeader != -1L && this.compressedSizeInHeader != l) || (this.uncompressedSizeInHeader != -1L && this.uncompressedSizeInHeader != this.uncompressedSize))
      throw new CorruptedInputException(); 
    while ((l++ & 0x3L) != 0L) {
      if (this.inData.readUnsignedByte() != 0)
        throw new CorruptedInputException(); 
    } 
    byte[] arrayOfByte = new byte[this.check.getSize()];
    this.inData.readFully(arrayOfByte);
    if (!Arrays.equals(this.check.finish(), arrayOfByte))
      throw new CorruptedInputException("Integrity check (" + this.check.getName() + ") does not match"); 
  }
  
  public int available() throws IOException {
    return this.filterChain.available();
  }
  
  public long getUnpaddedSize() {
    return this.headerSize + this.inCounted.getSize() + this.check.getSize();
  }
  
  public long getUncompressedSize() {
    return this.uncompressedSize;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\BlockInputStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */