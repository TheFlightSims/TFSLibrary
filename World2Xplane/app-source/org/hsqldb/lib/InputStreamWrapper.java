package org.hsqldb.lib;

import java.io.IOException;
import java.io.InputStream;

public class InputStreamWrapper implements InputStreamInterface {
  InputStream is;
  
  long limitSize = -1L;
  
  long fetchedSize = 0L;
  
  public InputStreamWrapper(InputStream paramInputStream) {
    this.is = paramInputStream;
  }
  
  public int read() throws IOException {
    if (this.fetchedSize == this.limitSize)
      return -1; 
    int i = this.is.read();
    if (i < 0) {
      if (this.limitSize == -1L)
        return -1; 
      throw new IOException("stream not reached the end" + this.fetchedSize + " " + this.limitSize);
    } 
    this.fetchedSize++;
    return i;
  }
  
  public int read(byte[] paramArrayOfbyte) throws IOException {
    return read(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public int read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    if (this.fetchedSize == this.limitSize)
      return -1; 
    if (this.limitSize >= 0L && this.limitSize - this.fetchedSize < paramInt2)
      paramInt2 = (int)(this.limitSize - this.fetchedSize); 
    int i = this.is.read(paramArrayOfbyte, paramInt1, paramInt2);
    if (i < 0) {
      if (this.limitSize == -1L)
        return -1; 
      throw new IOException("stream not reached the end" + this.fetchedSize + " " + this.limitSize);
    } 
    this.fetchedSize += i;
    return i;
  }
  
  public long skip(long paramLong) throws IOException {
    return this.is.skip(paramLong);
  }
  
  public int available() throws IOException {
    return this.is.available();
  }
  
  public void close() throws IOException {
    this.is.close();
  }
  
  public void setSizeLimit(long paramLong) {
    this.limitSize = paramLong;
  }
  
  public long getSizeLimit() {
    return this.limitSize;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\InputStreamWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */