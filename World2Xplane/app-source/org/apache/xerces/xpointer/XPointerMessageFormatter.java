package org.apache.xerces.xpointer;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.apache.xerces.util.MessageFormatter;

class XPointerMessageFormatter implements MessageFormatter {
  public static final String XPOINTER_DOMAIN = "http://www.w3.org/TR/XPTR";
  
  private Locale fLocale = null;
  
  private ResourceBundle fResourceBundle = null;
  
  public String formatMessage(Locale paramLocale, String paramString, Object[] paramArrayOfObject) throws MissingResourceException {
    if (this.fResourceBundle == null || paramLocale != this.fLocale) {
      if (paramLocale != null) {
        this.fResourceBundle = ResourceBundle.getBundle("org.apache.xerces.impl.msg.XPointerMessages", paramLocale);
        this.fLocale = paramLocale;
      } 
      if (this.fResourceBundle == null)
        this.fResourceBundle = ResourceBundle.getBundle("org.apache.xerces.impl.msg.XPointerMessages"); 
    } 
    String str = this.fResourceBundle.getString(paramString);
    if (paramArrayOfObject != null)
      try {
        str = MessageFormat.format(str, paramArrayOfObject);
      } catch (Exception exception) {
        str = this.fResourceBundle.getString("FormatFailed");
        str = str + " " + this.fResourceBundle.getString(paramString);
      }  
    if (str == null) {
      str = this.fResourceBundle.getString("BadMessageKey");
      throw new MissingResourceException(str, "org.apache.xerces.impl.msg.XPointerMessages", paramString);
    } 
    return str;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\xpointer\XPointerMessageFormatter.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */