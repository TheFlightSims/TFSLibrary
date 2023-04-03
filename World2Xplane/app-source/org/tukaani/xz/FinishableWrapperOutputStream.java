package org.tukaani.xz;

import java.io.IOException;
import java.io.OutputStream;

public class FinishableWrapperOutputStream extends FinishableOutputStream {
  protected OutputStream out;
  
  public FinishableWrapperOutputStream(OutputStream paramOutputStream) {
    this.out = paramOutputStream;
  }
  
  public void write(int paramInt) throws IOException {
    this.out.write(paramInt);
  }
  
  public void write(byte[] paramArrayOfbyte) throws IOException {
    this.out.write(paramArrayOfbyte);
  }
  
  public void write(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    this.out.write(paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  public void flush() throws IOException {
    this.out.flush();
  }
  
  public void close() throws IOException {
    this.out.close();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\FinishableWrapperOutputStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */