package org.tukaani.xz;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class XZInputStream extends InputStream {
  private final int memoryLimit;
  
  private InputStream in;
  
  private SingleXZInputStream xzIn;
  
  private boolean endReached = false;
  
  private IOException exception = null;
  
  public XZInputStream(InputStream paramInputStream) throws IOException {
    this(paramInputStream, -1);
  }
  
  public XZInputStream(InputStream paramInputStream, int paramInt) throws IOException {
    this.in = paramInputStream;
    this.memoryLimit = paramInt;
    this.xzIn = new SingleXZInputStream(paramInputStream, paramInt);
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
        if (this.xzIn == null) {
          prepareNextStream();
          if (this.endReached)
            return !i ? -1 : i; 
        } 
        int j = this.xzIn.read(paramArrayOfbyte, paramInt1, paramInt2);
        if (j > 0) {
          i += j;
          paramInt1 += j;
          paramInt2 -= j;
          continue;
        } 
        if (j == -1)
          this.xzIn = null; 
      } 
    } catch (IOException iOException) {
      this.exception = iOException;
      if (i == 0)
        throw iOException; 
    } 
    return i;
  }
  
  private void prepareNextStream() throws IOException {
    DataInputStream dataInputStream = new DataInputStream(this.in);
    byte[] arrayOfByte = new byte[12];
    do {
      int i = dataInputStream.read(arrayOfByte, 0, 1);
      if (i == -1) {
        this.endReached = true;
        return;
      } 
      dataInputStream.readFully(arrayOfByte, 1, 3);
    } while (arrayOfByte[0] == 0 && arrayOfByte[1] == 0 && arrayOfByte[2] == 0 && arrayOfByte[3] == 0);
    dataInputStream.readFully(arrayOfByte, 4, 8);
    try {
      this.xzIn = new SingleXZInputStream(this.in, this.memoryLimit, arrayOfByte);
    } catch (XZFormatException xZFormatException) {
      throw new CorruptedInputException("Garbage after a valid XZ Stream");
    } 
  }
  
  public int available() throws IOException {
    if (this.in == null)
      throw new XZIOException("Stream closed"); 
    if (this.exception != null)
      throw this.exception; 
    return (this.xzIn == null) ? 0 : this.xzIn.available();
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


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\XZInputStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */