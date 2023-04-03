package org.hsqldb.lib;

import java.io.BufferedOutputStream;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UTFDataFormatException;

public class DataOutputStream extends BufferedOutputStream implements DataOutput {
  byte[] tempBuffer = new byte[8];
  
  public DataOutputStream(OutputStream paramOutputStream) {
    super(paramOutputStream, 8);
  }
  
  public final void writeByte(int paramInt) throws IOException {
    write(paramInt);
  }
  
  public final void writeInt(int paramInt) throws IOException {
    byte b = 0;
    this.tempBuffer[b++] = (byte)(paramInt >>> 24);
    this.tempBuffer[b++] = (byte)(paramInt >>> 16);
    this.tempBuffer[b++] = (byte)(paramInt >>> 8);
    this.tempBuffer[b++] = (byte)paramInt;
    write(this.tempBuffer, 0, b);
  }
  
  public final void writeLong(long paramLong) throws IOException {
    writeInt((int)(paramLong >>> 32L));
    writeInt((int)paramLong);
  }
  
  public void writeChar(int paramInt) throws IOException {
    byte b = 0;
    this.tempBuffer[b++] = (byte)(paramInt >>> 8);
    this.tempBuffer[b++] = (byte)paramInt;
    write(this.tempBuffer, 0, b);
  }
  
  public void writeChars(String paramString) throws IOException {
    int i = paramString.length();
    for (byte b = 0; b < i; b++) {
      char c = paramString.charAt(b);
      byte b1 = 0;
      this.tempBuffer[b1++] = (byte)(c >>> 8);
      this.tempBuffer[b1++] = (byte)c;
      write(this.tempBuffer, 0, b1);
    } 
  }
  
  public void writeChars(char[] paramArrayOfchar) throws IOException {
    writeChars(paramArrayOfchar, paramArrayOfchar.length);
  }
  
  public void writeChars(char[] paramArrayOfchar, int paramInt) throws IOException {
    for (byte b = 0; b < paramInt; b++) {
      char c = paramArrayOfchar[b];
      byte b1 = 0;
      this.tempBuffer[b1++] = (byte)(c >>> 8);
      this.tempBuffer[b1++] = (byte)c;
      write(this.tempBuffer, 0, b1);
    } 
  }
  
  public long write(Reader paramReader, long paramLong) throws IOException {
    ReaderInputStream readerInputStream = new ReaderInputStream(paramReader);
    return write(readerInputStream, paramLong * 2L) / 2L;
  }
  
  public long write(InputStream paramInputStream, long paramLong) throws IOException {
    byte[] arrayOfByte = new byte[1024];
    for (long l = 0L;; l += l1) {
      long l1 = paramLong - l;
      if (l1 > arrayOfByte.length)
        l1 = arrayOfByte.length; 
      l1 = paramInputStream.read(arrayOfByte, 0, (int)l1);
      if (l1 < 1L)
        return l; 
      write(arrayOfByte, 0, (int)l1);
    } 
  }
  
  public void writeBoolean(boolean paramBoolean) throws IOException {
    boolean bool = paramBoolean ? true : false;
    write(bool);
  }
  
  public void writeShort(int paramInt) throws IOException {
    byte b = 0;
    this.tempBuffer[b++] = (byte)(paramInt >> 8);
    this.tempBuffer[b++] = (byte)paramInt;
    write(this.tempBuffer, 0, b);
  }
  
  public void writeFloat(float paramFloat) throws IOException {
    writeInt(Float.floatToIntBits(paramFloat));
  }
  
  public void writeDouble(double paramDouble) throws IOException {
    writeLong(Double.doubleToLongBits(paramDouble));
  }
  
  public void writeBytes(String paramString) throws IOException {
    int i = paramString.length();
    for (byte b = 0; b < i; b++)
      this.out.write((byte)paramString.charAt(b)); 
  }
  
  public void writeUTF(String paramString) throws IOException {
    int i = paramString.length();
    if (i > 65535)
      throw new UTFDataFormatException(); 
    int j = StringConverter.getUTFSize(paramString);
    if (j > 65535)
      throw new UTFDataFormatException(); 
    writeChar(j);
    HsqlByteArrayOutputStream hsqlByteArrayOutputStream = new HsqlByteArrayOutputStream(j);
    StringConverter.stringToUTFBytes(paramString, hsqlByteArrayOutputStream);
    write(hsqlByteArrayOutputStream.getBuffer(), 0, hsqlByteArrayOutputStream.size());
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\DataOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */