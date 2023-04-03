package org.apache.xerces.impl.dv.util;

public final class Base64 {
  private static final int BASELENGTH = 128;
  
  private static final int LOOKUPLENGTH = 64;
  
  private static final int TWENTYFOURBITGROUP = 24;
  
  private static final int EIGHTBIT = 8;
  
  private static final int SIXTEENBIT = 16;
  
  private static final int SIXBIT = 6;
  
  private static final int FOURBYTE = 4;
  
  private static final int SIGN = -128;
  
  private static final char PAD = '=';
  
  private static final boolean fDebug = false;
  
  private static final byte[] base64Alphabet = new byte[128];
  
  private static final char[] lookUpBase64Alphabet = new char[64];
  
  protected static boolean isWhiteSpace(char paramChar) {
    return (paramChar == ' ' || paramChar == '\r' || paramChar == '\n' || paramChar == '\t');
  }
  
  protected static boolean isPad(char paramChar) {
    return (paramChar == '=');
  }
  
  protected static boolean isData(char paramChar) {
    return (paramChar < '' && base64Alphabet[paramChar] != -1);
  }
  
  protected static boolean isBase64(char paramChar) {
    return (isWhiteSpace(paramChar) || isPad(paramChar) || isData(paramChar));
  }
  
  public static String encode(byte[] paramArrayOfbyte) {
    if (paramArrayOfbyte == null)
      return null; 
    int i = paramArrayOfbyte.length * 8;
    if (i == 0)
      return ""; 
    int j = i % 24;
    int k = i / 24;
    int m = (j != 0) ? (k + 1) : k;
    char[] arrayOfChar = null;
    arrayOfChar = new char[m * 4];
    byte b1 = 0;
    byte b2 = 0;
    byte b3 = 0;
    byte b4 = 0;
    byte b5 = 0;
    byte b6 = 0;
    byte b7 = 0;
    for (byte b8 = 0; b8 < k; b8++) {
      b3 = paramArrayOfbyte[b7++];
      b4 = paramArrayOfbyte[b7++];
      b5 = paramArrayOfbyte[b7++];
      b2 = (byte)(b4 & 0xF);
      b1 = (byte)(b3 & 0x3);
      byte b9 = ((b3 & 0xFFFFFF80) == 0) ? (byte)(b3 >> 2) : (byte)(b3 >> 2 ^ 0xC0);
      byte b10 = ((b4 & 0xFFFFFF80) == 0) ? (byte)(b4 >> 4) : (byte)(b4 >> 4 ^ 0xF0);
      byte b11 = ((b5 & 0xFFFFFF80) == 0) ? (byte)(b5 >> 6) : (byte)(b5 >> 6 ^ 0xFC);
      arrayOfChar[b6++] = lookUpBase64Alphabet[b9];
      arrayOfChar[b6++] = lookUpBase64Alphabet[b10 | b1 << 4];
      arrayOfChar[b6++] = lookUpBase64Alphabet[b2 << 2 | b11];
      arrayOfChar[b6++] = lookUpBase64Alphabet[b5 & 0x3F];
    } 
    if (j == 8) {
      b3 = paramArrayOfbyte[b7];
      b1 = (byte)(b3 & 0x3);
      byte b = ((b3 & 0xFFFFFF80) == 0) ? (byte)(b3 >> 2) : (byte)(b3 >> 2 ^ 0xC0);
      arrayOfChar[b6++] = lookUpBase64Alphabet[b];
      arrayOfChar[b6++] = lookUpBase64Alphabet[b1 << 4];
      arrayOfChar[b6++] = '=';
      arrayOfChar[b6++] = '=';
    } else if (j == 16) {
      b3 = paramArrayOfbyte[b7];
      b4 = paramArrayOfbyte[b7 + 1];
      b2 = (byte)(b4 & 0xF);
      b1 = (byte)(b3 & 0x3);
      byte b9 = ((b3 & 0xFFFFFF80) == 0) ? (byte)(b3 >> 2) : (byte)(b3 >> 2 ^ 0xC0);
      byte b10 = ((b4 & 0xFFFFFF80) == 0) ? (byte)(b4 >> 4) : (byte)(b4 >> 4 ^ 0xF0);
      arrayOfChar[b6++] = lookUpBase64Alphabet[b9];
      arrayOfChar[b6++] = lookUpBase64Alphabet[b10 | b1 << 4];
      arrayOfChar[b6++] = lookUpBase64Alphabet[b2 << 2];
      arrayOfChar[b6++] = '=';
    } 
    return new String(arrayOfChar);
  }
  
  public static byte[] decode(String paramString) {
    if (paramString == null)
      return null; 
    char[] arrayOfChar = paramString.toCharArray();
    int i = removeWhiteSpace(arrayOfChar);
    if (i % 4 != 0)
      return null; 
    int j = i / 4;
    if (j == 0)
      return new byte[0]; 
    byte[] arrayOfByte = null;
    byte b1 = 0;
    byte b2 = 0;
    byte b3 = 0;
    byte b4 = 0;
    char c1 = Character.MIN_VALUE;
    char c2 = Character.MIN_VALUE;
    char c3 = Character.MIN_VALUE;
    char c4 = Character.MIN_VALUE;
    byte b5 = 0;
    byte b6 = 0;
    byte b7 = 0;
    arrayOfByte = new byte[j * 3];
    while (b5 < j - 1) {
      if (!isData(c1 = arrayOfChar[b7++]) || !isData(c2 = arrayOfChar[b7++]) || !isData(c3 = arrayOfChar[b7++]) || !isData(c4 = arrayOfChar[b7++]))
        return null; 
      b1 = base64Alphabet[c1];
      b2 = base64Alphabet[c2];
      b3 = base64Alphabet[c3];
      b4 = base64Alphabet[c4];
      arrayOfByte[b6++] = (byte)(b1 << 2 | b2 >> 4);
      arrayOfByte[b6++] = (byte)((b2 & 0xF) << 4 | b3 >> 2 & 0xF);
      arrayOfByte[b6++] = (byte)(b3 << 6 | b4);
      b5++;
    } 
    if (!isData(c1 = arrayOfChar[b7++]) || !isData(c2 = arrayOfChar[b7++]))
      return null; 
    b1 = base64Alphabet[c1];
    b2 = base64Alphabet[c2];
    c3 = arrayOfChar[b7++];
    c4 = arrayOfChar[b7++];
    if (!isData(c3) || !isData(c4)) {
      if (isPad(c3) && isPad(c4)) {
        if ((b2 & 0xF) != 0)
          return null; 
        byte[] arrayOfByte1 = new byte[b5 * 3 + 1];
        System.arraycopy(arrayOfByte, 0, arrayOfByte1, 0, b5 * 3);
        arrayOfByte1[b6] = (byte)(b1 << 2 | b2 >> 4);
        return arrayOfByte1;
      } 
      if (!isPad(c3) && isPad(c4)) {
        b3 = base64Alphabet[c3];
        if ((b3 & 0x3) != 0)
          return null; 
        byte[] arrayOfByte1 = new byte[b5 * 3 + 2];
        System.arraycopy(arrayOfByte, 0, arrayOfByte1, 0, b5 * 3);
        arrayOfByte1[b6++] = (byte)(b1 << 2 | b2 >> 4);
        arrayOfByte1[b6] = (byte)((b2 & 0xF) << 4 | b3 >> 2 & 0xF);
        return arrayOfByte1;
      } 
      return null;
    } 
    b3 = base64Alphabet[c3];
    b4 = base64Alphabet[c4];
    arrayOfByte[b6++] = (byte)(b1 << 2 | b2 >> 4);
    arrayOfByte[b6++] = (byte)((b2 & 0xF) << 4 | b3 >> 2 & 0xF);
    arrayOfByte[b6++] = (byte)(b3 << 6 | b4);
    return arrayOfByte;
  }
  
  protected static int removeWhiteSpace(char[] paramArrayOfchar) {
    if (paramArrayOfchar == null)
      return 0; 
    byte b1 = 0;
    int i = paramArrayOfchar.length;
    for (byte b2 = 0; b2 < i; b2++) {
      if (!isWhiteSpace(paramArrayOfchar[b2]))
        paramArrayOfchar[b1++] = paramArrayOfchar[b2]; 
    } 
    return b1;
  }
  
  static {
    for (byte b1 = 0; b1 < ''; b1++)
      base64Alphabet[b1] = -1; 
    for (byte b2 = 90; b2 >= 65; b2--)
      base64Alphabet[b2] = (byte)(b2 - 65); 
    for (byte b3 = 122; b3 >= 97; b3--)
      base64Alphabet[b3] = (byte)(b3 - 97 + 26); 
    for (byte b4 = 57; b4 >= 48; b4--)
      base64Alphabet[b4] = (byte)(b4 - 48 + 52); 
    base64Alphabet[43] = 62;
    base64Alphabet[47] = 63;
    for (byte b5 = 0; b5 <= 25; b5++)
      lookUpBase64Alphabet[b5] = (char)(65 + b5); 
    byte b6 = 26;
    for (byte b7 = 0; b6 <= 51; b7++) {
      lookUpBase64Alphabet[b6] = (char)(97 + b7);
      b6++;
    } 
    byte b8 = 52;
    for (byte b9 = 0; b8 <= 61; b9++) {
      lookUpBase64Alphabet[b8] = (char)(48 + b9);
      b8++;
    } 
    lookUpBase64Alphabet[62] = '+';
    lookUpBase64Alphabet[63] = '/';
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\d\\util\Base64.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */