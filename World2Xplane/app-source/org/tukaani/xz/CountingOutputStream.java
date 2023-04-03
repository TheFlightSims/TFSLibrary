package org.tukaani.xz;

import java.io.IOException;
import java.io.OutputStream;

class CountingOutputStream extends FinishableOutputStream {
  private OutputStream out;
  
  private long size = 0L;
  
  public CountingOutputStream(OutputStream paramOutputStream) {
    this.out = paramOutputStream;
  }
  
  public void write(int paramInt) throws IOException {
    this.out.write(paramInt);
    if (this.size >= 0L)
      this.size++; 
  }
  
  public void write(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    this.out.write(paramArrayOfbyte, paramInt1, paramInt2);
    if (this.size >= 0L)
      this.size += paramInt2; 
  }
  
  public void flush() throws IOException {
    this.out.flush();
  }
  
  public void close() throws IOException {
    this.out.close();
  }
  
  public long getSize() {
    return this.size;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\CountingOutputStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */