package org.apache.xerces.impl.dv.util;

public final class HexBin {
  private static final int BASELENGTH = 128;
  
  private static final int LOOKUPLENGTH = 16;
  
  private static final byte[] hexNumberTable = new byte[128];
  
  private static final char[] lookUpHexAlphabet = new char[16];
  
  public static String encode(byte[] paramArrayOfbyte) {
    if (paramArrayOfbyte == null)
      return null; 
    int i = paramArrayOfbyte.length;
    int j = i * 2;
    char[] arrayOfChar = new char[j];
    for (byte b = 0; b < i; b++) {
      byte b1 = paramArrayOfbyte[b];
      if (b1 < 0)
        b1 += 256; 
      arrayOfChar[b * 2] = lookUpHexAlphabet[b1 >> 4];
      arrayOfChar[b * 2 + 1] = lookUpHexAlphabet[b1 & 0xF];
    } 
    return new String(arrayOfChar);
  }
  
  public static byte[] decode(String paramString) {
    if (paramString == null)
      return null; 
    int i = paramString.length();
    if (i % 2 != 0)
      return null; 
    char[] arrayOfChar = paramString.toCharArray();
    int j = i / 2;
    byte[] arrayOfByte = new byte[j];
    for (byte b = 0; b < j; b++) {
      char c = arrayOfChar[b * 2];
      byte b1 = (c < '') ? hexNumberTable[c] : -1;
      if (b1 == -1)
        return null; 
      c = arrayOfChar[b * 2 + 1];
      boolean bool = (c < '') ? hexNumberTable[c] : true;
      if (bool == -1)
        return null; 
      arrayOfByte[b] = (byte)(b1 << 4 | bool);
    } 
    return arrayOfByte;
  }
  
  static {
    for (byte b1 = 0; b1 < ''; b1++)
      hexNumberTable[b1] = -1; 
    for (byte b2 = 57; b2 >= 48; b2--)
      hexNumberTable[b2] = (byte)(b2 - 48); 
    for (byte b3 = 70; b3 >= 65; b3--)
      hexNumberTable[b3] = (byte)(b3 - 65 + 10); 
    for (byte b4 = 102; b4 >= 97; b4--)
      hexNumberTable[b4] = (byte)(b4 - 97 + 10); 
    for (byte b5 = 0; b5 < 10; b5++)
      lookUpHexAlphabet[b5] = (char)(48 + b5); 
    for (byte b6 = 10; b6 <= 15; b6++)
      lookUpHexAlphabet[b6] = (char)(65 + b6 - 10); 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\d\\util\HexBin.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */