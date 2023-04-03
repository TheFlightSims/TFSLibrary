package org.apache.xml.serialize;

import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.Locale;
import org.apache.xerces.util.EncodingMap;

public class Encodings {
  static final int DEFAULT_LAST_PRINTABLE = 127;
  
  static final int LAST_PRINTABLE_UNICODE = 65535;
  
  static final String[] UNICODE_ENCODINGS = new String[] { "Unicode", "UnicodeBig", "UnicodeLittle", "GB2312", "UTF8", "UTF-16" };
  
  static final String DEFAULT_ENCODING = "UTF8";
  
  static Hashtable _encodings = new Hashtable();
  
  static final String JIS_DANGER_CHARS = "\\~¢£¥¬—―‖…‾‾∥∯〜＼～￠￡￢￣";
  
  static EncodingInfo getEncodingInfo(String paramString, boolean paramBoolean) throws UnsupportedEncodingException {
    EncodingInfo encodingInfo = null;
    if (paramString == null) {
      if ((encodingInfo = (EncodingInfo)_encodings.get("UTF8")) != null)
        return encodingInfo; 
      encodingInfo = new EncodingInfo(EncodingMap.getJava2IANAMapping("UTF8"), "UTF8", 65535);
      _encodings.put("UTF8", encodingInfo);
      return encodingInfo;
    } 
    paramString = paramString.toUpperCase(Locale.ENGLISH);
    String str = EncodingMap.getIANA2JavaMapping(paramString);
    if (str == null) {
      if (paramBoolean) {
        EncodingInfo.testJavaEncodingName(paramString);
        if ((encodingInfo = (EncodingInfo)_encodings.get(paramString)) != null)
          return encodingInfo; 
        byte b1;
        for (b1 = 0; b1 < UNICODE_ENCODINGS.length; b1++) {
          if (UNICODE_ENCODINGS[b1].equalsIgnoreCase(paramString)) {
            encodingInfo = new EncodingInfo(EncodingMap.getJava2IANAMapping(paramString), paramString, 65535);
            break;
          } 
        } 
        if (b1 == UNICODE_ENCODINGS.length)
          encodingInfo = new EncodingInfo(EncodingMap.getJava2IANAMapping(paramString), paramString, 127); 
        _encodings.put(paramString, encodingInfo);
        return encodingInfo;
      } 
      throw new UnsupportedEncodingException(paramString);
    } 
    if ((encodingInfo = (EncodingInfo)_encodings.get(str)) != null)
      return encodingInfo; 
    byte b;
    for (b = 0; b < UNICODE_ENCODINGS.length; b++) {
      if (UNICODE_ENCODINGS[b].equalsIgnoreCase(str)) {
        encodingInfo = new EncodingInfo(paramString, str, 65535);
        break;
      } 
    } 
    if (b == UNICODE_ENCODINGS.length)
      encodingInfo = new EncodingInfo(paramString, str, 127); 
    _encodings.put(str, encodingInfo);
    return encodingInfo;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xml\serialize\Encodings.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */