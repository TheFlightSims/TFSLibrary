package org.apache.commons.math3.exception.util;

import java.io.Serializable;
import java.util.Locale;

public interface Localizable extends Serializable {
  String getSourceString();
  
  String getLocalizedString(Locale paramLocale);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\exceptio\\util\Localizable.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */