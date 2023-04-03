package org.apache.xerces.impl.dv.xs;

import java.io.UnsupportedEncodingException;
import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidationContext;
import org.apache.xerces.util.URI;

public class AnyURIDV extends TypeValidator {
  private static final URI BASE_URI;
  
  private static boolean[] gNeedEscaping = new boolean[128];
  
  private static char[] gAfterEscaping1 = new char[128];
  
  private static char[] gAfterEscaping2 = new char[128];
  
  private static char[] gHexChs = new char[] { 
      '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
      'A', 'B', 'C', 'D', 'E', 'F' };
  
  public short getAllowedFacets() {
    return 2079;
  }
  
  public Object getActualValue(String paramString, ValidationContext paramValidationContext) throws InvalidDatatypeValueException {
    try {
      if (paramString.length() != 0) {
        String str = encode(paramString);
        new URI(BASE_URI, str);
      } 
    } catch (org.apache.xerces.util.URI.MalformedURIException malformedURIException) {
      throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { paramString, "anyURI" });
    } 
    return paramString;
  }
  
  private static String encode(String paramString) {
    int i = paramString.length();
    StringBuffer stringBuffer = new StringBuffer(i * 3);
    byte b;
    for (b = 0; b < i; b++) {
      char c = paramString.charAt(b);
      if (c >= '')
        break; 
      if (gNeedEscaping[c]) {
        stringBuffer.append('%');
        stringBuffer.append(gAfterEscaping1[c]);
        stringBuffer.append(gAfterEscaping2[c]);
      } else {
        stringBuffer.append((char)c);
      } 
    } 
    if (b < i) {
      byte[] arrayOfByte = null;
      try {
        arrayOfByte = paramString.substring(b).getBytes("UTF-8");
      } catch (UnsupportedEncodingException unsupportedEncodingException) {
        return paramString;
      } 
      i = arrayOfByte.length;
      for (b = 0; b < i; b++) {
        byte b1 = arrayOfByte[b];
        if (b1 < 0) {
          int j = b1 + 256;
          stringBuffer.append('%');
          stringBuffer.append(gHexChs[j >> 4]);
          stringBuffer.append(gHexChs[j & 0xF]);
        } else if (gNeedEscaping[b1]) {
          stringBuffer.append('%');
          stringBuffer.append(gAfterEscaping1[b1]);
          stringBuffer.append(gAfterEscaping2[b1]);
        } else {
          stringBuffer.append((char)b1);
        } 
      } 
    } 
    return (stringBuffer.length() != i) ? stringBuffer.toString() : paramString;
  }
  
  static {
    for (byte b = 0; b <= 31; b++) {
      gNeedEscaping[b] = true;
      gAfterEscaping1[b] = gHexChs[b >> 4];
      gAfterEscaping2[b] = gHexChs[b & 0xF];
    } 
    gNeedEscaping[127] = true;
    gAfterEscaping1[127] = '7';
    gAfterEscaping2[127] = 'F';
    for (char c : new char[] { 
        ' ', '<', '>', '"', '{', '}', '|', '\\', '^', '~', 
        '`' }) {
      gNeedEscaping[c] = true;
      gAfterEscaping1[c] = gHexChs[c >> 4];
      gAfterEscaping2[c] = gHexChs[c & 0xF];
    } 
  }
  
  static {
    URI uRI = null;
    try {
      uRI = new URI("abc://def.ghi.jkl");
    } catch (org.apache.xerces.util.URI.MalformedURIException malformedURIException) {}
    BASE_URI = uRI;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\dv\xs\AnyURIDV.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */