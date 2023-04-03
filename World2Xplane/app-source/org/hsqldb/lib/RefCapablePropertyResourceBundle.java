package org.hsqldb.lib;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RefCapablePropertyResourceBundle {
  private PropertyResourceBundle wrappedBundle;
  
  private String baseName;
  
  private String language;
  
  private String country;
  
  private String variant;
  
  private static Map<ResourceBundle, RefCapablePropertyResourceBundle> allBundles = new HashMap<ResourceBundle, RefCapablePropertyResourceBundle>();
  
  public static String LS = System.getProperty("line.separator");
  
  private Pattern sysPropVarPattern = Pattern.compile("(?s)\\Q${\\E([^}]+?)(?:\\Q:+\\E([^}]+))?\\Q}");
  
  private Pattern posPattern = Pattern.compile("(?s)\\Q%{\\E(\\d)(?:\\Q:+\\E([^}]+))?\\Q}");
  
  private ClassLoader loader;
  
  public static final int THROW_BEHAVIOR = 0;
  
  public static final int EMPTYSTRING_BEHAVIOR = 1;
  
  public static final int NOOP_BEHAVIOR = 2;
  
  public Enumeration<String> getKeys() {
    return this.wrappedBundle.getKeys();
  }
  
  private RefCapablePropertyResourceBundle(String paramString, PropertyResourceBundle paramPropertyResourceBundle, ClassLoader paramClassLoader) {
    this.baseName = paramString;
    this.wrappedBundle = paramPropertyResourceBundle;
    Locale locale = paramPropertyResourceBundle.getLocale();
    this.loader = paramClassLoader;
    this.language = locale.getLanguage();
    this.country = locale.getCountry();
    this.variant = locale.getVariant();
    if (this.language.length() < 1)
      this.language = null; 
    if (this.country.length() < 1)
      this.country = null; 
    if (this.variant.length() < 1)
      this.variant = null; 
  }
  
  public String getExpandedString(String paramString, int paramInt) {
    String str = getString(paramString);
    Matcher matcher = this.sysPropVarPattern.matcher(str);
    int i = 0;
    StringBuffer stringBuffer = new StringBuffer();
    while (matcher.find()) {
      String str1 = matcher.group(1);
      String str3 = (matcher.groupCount() > 1) ? matcher.group(2) : null;
      String str2 = System.getProperty(str1);
      if (str3 != null)
        str2 = (str2 == null) ? "" : str3.replaceAll("\\Q$" + str1 + "\\E\\b", Matcher.quoteReplacement(str2)); 
      if (str2 == null)
        switch (paramInt) {
          case 0:
            throw new RuntimeException("No Sys Property set for variable '" + str1 + "' in property value (" + str + ").");
          case 1:
            str2 = "";
            break;
          case 2:
            break;
          default:
            throw new RuntimeException("Undefined value for behavior: " + paramInt);
        }  
      stringBuffer.append(str.substring(i, matcher.start()) + ((str2 == null) ? matcher.group() : str2));
      i = matcher.end();
    } 
    return (i < 1) ? str : (stringBuffer.toString() + str.substring(i));
  }
  
  public String posSubst(String paramString, String[] paramArrayOfString, int paramInt) {
    Matcher matcher = this.posPattern.matcher(paramString);
    int i = 0;
    StringBuffer stringBuffer = new StringBuffer();
    while (matcher.find()) {
      int j = Integer.parseInt(matcher.group(1)) - 1;
      String str2 = (matcher.groupCount() > 1) ? matcher.group(2) : null;
      String str1 = (j < paramArrayOfString.length) ? paramArrayOfString[j] : null;
      if (str2 != null)
        str1 = (str1 == null) ? "" : str2.replaceAll("\\Q%" + (j + 1) + "\\E\\b", Matcher.quoteReplacement(str1)); 
      if (str1 == null)
        switch (paramInt) {
          case 0:
            throw new RuntimeException(Integer.toString(paramArrayOfString.length) + " positional values given, but property string " + "contains (" + matcher.group() + ").");
          case 1:
            str1 = "";
            break;
          case 2:
            break;
          default:
            throw new RuntimeException("Undefined value for behavior: " + paramInt);
        }  
      stringBuffer.append(paramString.substring(i, matcher.start()) + ((str1 == null) ? matcher.group() : str1));
      i = matcher.end();
    } 
    return (i < 1) ? paramString : (stringBuffer.toString() + paramString.substring(i));
  }
  
  public String getExpandedString(String paramString, String[] paramArrayOfString, int paramInt1, int paramInt2) {
    return posSubst(getExpandedString(paramString, paramInt1), paramArrayOfString, paramInt2);
  }
  
  public String getString(String paramString, String[] paramArrayOfString, int paramInt) {
    return posSubst(getString(paramString), paramArrayOfString, paramInt);
  }
  
  public String toString() {
    return this.baseName + " for " + this.language + " / " + this.country + " / " + this.variant;
  }
  
  public String getString(String paramString) {
    String str = this.wrappedBundle.getString(paramString);
    if (str.length() < 1) {
      str = getStringFromFile(paramString);
      if (str.indexOf('\r') > -1)
        str = str.replaceAll("\\Q\r\n", "\n").replaceAll("\\Q\r", "\n"); 
      if (str.length() > 0 && str.charAt(str.length() - 1) == '\n')
        str = str.substring(0, str.length() - 1); 
    } 
    return toNativeLs(str);
  }
  
  public static String toNativeLs(String paramString) {
    return LS.equals("\n") ? paramString : paramString.replaceAll("\\Q\n", LS);
  }
  
  public static RefCapablePropertyResourceBundle getBundle(String paramString, ClassLoader paramClassLoader) {
    return getRef(paramString, ResourceBundle.getBundle(paramString, Locale.getDefault(), paramClassLoader), paramClassLoader);
  }
  
  public static RefCapablePropertyResourceBundle getBundle(String paramString, Locale paramLocale, ClassLoader paramClassLoader) {
    return getRef(paramString, ResourceBundle.getBundle(paramString, paramLocale, paramClassLoader), paramClassLoader);
  }
  
  private static RefCapablePropertyResourceBundle getRef(String paramString, ResourceBundle paramResourceBundle, ClassLoader paramClassLoader) {
    if (!(paramResourceBundle instanceof PropertyResourceBundle))
      throw new MissingResourceException("Found a Resource Bundle, but it is a " + paramResourceBundle.getClass().getName(), PropertyResourceBundle.class.getName(), null); 
    if (allBundles.containsKey(paramResourceBundle))
      return allBundles.get(paramResourceBundle); 
    RefCapablePropertyResourceBundle refCapablePropertyResourceBundle = new RefCapablePropertyResourceBundle(paramString, (PropertyResourceBundle)paramResourceBundle, paramClassLoader);
    allBundles.put(paramResourceBundle, refCapablePropertyResourceBundle);
    return refCapablePropertyResourceBundle;
  }
  
  private InputStream getMostSpecificStream(String paramString1, String paramString2, String paramString3, String paramString4) {
    final String filePath = this.baseName.replace('.', '/') + '/' + paramString1 + ((paramString2 == null) ? "" : ("_" + paramString2)) + ((paramString3 == null) ? "" : ("_" + paramString3)) + ((paramString4 == null) ? "" : ("_" + paramString4)) + ".text";
    InputStream inputStream = AccessController.<InputStream>doPrivileged(new PrivilegedAction<InputStream>() {
          public InputStream run() {
            return RefCapablePropertyResourceBundle.this.loader.getResourceAsStream(filePath);
          }
        });
    return (inputStream == null && paramString2 != null) ? getMostSpecificStream(paramString1, (paramString3 == null) ? null : paramString2, (paramString4 == null) ? null : paramString3, null) : inputStream;
  }
  
  private String getStringFromFile(String paramString) {
    byte[] arrayOfByte = null;
    int i = 0;
    InputStream inputStream = getMostSpecificStream(paramString, this.language, this.country, this.variant);
    if (inputStream == null)
      throw new MissingResourceException("Key '" + paramString + "' is present in .properties file with no value, yet " + "text file resource is missing", RefCapablePropertyResourceBundle.class.getName(), paramString); 
    try {
      try {
        arrayOfByte = new byte[inputStream.available()];
      } catch (RuntimeException runtimeException) {
        throw new MissingResourceException("Resource is too big to read in '" + paramString + "' value in one " + "gulp.\nPlease run the program with more RAM " + "(try Java -Xm* switches).: " + runtimeException, RefCapablePropertyResourceBundle.class.getName(), paramString);
      } catch (IOException iOException) {
        throw new MissingResourceException("Failed to read in value for key '" + paramString + "': " + iOException, RefCapablePropertyResourceBundle.class.getName(), paramString);
      } 
      try {
        int j;
        while (i < arrayOfByte.length && (j = inputStream.read(arrayOfByte, i, arrayOfByte.length - i)) > 0)
          i += j; 
      } catch (IOException iOException) {
        throw new MissingResourceException("Failed to read in value for '" + paramString + "': " + iOException, RefCapablePropertyResourceBundle.class.getName(), paramString);
      } 
    } finally {
      try {
        inputStream.close();
      } catch (IOException iOException) {
        System.err.println("Failed to close input stream: " + iOException);
      } 
    } 
    if (i != arrayOfByte.length)
      throw new MissingResourceException("Didn't read all bytes.  Read in " + i + " bytes out of " + arrayOfByte.length + " bytes for key '" + paramString + "'", RefCapablePropertyResourceBundle.class.getName(), paramString); 
    try {
      return new String(arrayOfByte, "ISO-8859-1");
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      throw new RuntimeException(unsupportedEncodingException);
    } catch (RuntimeException runtimeException) {
      throw new MissingResourceException("Value for key '" + paramString + "' too big to convert to String.  " + "Please run the program with more RAM " + "(try Java -Xm* switches).: " + runtimeException, RefCapablePropertyResourceBundle.class.getName(), paramString);
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\RefCapablePropertyResourceBundle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */