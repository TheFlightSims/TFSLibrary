package org.tukaani.xz;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

class CountingInputStream extends FilterInputStream {
  private long size = 0L;
  
  public CountingInputStream(InputStream paramInputStream) {
    super(paramInputStream);
  }
  
  public int read() throws IOException {
    int i = this.in.read();
    if (i != -1 && this.size >= 0L)
      this.size++; 
    return i;
  }
  
  public int read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    int i = this.in.read(paramArrayOfbyte, paramInt1, paramInt2);
    if (i > 0 && this.size >= 0L)
      this.size += i; 
    return i;
  }
  
  public long getSize() {
    return this.size;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\CountingInputStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */