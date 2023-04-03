package org.hsqldb.lib;

import java.io.IOException;
import java.io.InputStream;
import java.io.UTFDataFormatException;
import org.hsqldb.map.BitMap;

public class StringConverter {
  private static final byte[] HEXBYTES = new byte[] { 
      48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 
      97, 98, 99, 100, 101, 102 };
  
  private static int getNibble(int paramInt) {
    return (paramInt >= 48 && paramInt <= 57) ? (paramInt - 48) : ((paramInt >= 97 && paramInt <= 102) ? (10 + paramInt - 97) : ((paramInt >= 65 && paramInt <= 70) ? (10 + paramInt - 65) : -1));
  }
  
  public static byte[] hexStringToByteArray(String paramString) throws IOException {
    int i = paramString.length();
    byte[] arrayOfByte = new byte[i / 2 + i % 2];
    int j = 0;
    boolean bool = true;
    byte b1 = 0;
    for (byte b2 = 0; b2 < i; b2++) {
      char c = paramString.charAt(b2);
      if (c != ' ') {
        int k = getNibble(c);
        if (k == -1)
          throw new IOException("hexadecimal string contains non hex character"); 
        if (bool) {
          j = (k & 0xF) << 4;
          bool = false;
        } else {
          j += k & 0xF;
          bool = true;
          arrayOfByte[b1++] = (byte)j;
        } 
      } 
    } 
    if (!bool)
      throw new IOException("hexadecimal string with odd number of characters"); 
    if (b1 < arrayOfByte.length)
      arrayOfByte = (byte[])ArrayUtil.resizeArray(arrayOfByte, b1); 
    return arrayOfByte;
  }
  
  public static BitMap sqlBitStringToBitMap(String paramString) throws IOException {
    int i = paramString.length();
    byte b1 = 0;
    BitMap bitMap = new BitMap(i, true);
    for (byte b2 = 0; b2 < i; b2++) {
      char c = paramString.charAt(b2);
      if (c != ' ') {
        int j = getNibble(c);
        if (j != 0 && j != 1)
          throw new IOException("hexadecimal string contains non hex character"); 
        if (j == 1)
          bitMap.set(b1); 
        b1++;
      } 
    } 
    bitMap.setSize(b1);
    return bitMap;
  }
  
  public static String byteArrayToHexString(byte[] paramArrayOfbyte) {
    int i = paramArrayOfbyte.length;
    char[] arrayOfChar = new char[i * 2];
    byte b1 = 0;
    byte b2 = 0;
    while (b1 < i) {
      int j = paramArrayOfbyte[b1] & 0xFF;
      arrayOfChar[b2++] = (char)HEXBYTES[j >> 4 & 0xF];
      arrayOfChar[b2++] = (char)HEXBYTES[j & 0xF];
      b1++;
    } 
    return new String(arrayOfChar);
  }
  
  public static String byteArrayToSQLHexString(byte[] paramArrayOfbyte) {
    int i = paramArrayOfbyte.length;
    char[] arrayOfChar = new char[i * 2 + 3];
    arrayOfChar[0] = 'X';
    arrayOfChar[1] = '\'';
    byte b1 = 2;
    for (byte b2 = 0; b2 < i; b2++) {
      int j = paramArrayOfbyte[b2] & 0xFF;
      arrayOfChar[b1++] = (char)HEXBYTES[j >> 4 & 0xF];
      arrayOfChar[b1++] = (char)HEXBYTES[j & 0xF];
    } 
    arrayOfChar[b1] = '\'';
    return new String(arrayOfChar);
  }
  
  public static String byteArrayToBitString(byte[] paramArrayOfbyte, int paramInt) {
    char[] arrayOfChar = new char[paramInt];
    for (byte b = 0; b < paramInt; b++) {
      byte b1 = paramArrayOfbyte[b / 8];
      arrayOfChar[b] = BitMap.isSet(b1, b % 8) ? '1' : '0';
    } 
    return new String(arrayOfChar);
  }
  
  public static String byteArrayToSQLBitString(byte[] paramArrayOfbyte, int paramInt) {
    char[] arrayOfChar = new char[paramInt + 3];
    arrayOfChar[0] = 'B';
    arrayOfChar[1] = '\'';
    byte b1 = 2;
    for (byte b2 = 0; b2 < paramInt; b2++) {
      byte b = paramArrayOfbyte[b2 / 8];
      arrayOfChar[b1++] = BitMap.isSet(b, b2 % 8) ? '1' : '0';
    } 
    arrayOfChar[b1] = '\'';
    return new String(arrayOfChar);
  }
  
  public static void writeHexBytes(byte[] paramArrayOfbyte1, int paramInt, byte[] paramArrayOfbyte2) {
    int i = paramArrayOfbyte2.length;
    for (byte b = 0; b < i; b++) {
      int j = paramArrayOfbyte2[b] & 0xFF;
      paramArrayOfbyte1[paramInt++] = HEXBYTES[j >> 4 & 0xF];
      paramArrayOfbyte1[paramInt++] = HEXBYTES[j & 0xF];
    } 
  }
  
  public static String byteArrayToString(byte[] paramArrayOfbyte, String paramString) {
    try {
      return (paramString == null) ? new String(paramArrayOfbyte) : new String(paramArrayOfbyte, paramString);
    } catch (Exception exception) {
      return null;
    } 
  }
  
  public static void stringToUnicodeBytes(HsqlByteArrayOutputStream paramHsqlByteArrayOutputStream, String paramString, boolean paramBoolean) {
    if (paramString == null)
      return; 
    int i = paramString.length();
    byte b1 = 0;
    if (i == 0)
      return; 
    paramHsqlByteArrayOutputStream.ensureRoom(i * 2 + 5);
    for (byte b2 = 0; b2 < i; b2++) {
      char c = paramString.charAt(b2);
      if (c == '\\') {
        if (b2 < i - 1 && paramString.charAt(b2 + 1) == 'u') {
          paramHsqlByteArrayOutputStream.writeNoCheck(c);
          paramHsqlByteArrayOutputStream.writeNoCheck(117);
          paramHsqlByteArrayOutputStream.writeNoCheck(48);
          paramHsqlByteArrayOutputStream.writeNoCheck(48);
          paramHsqlByteArrayOutputStream.writeNoCheck(53);
          paramHsqlByteArrayOutputStream.writeNoCheck(99);
          b1 += true;
        } else {
          paramHsqlByteArrayOutputStream.write(c);
        } 
      } else if (c >= ' ' && c <= '') {
        paramHsqlByteArrayOutputStream.writeNoCheck(c);
        if (c == '\'' && paramBoolean) {
          paramHsqlByteArrayOutputStream.writeNoCheck(c);
          b1++;
        } 
      } else {
        paramHsqlByteArrayOutputStream.writeNoCheck(92);
        paramHsqlByteArrayOutputStream.writeNoCheck(117);
        paramHsqlByteArrayOutputStream.writeNoCheck(HEXBYTES[c >> 12 & 0xF]);
        paramHsqlByteArrayOutputStream.writeNoCheck(HEXBYTES[c >> 8 & 0xF]);
        paramHsqlByteArrayOutputStream.writeNoCheck(HEXBYTES[c >> 4 & 0xF]);
        paramHsqlByteArrayOutputStream.writeNoCheck(HEXBYTES[c & 0xF]);
        b1 += 5;
      } 
      if (b1 > i) {
        paramHsqlByteArrayOutputStream.ensureRoom(i + b1 + 5);
        b1 = 0;
      } 
    } 
  }
  
  public static String unicodeStringToString(String paramString) {
    if (paramString == null || paramString.indexOf("\\u") == -1)
      return paramString; 
    int i = paramString.length();
    char[] arrayOfChar = new char[i];
    byte b1 = 0;
    for (byte b2 = 0; b2 < i; b2++) {
      char c = paramString.charAt(b2);
      if (c == '\\' && b2 < i - 5) {
        char c1 = paramString.charAt(b2 + 1);
        if (c1 == 'u') {
          b2++;
          int j = getNibble(paramString.charAt(++b2)) << 12;
          j += getNibble(paramString.charAt(++b2)) << 8;
          j += getNibble(paramString.charAt(++b2)) << 4;
          j += getNibble(paramString.charAt(++b2));
          arrayOfChar[b1++] = (char)j;
        } else {
          arrayOfChar[b1++] = c;
        } 
      } else {
        arrayOfChar[b1++] = c;
      } 
    } 
    return new String(arrayOfChar, 0, b1);
  }
  
  public static String readUTF(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    char[] arrayOfChar = new char[paramInt2];
    return readUTF(paramArrayOfbyte, paramInt1, paramInt2, arrayOfChar);
  }
  
  public static String readUTF(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, char[] paramArrayOfchar) throws IOException {
    byte b1 = 0;
    byte b2 = 0;
    while (b2 < paramInt2) {
      byte b4;
      byte b5;
      byte b3 = paramArrayOfbyte[paramInt1 + b2];
      if (b1 == paramArrayOfchar.length)
        paramArrayOfchar = (char[])ArrayUtil.resizeArray(paramArrayOfchar, paramInt2); 
      if (b3 > 0) {
        b2++;
        paramArrayOfchar[b1++] = (char)b3;
        continue;
      } 
      int i = b3 & 0xFF;
      switch (i >> 4) {
        case 12:
        case 13:
          b2 += 2;
          if (b2 > paramInt2)
            throw new UTFDataFormatException(); 
          b4 = paramArrayOfbyte[paramInt1 + b2 - 1];
          if ((b4 & 0xC0) != 128)
            throw new UTFDataFormatException(); 
          paramArrayOfchar[b1++] = (char)((i & 0x1F) << 6 | b4 & 0x3F);
          continue;
        case 14:
          b2 += 3;
          if (b2 > paramInt2)
            throw new UTFDataFormatException(); 
          b4 = paramArrayOfbyte[paramInt1 + b2 - 2];
          b5 = paramArrayOfbyte[paramInt1 + b2 - 1];
          if ((b4 & 0xC0) != 128 || (b5 & 0xC0) != 128)
            throw new UTFDataFormatException(); 
          paramArrayOfchar[b1++] = (char)((i & 0xF) << 12 | (b4 & 0x3F) << 6 | (b5 & 0x3F) << 0);
          continue;
      } 
      throw new UTFDataFormatException();
    } 
    return new String(paramArrayOfchar, 0, b1);
  }
  
  public static int stringToUTFBytes(String paramString, HsqlByteArrayOutputStream paramHsqlByteArrayOutputStream) {
    int i = paramString.length();
    byte b1 = 0;
    if (paramHsqlByteArrayOutputStream.count + i + 8 > paramHsqlByteArrayOutputStream.buffer.length)
      paramHsqlByteArrayOutputStream.ensureRoom(i + 8); 
    char[] arrayOfChar = paramString.toCharArray();
    for (byte b2 = 0; b2 < i; b2++) {
      char c = arrayOfChar[b2];
      if (c >= '\001' && c <= '') {
        paramHsqlByteArrayOutputStream.buffer[paramHsqlByteArrayOutputStream.count++] = (byte)c;
        b1++;
      } else if (c > '߿') {
        paramHsqlByteArrayOutputStream.buffer[paramHsqlByteArrayOutputStream.count++] = (byte)(0xE0 | c >> 12 & 0xF);
        paramHsqlByteArrayOutputStream.buffer[paramHsqlByteArrayOutputStream.count++] = (byte)(0x80 | c >> 6 & 0x3F);
        paramHsqlByteArrayOutputStream.buffer[paramHsqlByteArrayOutputStream.count++] = (byte)(0x80 | c >> 0 & 0x3F);
        b1 += 3;
      } else {
        paramHsqlByteArrayOutputStream.buffer[paramHsqlByteArrayOutputStream.count++] = (byte)(0xC0 | c >> 6 & 0x1F);
        paramHsqlByteArrayOutputStream.buffer[paramHsqlByteArrayOutputStream.count++] = (byte)(0x80 | c >> 0 & 0x3F);
        b1 += 2;
      } 
      if (paramHsqlByteArrayOutputStream.count + 8 > paramHsqlByteArrayOutputStream.buffer.length)
        paramHsqlByteArrayOutputStream.ensureRoom(i - b2 + 8); 
    } 
    return b1;
  }
  
  public static int getUTFSize(String paramString) {
    byte b1 = (paramString == null) ? 0 : paramString.length();
    byte b2 = 0;
    for (byte b3 = 0; b3 < b1; b3++) {
      char c = paramString.charAt(b3);
      if (c >= '\001' && c <= '') {
        b2++;
      } else if (c > '߿') {
        b2 += 3;
      } else {
        b2 += 2;
      } 
    } 
    return b2;
  }
  
  public static String inputStreamToString(InputStream paramInputStream, String paramString) throws IOException {
    HsqlByteArrayOutputStream hsqlByteArrayOutputStream = new HsqlByteArrayOutputStream(1024);
    while (true) {
      int i = paramInputStream.read();
      if (i == -1)
        return new String(hsqlByteArrayOutputStream.getBuffer(), 0, hsqlByteArrayOutputStream.size(), paramString); 
      hsqlByteArrayOutputStream.write(i);
    } 
  }
  
  public static String toQuotedString(String paramString, char paramChar, boolean paramBoolean) {
    if (paramString == null)
      return null; 
    byte b1 = paramBoolean ? count(paramString, paramChar) : 0;
    int i = paramString.length();
    char[] arrayOfChar = new char[2 + b1 + i];
    byte b2 = 0;
    byte b3 = 0;
    arrayOfChar[b3++] = paramChar;
    while (b2 < i) {
      char c = paramString.charAt(b2);
      arrayOfChar[b3++] = c;
      if (paramBoolean && c == paramChar)
        arrayOfChar[b3++] = c; 
      b2++;
    } 
    arrayOfChar[b3] = paramChar;
    return new String(arrayOfChar);
  }
  
  static int count(String paramString, char paramChar) {
    int i = 0;
    byte b = 0;
    if (paramString != null)
      while ((i = paramString.indexOf(paramChar, i)) > -1) {
        b++;
        i++;
      }  
    return b;
  }
  
  public static void stringToHtmlBytes(HsqlByteArrayOutputStream paramHsqlByteArrayOutputStream, String paramString) {
    if (paramString == null)
      return; 
    int i = paramString.length();
    if (i == 0)
      return; 
    char[] arrayOfChar = paramString.toCharArray();
    paramHsqlByteArrayOutputStream.ensureRoom(i);
    for (byte b = 0; b < i; b++) {
      char c = arrayOfChar[b];
      if (c > '' || c == '"' || c == '&' || c == '<' || c == '>') {
        int j = Character.codePointAt(arrayOfChar, b);
        if (Character.charCount(j) == 2)
          b++; 
        paramHsqlByteArrayOutputStream.ensureRoom(16);
        paramHsqlByteArrayOutputStream.writeNoCheck(38);
        paramHsqlByteArrayOutputStream.writeNoCheck(35);
        paramHsqlByteArrayOutputStream.writeBytes(String.valueOf(j));
        paramHsqlByteArrayOutputStream.writeNoCheck(59);
      } else if (c < ' ') {
        paramHsqlByteArrayOutputStream.writeNoCheck(32);
      } else {
        paramHsqlByteArrayOutputStream.writeNoCheck(c);
      } 
    } 
  }
  
  public static String toStringUUID(byte[] paramArrayOfbyte) {
    char[] arrayOfChar = new char[36];
    if (paramArrayOfbyte == null)
      return null; 
    if (paramArrayOfbyte.length != 16)
      throw new NumberFormatException(); 
    byte b1 = 0;
    byte b2 = 0;
    while (b1 < paramArrayOfbyte.length) {
      int i = (paramArrayOfbyte[b1] & 0xF0) >> 4;
      arrayOfChar[b2++] = (char)HEXBYTES[i];
      i = paramArrayOfbyte[b1] & 0xF;
      arrayOfChar[b2++] = (char)HEXBYTES[i];
      if (++b1 >= 4 && b1 <= 10 && b1 % 2 == 0)
        arrayOfChar[b2++] = '-'; 
    } 
    return new String(arrayOfChar);
  }
  
  public static byte[] toBinaryUUID(String paramString) {
    byte[] arrayOfByte = new byte[16];
    if (paramString == null)
      return null; 
    if (paramString.length() != 36)
      throw new NumberFormatException(); 
    byte b1 = 0;
    byte b2 = 0;
    while (b1 < arrayOfByte.length) {
      char c = paramString.charAt(b2++);
      int i = getNibble(c);
      c = paramString.charAt(b2++);
      arrayOfByte[b1] = (byte)((i << 4) + getNibble(c));
      if (++b1 >= 4 && b1 <= 10 && b1 % 2 == 0) {
        c = paramString.charAt(b2++);
        if (c != '-');
      } 
    } 
    return arrayOfByte;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\StringConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */