package org.hsqldb.lib;

import java.io.DataInput;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class HsqlByteArrayInputStream extends InputStream implements DataInput {
  protected byte[] buffer;
  
  protected int pos;
  
  protected int mark = 0;
  
  protected int count;
  
  public HsqlByteArrayInputStream(byte[] paramArrayOfbyte) {
    this.buffer = paramArrayOfbyte;
    this.pos = 0;
    this.count = paramArrayOfbyte.length;
  }
  
  public HsqlByteArrayInputStream(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    this.buffer = paramArrayOfbyte;
    this.pos = paramInt1;
    this.count = Math.min(paramInt1 + paramInt2, paramArrayOfbyte.length);
    this.mark = paramInt1;
  }
  
  public final void readFully(byte[] paramArrayOfbyte) throws IOException {
    readFully(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public final void readFully(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    if (paramInt2 < 0)
      throw new IndexOutOfBoundsException(); 
    int i;
    for (i = 0; i < paramInt2; i += j) {
      int j = read(paramArrayOfbyte, paramInt1 + i, paramInt2 - i);
      if (j < 0)
        throw new EOFException(); 
    } 
  }
  
  public final boolean readBoolean() throws IOException {
    int i = read();
    if (i < 0)
      throw new EOFException(); 
    return (i != 0);
  }
  
  public final byte readByte() throws IOException {
    int i = read();
    if (i < 0)
      throw new EOFException(); 
    return (byte)i;
  }
  
  public final int readUnsignedByte() throws IOException {
    int i = read();
    if (i < 0)
      throw new EOFException(); 
    return i;
  }
  
  public short readShort() throws IOException {
    if (this.count - this.pos < 2) {
      this.pos = this.count;
      throw new EOFException();
    } 
    int i = this.buffer[this.pos++] & 0xFF;
    int j = this.buffer[this.pos++] & 0xFF;
    return (short)((i << 8) + j);
  }
  
  public final int readUnsignedShort() throws IOException {
    int i = read();
    int j = read();
    if ((i | j) < 0)
      throw new EOFException(); 
    return (i << 8) + j;
  }
  
  public final char readChar() throws IOException {
    int i = read();
    int j = read();
    if ((i | j) < 0)
      throw new EOFException(); 
    return (char)((i << 8) + j);
  }
  
  public int readInt() throws IOException {
    if (this.count - this.pos < 4) {
      this.pos = this.count;
      throw new EOFException();
    } 
    int i = this.buffer[this.pos++] & 0xFF;
    int j = this.buffer[this.pos++] & 0xFF;
    int k = this.buffer[this.pos++] & 0xFF;
    int m = this.buffer[this.pos++] & 0xFF;
    return (i << 24) + (j << 16) + (k << 8) + m;
  }
  
  public long readLong() throws IOException {
    return (readInt() << 32L) + (readInt() & 0xFFFFFFFFL);
  }
  
  public final float readFloat() throws IOException {
    return Float.intBitsToFloat(readInt());
  }
  
  public final double readDouble() throws IOException {
    return Double.longBitsToDouble(readLong());
  }
  
  public int skipBytes(int paramInt) throws IOException {
    return (int)skip(paramInt);
  }
  
  public String readLine() throws IOException {
    throw new RuntimeException("not implemented.");
  }
  
  public String readUTF() throws IOException {
    int i = readUnsignedShort();
    if (this.pos + i >= this.count)
      throw new EOFException(); 
    String str = StringConverter.readUTF(this.buffer, this.pos, i);
    this.pos += i;
    return str;
  }
  
  public int read() {
    return (this.pos < this.count) ? (this.buffer[this.pos++] & 0xFF) : -1;
  }
  
  public int read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    if (this.pos >= this.count)
      return -1; 
    if (this.pos + paramInt2 > this.count)
      paramInt2 = this.count - this.pos; 
    if (paramInt2 <= 0)
      return 0; 
    System.arraycopy(this.buffer, this.pos, paramArrayOfbyte, paramInt1, paramInt2);
    this.pos += paramInt2;
    return paramInt2;
  }
  
  public long skip(long paramLong) {
    if (this.pos + paramLong > this.count)
      paramLong = (this.count - this.pos); 
    if (paramLong < 0L)
      return 0L; 
    this.pos = (int)(this.pos + paramLong);
    return paramLong;
  }
  
  public int available() {
    return this.count - this.pos;
  }
  
  public boolean markSupported() {
    return true;
  }
  
  public void mark(int paramInt) {
    this.mark = this.pos;
  }
  
  public void reset() {
    this.pos = this.mark;
  }
  
  public void close() throws IOException {}
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\HsqlByteArrayInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */