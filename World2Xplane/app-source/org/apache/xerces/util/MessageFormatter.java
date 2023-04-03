package org.apache.xerces.util;

import java.util.Locale;
import java.util.MissingResourceException;

public interface MessageFormatter {
  String formatMessage(Locale paramLocale, String paramString, Object[] paramArrayOfObject) throws MissingResourceException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerce\\util\MessageFormatter.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */