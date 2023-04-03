package org.apache.xerces.jaxp.validation;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

final class JAXPValidationMessageFormatter {
  public static String formatMessage(Locale paramLocale, String paramString, Object[] paramArrayOfObject) throws MissingResourceException {
    String str;
    ResourceBundle resourceBundle = null;
    if (paramLocale != null) {
      resourceBundle = ResourceBundle.getBundle("org.apache.xerces.impl.msg.JAXPValidationMessages", paramLocale);
    } else {
      resourceBundle = ResourceBundle.getBundle("org.apache.xerces.impl.msg.JAXPValidationMessages");
    } 
    try {
      str = resourceBundle.getString(paramString);
      if (paramArrayOfObject != null)
        try {
          str = MessageFormat.format(str, paramArrayOfObject);
        } catch (Exception exception) {
          str = resourceBundle.getString("FormatFailed");
          str = str + " " + resourceBundle.getString(paramString);
        }  
    } catch (MissingResourceException missingResourceException) {
      str = resourceBundle.getString("BadMessageKey");
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


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\jaxp\validation\JAXPValidationMessageFormatter.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */