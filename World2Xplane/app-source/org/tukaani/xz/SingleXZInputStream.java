package org.tukaani.xz;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.tukaani.xz.check.Check;
import org.tukaani.xz.common.DecoderUtil;
import org.tukaani.xz.common.StreamFlags;
import org.tukaani.xz.index.IndexHash;

public class SingleXZInputStream extends InputStream {
  private InputStream in;
  
  private int memoryLimit;
  
  private StreamFlags streamHeaderFlags;
  
  private Check check;
  
  private BlockInputStream blockDecoder = null;
  
  private IndexHash indexHash = new IndexHash();
  
  private boolean endReached = false;
  
  private IOException exception = null;
  
  public SingleXZInputStream(InputStream paramInputStream) throws IOException {
    initialize(paramInputStream, -1);
  }
  
  public SingleXZInputStream(InputStream paramInputStream, int paramInt) throws IOException {
    initialize(paramInputStream, paramInt);
  }
  
  SingleXZInputStream(InputStream paramInputStream, int paramInt, byte[] paramArrayOfbyte) throws IOException {
    initialize(paramInputStream, paramInt, paramArrayOfbyte);
  }
  
  private void initialize(InputStream paramInputStream, int paramInt) throws IOException {
    byte[] arrayOfByte = new byte[12];
    (new DataInputStream(paramInputStream)).readFully(arrayOfByte);
    initialize(paramInputStream, paramInt, arrayOfByte);
  }
  
  private void initialize(InputStream paramInputStream, int paramInt, byte[] paramArrayOfbyte) throws IOException {
    this.in = paramInputStream;
    this.memoryLimit = paramInt;
    this.streamHeaderFlags = DecoderUtil.decodeStreamHeader(paramArrayOfbyte);
    this.check = Check.getInstance(this.streamHeaderFlags.checkType);
  }
  
  public int getCheckType() {
    return this.streamHeaderFlags.checkType;
  }
  
  public String getCheckName() {
    return this.check.getName();
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
    int i = 0;
    try {
      while (paramInt2 > 0) {
        if (this.blockDecoder == null)
          try {
            this.blockDecoder = new BlockInputStream(this.in, this.check, this.memoryLimit, -1L, -1L);
          } catch (IndexIndicatorException indexIndicatorException) {
            this.indexHash.validate(this.in);
            validateStreamFooter();
            this.endReached = true;
            return i ? i : -1;
          }  
        int j = this.blockDecoder.read(paramArrayOfbyte, paramInt1, paramInt2);
        if (j > 0) {
          i += j;
          paramInt1 += j;
          paramInt2 -= j;
          continue;
        } 
        if (j == -1) {
          this.indexHash.add(this.blockDecoder.getUnpaddedSize(), this.blockDecoder.getUncompressedSize());
          this.blockDecoder = null;
        } 
      } 
    } catch (IOException iOException) {
      this.exception = iOException;
      if (i == 0)
        throw iOException; 
    } 
    return i;
  }
  
  private void validateStreamFooter() throws IOException {
    byte[] arrayOfByte = new byte[12];
    (new DataInputStream(this.in)).readFully(arrayOfByte);
    StreamFlags streamFlags = DecoderUtil.decodeStreamFooter(arrayOfByte);
    if (!DecoderUtil.areStreamFlagsEqual(this.streamHeaderFlags, streamFlags) || this.indexHash.getIndexSize() != streamFlags.backwardSize)
      throw new CorruptedInputException("XZ Stream Footer does not match Stream Header"); 
  }
  
  public int available() throws IOException {
    if (this.in == null)
      throw new XZIOException("Stream closed"); 
    if (this.exception != null)
      throw this.exception; 
    return (this.blockDecoder == null) ? 0 : this.blockDecoder.available();
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


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\SingleXZInputStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */