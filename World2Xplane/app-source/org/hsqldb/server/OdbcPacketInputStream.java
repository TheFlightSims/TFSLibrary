package org.hsqldb.server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.hsqldb.HsqlException;
import org.hsqldb.types.BinaryData;

class OdbcPacketInputStream extends DataInputStream {
  char packetType;
  
  private InputStream bufferStream;
  
  static OdbcPacketInputStream newOdbcPacketInputStream(char paramChar, InputStream paramInputStream, int paramInt) throws IOException {
    return newOdbcPacketInputStream(paramChar, paramInputStream, new Integer(paramInt));
  }
  
  static OdbcPacketInputStream newOdbcPacketInputStream(char paramChar, InputStream paramInputStream) throws IOException {
    return newOdbcPacketInputStream(paramChar, paramInputStream, (Integer)null);
  }
  
  private static OdbcPacketInputStream newOdbcPacketInputStream(char paramChar, InputStream paramInputStream, Integer paramInteger) throws IOException {
    int k = 0;
    if (paramInteger == null) {
      byte[] arrayOfByte1 = new byte[4];
      int m;
      int n;
      for (m = 0; (n = paramInputStream.read(arrayOfByte1, m, arrayOfByte1.length - m)) > 0; m += n);
      if (m != arrayOfByte1.length)
        throw new EOFException("Failed to read size header int"); 
      k = ((arrayOfByte1[0] & 0xFF) << 24) + ((arrayOfByte1[1] & 0xFF) << 16) + ((arrayOfByte1[2] & 0xFF) << 8) + (arrayOfByte1[3] & 0xFF) - 4;
    } else {
      k = paramInteger.intValue();
    } 
    byte[] arrayOfByte = new byte[k];
    int i;
    int j;
    for (i = 0; (j = paramInputStream.read(arrayOfByte, i, arrayOfByte.length - i)) > 0; i += j);
    if (i != arrayOfByte.length)
      throw new EOFException("Failed to read packet contents from given stream"); 
    return new OdbcPacketInputStream(paramChar, new ByteArrayInputStream(arrayOfByte));
  }
  
  private OdbcPacketInputStream(char paramChar, InputStream paramInputStream) {
    super(paramInputStream);
    this.packetType = paramChar;
  }
  
  Map readStringPairs() throws IOException {
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    while (true) {
      String str = readString();
      if (str.length() < 1)
        return hashMap; 
      hashMap.put(str, readString());
    } 
  }
  
  String readString() throws IOException {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    byteArrayOutputStream.write(88);
    byteArrayOutputStream.write(88);
    byte b;
    while ((b = readByte()) > 0)
      byteArrayOutputStream.write((byte)b); 
    byte[] arrayOfByte = byteArrayOutputStream.toByteArray();
    byteArrayOutputStream.close();
    int i = arrayOfByte.length - 2;
    arrayOfByte[0] = (byte)(i >>> 8);
    arrayOfByte[1] = (byte)i;
    DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(arrayOfByte));
    String str = dataInputStream.readUTF();
    dataInputStream.close();
    return str;
  }
  
  BinaryData readSizedBinaryData() throws IOException {
    int i = readInt();
    try {
      return (i < 0) ? null : new BinaryData(i, this);
    } catch (HsqlException hsqlException) {
      throw new IOException(hsqlException.getMessage());
    } 
  }
  
  String readSizedString() throws IOException {
    int i = readInt();
    return (i < 0) ? null : readString(i);
  }
  
  String readString(int paramInt) throws IOException {
    int i = 0;
    byte[] arrayOfByte = new byte[paramInt + 2];
    arrayOfByte[0] = (byte)(paramInt >>> 8);
    arrayOfByte[1] = (byte)paramInt;
    int j;
    while ((j = read(arrayOfByte, 2 + i, paramInt - i)) > -1 && i < paramInt)
      i += j; 
    if (i != paramInt)
      throw new EOFException("Packet ran dry"); 
    for (j = 2; j < arrayOfByte.length - 1; j++) {
      if (arrayOfByte[j] == 0)
        throw new RuntimeException("Null internal to String at offset " + (j - 2)); 
    } 
    DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(arrayOfByte));
    String str = dataInputStream.readUTF();
    dataInputStream.close();
    return str;
  }
  
  public char readByteChar() throws IOException {
    return (char)readByte();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\server\OdbcPacketInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */