package javax.vecmath;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

class VecMathI18N {
  static String getString(String paramString) {
    String str;
    try {
      str = ResourceBundle.getBundle("javax.vecmath.ExceptionStrings").getString(paramString);
    } catch (MissingResourceException missingResourceException) {
      System.err.println("VecMathI18N: Error looking up: " + paramString);
      str = paramString;
    } 
    return str;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\vecmath\VecMathI18N.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */