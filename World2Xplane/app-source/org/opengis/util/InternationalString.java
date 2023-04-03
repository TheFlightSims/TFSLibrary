package org.opengis.util;

import java.util.Locale;

public interface InternationalString extends CharSequence, Comparable<InternationalString> {
  String toString(Locale paramLocale);
  
  String toString();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengi\\util\InternationalString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */