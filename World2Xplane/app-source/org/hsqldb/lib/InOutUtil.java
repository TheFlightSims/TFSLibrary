package org.hsqldb.lib;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;

public final class InOutUtil {
  public static final int DEFAULT_COPY_BUFFER_SIZE = 8192;
  
  public static final long DEFAULT_COPY_AMOUNT = 9223372036854775807L;
  
  public static int readLine(InputStream paramInputStream, OutputStream paramOutputStream) throws IOException {
    int i;
    byte b = 0;
    do {
      i = paramInputStream.read();
      if (i == -1)
        break; 
      b++;
      paramOutputStream.write(i);
    } while (i != 10);
    return b;
  }
  
  public static byte[] serialize(Serializable paramSerializable) throws IOException {
    HsqlByteArrayOutputStream hsqlByteArrayOutputStream = new HsqlByteArrayOutputStream();
    ObjectOutputStream objectOutputStream = new ObjectOutputStream(hsqlByteArrayOutputStream);
    objectOutputStream.writeObject(paramSerializable);
    return hsqlByteArrayOutputStream.toByteArray();
  }
  
  public static Serializable deserialize(byte[] paramArrayOfbyte) throws IOException, ClassNotFoundException {
    HsqlByteArrayInputStream hsqlByteArrayInputStream = new HsqlByteArrayInputStream(paramArrayOfbyte);
    ObjectInputStream objectInputStream = new ObjectInputStream(hsqlByteArrayInputStream);
    return (Serializable)objectInputStream.readObject();
  }
  
  public static long copy(InputStream paramInputStream, OutputStream paramOutputStream) throws IOException {
    return copy(paramInputStream, paramOutputStream, Long.MAX_VALUE, 8192);
  }
  
  public static long copy(InputStream paramInputStream, OutputStream paramOutputStream, long paramLong) throws IOException {
    return copy(paramInputStream, paramOutputStream, paramLong, 8192);
  }
  
  public static long copy(InputStream paramInputStream, OutputStream paramOutputStream, long paramLong, int paramInt) throws IOException {
    int i = (int)Math.min(paramInt, paramLong);
    byte[] arrayOfByte = new byte[i];
    long l = 0L;
    int j;
    while (l < paramLong && -1 != (j = paramInputStream.read(arrayOfByte, 0, i))) {
      paramOutputStream.write(arrayOfByte, 0, j);
      if (j > Long.MAX_VALUE - l) {
        l = Long.MAX_VALUE;
      } else {
        l += j;
      } 
      if (l >= paramLong)
        return l; 
      i = (int)Math.min(paramInt, paramLong - l);
    } 
    return l;
  }
  
  public static long copy(Reader paramReader, Writer paramWriter) throws IOException {
    return copy(paramReader, paramWriter, Long.MAX_VALUE, 8192);
  }
  
  public static long copy(Reader paramReader, Writer paramWriter, long paramLong) throws IOException {
    return copy(paramReader, paramWriter, paramLong, 8192);
  }
  
  public static long copy(Reader paramReader, Writer paramWriter, long paramLong, int paramInt) throws IOException {
    int i = (int)Math.min(paramInt, paramLong);
    char[] arrayOfChar = new char[i];
    long l = 0L;
    int j;
    while (l < paramLong && -1 != (j = paramReader.read(arrayOfChar, 0, i))) {
      paramWriter.write(arrayOfChar, 0, j);
      if (j > Long.MAX_VALUE - l) {
        l = Long.MAX_VALUE;
      } else {
        l += j;
      } 
      if (l >= paramLong)
        return l; 
      i = (int)Math.min(paramInt, paramLong - l);
    } 
    return l;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\InOutUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */