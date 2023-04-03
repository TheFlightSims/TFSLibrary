package org.apache.xerces.impl.msg;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.apache.xerces.util.MessageFormatter;

public class XMLMessageFormatter implements MessageFormatter {
  public static final String XML_DOMAIN = "http://www.w3.org/TR/1998/REC-xml-19980210";
  
  public static final String XMLNS_DOMAIN = "http://www.w3.org/TR/1999/REC-xml-names-19990114";
  
  private Locale fLocale = null;
  
  private ResourceBundle fResourceBundle = null;
  
  public String formatMessage(Locale paramLocale, String paramString, Object[] paramArrayOfObject) throws MissingResourceException {
    String str;
    if (this.fResourceBundle == null || paramLocale != this.fLocale) {
      if (paramLocale != null) {
        this.fResourceBundle = ResourceBundle.getBundle("org.apache.xerces.impl.msg.XMLMessages", paramLocale);
        this.fLocale = paramLocale;
      } 
      if (this.fResourceBundle == null)
        this.fResourceBundle = ResourceBundle.getBundle("org.apache.xerces.impl.msg.XMLMessages"); 
    } 
    try {
      str = this.fResourceBundle.getString(paramString);
      if (paramArrayOfObject != null)
        try {
          str = MessageFormat.format(str, paramArrayOfObject);
        } catch (Exception exception) {
          str = this.fResourceBundle.getString("FormatFailed");
          str = str + " " + this.fResourceBundle.getString(paramString);
        }  
    } catch (MissingResourceException missingResourceException) {
      str = this.fResourceBundle.getString("BadMessageKey");
      throw new MissingResourceException(paramString, str, paramString);
    } 
    if (str == null) {
      str = paramString;
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
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\msg\XMLMessageFormatter.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */