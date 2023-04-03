package org.hsqldb.lib;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class MD5 {
  private static MessageDigest md5;
  
  public static final String encode(String paramString1, String paramString2) throws RuntimeException {
    return StringConverter.byteArrayToHexString(digest(paramString1, paramString2));
  }
  
  public static final String digest(String paramString) throws RuntimeException {
    return encode(paramString, "ISO-8859-1");
  }
  
  public static final byte[] digest(String paramString1, String paramString2) throws RuntimeException {
    byte[] arrayOfByte;
    if (paramString2 == null)
      paramString2 = "ISO-8859-1"; 
    try {
      arrayOfByte = paramString1.getBytes(paramString2);
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      throw new RuntimeException(unsupportedEncodingException.toString());
    } 
    return digest(arrayOfByte);
  }
  
  public static final byte[] digest(byte[] paramArrayOfbyte) throws RuntimeException {
    synchronized (MD5.class) {
      if (md5 == null)
        try {
          md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
          throw new RuntimeException(noSuchAlgorithmException.toString());
        }  
      return md5.digest(paramArrayOfbyte);
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\MD5.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */