package org.apache.xerces.dom;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class DOMMessageFormatter {
  public static final String DOM_DOMAIN = "http://www.w3.org/dom/DOMTR";
  
  public static final String XML_DOMAIN = "http://www.w3.org/TR/1998/REC-xml-19980210";
  
  public static final String SERIALIZER_DOMAIN = "http://apache.org/xml/serializer";
  
  private static ResourceBundle domResourceBundle = null;
  
  private static ResourceBundle xmlResourceBundle = null;
  
  private static ResourceBundle serResourceBundle = null;
  
  private static Locale locale = null;
  
  DOMMessageFormatter() {
    locale = Locale.getDefault();
  }
  
  public static String formatMessage(String paramString1, String paramString2, Object[] paramArrayOfObject) throws MissingResourceException {
    String str;
    ResourceBundle resourceBundle = getResourceBundle(paramString1);
    if (resourceBundle == null) {
      init();
      resourceBundle = getResourceBundle(paramString1);
      if (resourceBundle == null)
        throw new MissingResourceException("Unknown domain" + paramString1, null, paramString2); 
    } 
    try {
      str = paramString2 + ": " + resourceBundle.getString(paramString2);
      if (paramArrayOfObject != null)
        try {
          str = MessageFormat.format(str, paramArrayOfObject);
        } catch (Exception exception) {
          str = resourceBundle.getString("FormatFailed");
          str = str + " " + resourceBundle.getString(paramString2);
        }  
    } catch (MissingResourceException missingResourceException) {
      str = resourceBundle.getString("BadMessageKey");
      throw new MissingResourceException(paramString2, str, paramString2);
    } 
    if (str == null) {
      str = paramString2;
      if (paramArrayOfObject.length > 0) {
        StringBuffer stringBuffer = new StringBuffer(str);
        stringBuffer.append('?');
        for (byte b = 0; b < paramArrayOfObject.length; b++) {
          if (b > 0)
            stringBuffer.append('&'); 
          stringBuffer.append(String.valueOf(paramArrayOfObject[b]));
        } 
      } 
    } 
    return str;
  }
  
  static ResourceBundle getResourceBundle(String paramString) {
    return (paramString == "http://www.w3.org/dom/DOMTR" || paramString.equals("http://www.w3.org/dom/DOMTR")) ? domResourceBundle : ((paramString == "http://www.w3.org/TR/1998/REC-xml-19980210" || paramString.equals("http://www.w3.org/TR/1998/REC-xml-19980210")) ? xmlResourceBundle : ((paramString == "http://apache.org/xml/serializer" || paramString.equals("http://apache.org/xml/serializer")) ? serResourceBundle : null));
  }
  
  public static void init() {
    if (locale != null) {
      domResourceBundle = ResourceBundle.getBundle("org.apache.xerces.impl.msg.DOMMessages", locale);
      serResourceBundle = ResourceBundle.getBundle("org.apache.xerces.impl.msg.XMLSerializerMessages", locale);
      xmlResourceBundle = ResourceBundle.getBundle("org.apache.xerces.impl.msg.XMLMessages", locale);
    } else {
      domResourceBundle = ResourceBundle.getBundle("org.apache.xerces.impl.msg.DOMMessages");
      serResourceBundle = ResourceBundle.getBundle("org.apache.xerces.impl.msg.XMLSerializerMessages");
      xmlResourceBundle = ResourceBundle.getBundle("org.apache.xerces.impl.msg.XMLMessages");
    } 
  }
  
  public static void setLocale(Locale paramLocale) {
    locale = paramLocale;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom\DOMMessageFormatter.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */