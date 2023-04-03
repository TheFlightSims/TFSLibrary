package org.tukaani.xz.lz;

import java.io.DataInputStream;
import java.io.IOException;
import org.tukaani.xz.CorruptedInputException;

public final class LZDecoder {
  private byte[] buf;
  
  private int start = 0;
  
  private int pos = 0;
  
  private int full = 0;
  
  private int limit = 0;
  
  private int pendingLen = 0;
  
  private int pendingDist = 0;
  
  public LZDecoder(int paramInt, byte[] paramArrayOfbyte) {
    this.buf = new byte[paramInt];
    if (paramArrayOfbyte != null) {
      this.pos = Math.min(paramArrayOfbyte.length, paramInt);
      this.full = this.pos;
      this.start = this.pos;
      System.arraycopy(paramArrayOfbyte, paramArrayOfbyte.length - this.pos, this.buf, 0, this.pos);
    } 
  }
  
  public void reset() {
    this.start = 0;
    this.pos = 0;
    this.full = 0;
    this.limit = 0;
    this.buf[this.buf.length - 1] = 0;
  }
  
  public void setLimit(int paramInt) {
    if (this.buf.length - this.pos <= paramInt) {
      this.limit = this.buf.length;
    } else {
      this.limit = this.pos + paramInt;
    } 
  }
  
  public boolean hasSpace() {
    return (this.pos < this.limit);
  }
  
  public boolean hasPending() {
    return (this.pendingLen > 0);
  }
  
  public int getPos() {
    return this.pos;
  }
  
  public int getByte(int paramInt) {
    int i = this.pos - paramInt - 1;
    if (paramInt >= this.pos)
      i += this.buf.length; 
    return this.buf[i] & 0xFF;
  }
  
  public void putByte(byte paramByte) {
    this.buf[this.pos++] = paramByte;
    if (this.full < this.pos)
      this.full = this.pos; 
  }
  
  public void repeat(int paramInt1, int paramInt2) throws IOException {
    if (paramInt1 < 0 || paramInt1 >= this.full)
      throw new CorruptedInputException(); 
    int i = Math.min(this.limit - this.pos, paramInt2);
    this.pendingLen = paramInt2 - i;
    this.pendingDist = paramInt1;
    int j = this.pos - paramInt1 - 1;
    if (paramInt1 >= this.pos)
      j += this.buf.length; 
    while (true) {
      this.buf[this.pos++] = this.buf[j++];
      if (j == this.buf.length)
        j = 0; 
      if (--i <= 0) {
        if (this.full < this.pos)
          this.full = this.pos; 
        return;
      } 
    } 
  }
  
  public void repeatPending() throws IOException {
    if (this.pendingLen > 0)
      repeat(this.pendingDist, this.pendingLen); 
  }
  
  public void copyUncompressed(DataInputStream paramDataInputStream, int paramInt) throws IOException {
    int i = Math.min(this.buf.length - this.pos, paramInt);
    paramDataInputStream.readFully(this.buf, this.pos, i);
    this.pos += i;
    if (this.full < this.pos)
      this.full = this.pos; 
  }
  
  public int flush(byte[] paramArrayOfbyte, int paramInt) {
    int i = this.pos - this.start;
    if (this.pos == this.buf.length)
      this.pos = 0; 
    System.arraycopy(this.buf, this.start, paramArrayOfbyte, paramInt, i);
    this.start = this.pos;
    return i;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\lz\LZDecoder.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */