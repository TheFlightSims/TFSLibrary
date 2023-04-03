package org.tukaani.xz;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.tukaani.xz.lz.LZDecoder;
import org.tukaani.xz.lzma.LZMADecoder;
import org.tukaani.xz.rangecoder.RangeDecoder;

public class LZMA2InputStream extends InputStream {
  public static final int DICT_SIZE_MIN = 4096;
  
  public static final int DICT_SIZE_MAX = 2147483632;
  
  private static final int COMPRESSED_SIZE_MAX = 65536;
  
  private DataInputStream in;
  
  private final LZDecoder lz;
  
  private final RangeDecoder rc = new RangeDecoder(65536);
  
  private LZMADecoder lzma;
  
  private int uncompressedSize = 0;
  
  private boolean isLZMAChunk;
  
  private boolean needDictReset = true;
  
  private boolean needProps = true;
  
  private boolean endReached = false;
  
  private IOException exception = null;
  
  public static int getMemoryUsage(int paramInt) {
    return 104 + getDictSize(paramInt) / 1024;
  }
  
  private static int getDictSize(int paramInt) {
    if (paramInt < 4096 || paramInt > 2147483632)
      throw new IllegalArgumentException("Unsupported dictionary size " + paramInt); 
    return paramInt + 15 & 0xFFFFFFF0;
  }
  
  public LZMA2InputStream(InputStream paramInputStream, int paramInt) {
    this(paramInputStream, paramInt, null);
  }
  
  public LZMA2InputStream(InputStream paramInputStream, int paramInt, byte[] paramArrayOfbyte) {
    if (paramInputStream == null)
      throw new NullPointerException(); 
    this.in = new DataInputStream(paramInputStream);
    this.lz = new LZDecoder(getDictSize(paramInt), paramArrayOfbyte);
    if (paramArrayOfbyte != null && paramArrayOfbyte.length > 0)
      this.needDictReset = false; 
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
    if (this.endReached)
      return -1; 
    try {
      int i = 0;
      while (paramInt2 > 0) {
        if (this.uncompressedSize == 0) {
          decodeChunkHeader();
          if (this.endReached)
            return !i ? -1 : i; 
        } 
        int j = Math.min(this.uncompressedSize, paramInt2);
        if (!this.isLZMAChunk) {
          this.lz.copyUncompressed(this.in, j);
        } else {
          this.lz.setLimit(j);
          this.lzma.decode();
        } 
        int k = this.lz.flush(paramArrayOfbyte, paramInt1);
        paramInt1 += k;
        paramInt2 -= k;
        i += k;
        this.uncompressedSize -= k;
        if (this.uncompressedSize == 0 && (!this.rc.isFinished() || this.lz.hasPending()))
          throw new CorruptedInputException(); 
      } 
      return i;
    } catch (IOException iOException) {
      this.exception = iOException;
      throw iOException;
    } 
  }
  
  private void decodeChunkHeader() throws IOException {
    int i = this.in.readUnsignedByte();
    if (i == 0) {
      this.endReached = true;
      return;
    } 
    if (i >= 224 || i == 1) {
      this.needProps = true;
      this.needDictReset = false;
      this.lz.reset();
    } else if (this.needDictReset) {
      throw new CorruptedInputException();
    } 
    if (i >= 128) {
      this.isLZMAChunk = true;
      this.uncompressedSize = (i & 0x1F) << 16;
      this.uncompressedSize += this.in.readUnsignedShort() + 1;
      int j = this.in.readUnsignedShort() + 1;
      if (i >= 192) {
        this.needProps = false;
        decodeProps();
      } else {
        if (this.needProps)
          throw new CorruptedInputException(); 
        if (i >= 160)
          this.lzma.reset(); 
      } 
      this.rc.prepareInputBuffer(this.in, j);
    } else {
      if (i > 2)
        throw new CorruptedInputException(); 
      this.isLZMAChunk = false;
      this.uncompressedSize = this.in.readUnsignedShort() + 1;
    } 
  }
  
  private void decodeProps() throws IOException {
    int i = this.in.readUnsignedByte();
    if (i > 224)
      throw new CorruptedInputException(); 
    int j = i / 45;
    i -= j * 9 * 5;
    int k = i / 9;
    int m = i - k * 9;
    if (m + k > 4)
      throw new CorruptedInputException(); 
    this.lzma = new LZMADecoder(this.lz, this.rc, m, k, j);
  }
  
  public int available() throws IOException {
    if (this.in == null)
      throw new XZIOException("Stream closed"); 
    if (this.exception != null)
      throw this.exception; 
    return this.uncompressedSize;
  }
  
  public void close() throws IOException {
    if (this.in != null)
      try {
        this.in.close();
      } finally {
        this.in = null;
      }  
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\LZMA2InputStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */