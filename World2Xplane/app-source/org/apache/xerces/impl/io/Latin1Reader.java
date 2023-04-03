package org.apache.xerces.impl.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class Latin1Reader extends Reader {
  public static final int DEFAULT_BUFFER_SIZE = 2048;
  
  protected final InputStream fInputStream;
  
  protected final byte[] fBuffer;
  
  public Latin1Reader(InputStream paramInputStream) {
    this(paramInputStream, 2048);
  }
  
  public Latin1Reader(InputStream paramInputStream, int paramInt) {
    this(paramInputStream, new byte[paramInt]);
  }
  
  public Latin1Reader(InputStream paramInputStream, byte[] paramArrayOfbyte) {
    this.fInputStream = paramInputStream;
    this.fBuffer = paramArrayOfbyte;
  }
  
  public int read() throws IOException {
    return this.fInputStream.read();
  }
  
  public int read(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws IOException {
    if (paramInt2 > this.fBuffer.length)
      paramInt2 = this.fBuffer.length; 
    int i = this.fInputStream.read(this.fBuffer, 0, paramInt2);
    for (byte b = 0; b < i; b++)
      paramArrayOfchar[paramInt1 + b] = (char)(this.fBuffer[b] & 0xFF); 
    return i;
  }
  
  public long skip(long paramLong) throws IOException {
    return this.fInputStream.skip(paramLong);
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


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\io\Latin1Reader.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */