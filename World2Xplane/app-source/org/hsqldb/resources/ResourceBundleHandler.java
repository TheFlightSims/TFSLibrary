package org.hsqldb.resources;

import java.lang.reflect.Method;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.hsqldb.lib.HashMap;
import org.hsqldb.lib.HsqlArrayList;

public final class ResourceBundleHandler {
  private static final Object mutex = new Object();
  
  private static Locale locale = Locale.getDefault();
  
  private static HashMap bundleHandleMap = new HashMap();
  
  private static HsqlArrayList bundleList = new HsqlArrayList();
  
  private static final String prefix = "org.hsqldb.resources.";
  
  private static final Method newGetBundleMethod = getNewGetBundleMethod();
  
  public static Locale getLocale() {
    synchronized (mutex) {
      return locale;
    } 
  }
  
  public static void setLocale(Locale paramLocale) throws IllegalArgumentException {
    synchronized (mutex) {
      if (paramLocale == null)
        throw new IllegalArgumentException("null locale"); 
      locale = paramLocale;
    } 
  }
  
  public static int getBundleHandle(String paramString, ClassLoader paramClassLoader) {
    Integer integer;
    String str = "org.hsqldb.resources." + paramString;
    synchronized (mutex) {
      String str1 = locale.toString() + str;
      integer = (Integer)bundleHandleMap.get(str1);
      if (integer == null) {
        ResourceBundle resourceBundle = getBundle(str, locale, paramClassLoader);
        bundleList.add(resourceBundle);
        integer = new Integer(bundleList.size() - 1);
        bundleHandleMap.put(str1, integer);
      } 
    } 
    return (integer == null) ? -1 : integer.intValue();
  }
  
  public static String getString(int paramInt, String paramString) {
    ResourceBundle resourceBundle;
    String str;
    synchronized (mutex) {
      if (paramInt < 0 || paramInt >= bundleList.size() || paramString == null) {
        resourceBundle = null;
      } else {
        resourceBundle = (ResourceBundle)bundleList.get(paramInt);
      } 
    } 
    if (resourceBundle == null) {
      str = null;
    } else {
      try {
        str = resourceBundle.getString(paramString);
      } catch (Exception exception) {
        str = null;
      } 
    } 
    return str;
  }
  
  private static Method getNewGetBundleMethod() {
    Class<ResourceBundle> clazz = ResourceBundle.class;
    Class[] arrayOfClass = { String.class, Locale.class, ClassLoader.class };
    try {
      return clazz.getMethod("getBundle", arrayOfClass);
    } catch (Exception exception) {
      return null;
    } 
  }
  
  public static ResourceBundle getBundle(String paramString, Locale paramLocale, ClassLoader paramClassLoader) throws NullPointerException, MissingResourceException {
    if (paramClassLoader == null)
      return ResourceBundle.getBundle(paramString, paramLocale); 
    if (newGetBundleMethod == null)
      return ResourceBundle.getBundle(paramString, paramLocale); 
    try {
      return (ResourceBundle)newGetBundleMethod.invoke(null, new Object[] { paramString, paramLocale, paramClassLoader });
    } catch (Exception exception) {
      return ResourceBundle.getBundle(paramString, paramLocale);
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\resources\ResourceBundleHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */