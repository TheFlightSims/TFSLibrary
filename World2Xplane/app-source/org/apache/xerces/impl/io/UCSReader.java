package org.apache.xerces.impl.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class UCSReader extends Reader {
  public static final int DEFAULT_BUFFER_SIZE = 8192;
  
  public static final short UCS2LE = 1;
  
  public static final short UCS2BE = 2;
  
  public static final short UCS4LE = 4;
  
  public static final short UCS4BE = 8;
  
  protected final InputStream fInputStream;
  
  protected final byte[] fBuffer;
  
  protected final short fEncoding;
  
  public UCSReader(InputStream paramInputStream, short paramShort) {
    this(paramInputStream, 8192, paramShort);
  }
  
  public UCSReader(InputStream paramInputStream, int paramInt, short paramShort) {
    this(paramInputStream, new byte[paramInt], paramShort);
  }
  
  public UCSReader(InputStream paramInputStream, byte[] paramArrayOfbyte, short paramShort) {
    this.fInputStream = paramInputStream;
    this.fBuffer = paramArrayOfbyte;
    this.fEncoding = paramShort;
  }
  
  public int read() throws IOException {
    int i = this.fInputStream.read() & 0xFF;
    if (i == 255)
      return -1; 
    int j = this.fInputStream.read() & 0xFF;
    if (j == 255)
      return -1; 
    if (this.fEncoding >= 4) {
      int k = this.fInputStream.read() & 0xFF;
      if (k == 255)
        return -1; 
      int m = this.fInputStream.read() & 0xFF;
      return (m == 255) ? -1 : ((this.fEncoding == 8) ? ((i << 24) + (j << 16) + (k << 8) + m) : ((m << 24) + (k << 16) + (j << 8) + i));
    } 
    return (this.fEncoding == 2) ? ((i << 8) + j) : ((j << 8) + i);
  }
  
  public int read(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws IOException {
    int i = paramInt2 << ((this.fEncoding >= 4) ? 2 : 1);
    if (i > this.fBuffer.length)
      i = this.fBuffer.length; 
    int j = this.fInputStream.read(this.fBuffer, 0, i);
    if (j == -1)
      return -1; 
    if (this.fEncoding >= 4) {
      int m = 4 - (j & 0x3) & 0x3;
      for (byte b = 0; b < m; b++) {
        int n = this.fInputStream.read();
        if (n == -1) {
          for (byte b3 = b; b3 < m; b3++)
            this.fBuffer[j + b3] = 0; 
          break;
        } 
        this.fBuffer[j + b] = (byte)n;
      } 
      j += m;
    } else {
      int m = j & 0x1;
      if (m != 0) {
        j++;
        int n = this.fInputStream.read();
        if (n == -1) {
          this.fBuffer[j] = 0;
        } else {
          this.fBuffer[j] = (byte)n;
        } 
      } 
    } 
    int k = j >> ((this.fEncoding >= 4) ? 2 : 1);
    byte b1 = 0;
    for (byte b2 = 0; b2 < k; b2++) {
      int m = this.fBuffer[b1++] & 0xFF;
      int n = this.fBuffer[b1++] & 0xFF;
      if (this.fEncoding >= 4) {
        int i1 = this.fBuffer[b1++] & 0xFF;
        int i2 = this.fBuffer[b1++] & 0xFF;
        if (this.fEncoding == 8) {
          paramArrayOfchar[paramInt1 + b2] = (char)((m << 24) + (n << 16) + (i1 << 8) + i2);
        } else {
          paramArrayOfchar[paramInt1 + b2] = (char)((i2 << 24) + (i1 << 16) + (n << 8) + m);
        } 
      } else if (this.fEncoding == 2) {
        paramArrayOfchar[paramInt1 + b2] = (char)((m << 8) + n);
      } else {
        paramArrayOfchar[paramInt1 + b2] = (char)((n << 8) + m);
      } 
    } 
    return k;
  }
  
  public long skip(long paramLong) throws IOException {
    boolean bool = (this.fEncoding >= 4) ? true : true;
    long l = this.fInputStream.skip(paramLong << bool);
    return ((l & (bool | true)) == 0L) ? (l >> bool) : ((l >> bool) + 1L);
  }
  
  public boolean ready() throws IOException {
    return false;
  }
  
  public boolean markSupported() {
    return this.fInputStream.markSupported();
  }
  
  public void mark(int paramInt) throws IOException {
    this.fInputStream.mark(paramInt);
  }
  
  public void reset() throws IOException {
    this.fInputStream.reset();
  }
  
  public void close() throws IOException {
    this.fInputStream.close();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\io\UCSReader.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */