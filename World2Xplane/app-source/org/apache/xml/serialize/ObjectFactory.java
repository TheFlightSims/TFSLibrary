package org.apache.xml.serialize;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

final class ObjectFactory {
  private static final String DEFAULT_PROPERTIES_FILENAME = "xerces.properties";
  
  private static final boolean DEBUG = false;
  
  private static final int DEFAULT_LINE_LENGTH = 80;
  
  private static Properties fXercesProperties = null;
  
  private static long fLastModified = -1L;
  
  static Object createObject(String paramString1, String paramString2) throws ConfigurationError {
    return createObject(paramString1, null, paramString2);
  }
  
  static Object createObject(String paramString1, String paramString2, String paramString3) throws ConfigurationError {
    ClassLoader classLoader = findClassLoader();
    try {
      String str1 = SecuritySupport.getSystemProperty(paramString1);
      if (str1 != null)
        return newInstance(str1, classLoader, true); 
    } catch (SecurityException securityException) {}
    String str = null;
    if (paramString2 == null) {
      File file = null;
      boolean bool = false;
      try {
        String str1 = SecuritySupport.getSystemProperty("java.home");
        paramString2 = str1 + File.separator + "lib" + File.separator + "xerces.properties";
        file = new File(paramString2);
        bool = SecuritySupport.getFileExists(file);
      } catch (SecurityException securityException) {
        fLastModified = -1L;
        fXercesProperties = null;
      } 
      synchronized (ObjectFactory.class) {
        boolean bool1 = false;
        FileInputStream fileInputStream = null;
        try {
          if (fLastModified >= 0L) {
            if (bool && fLastModified < (fLastModified = SecuritySupport.getLastModified(file))) {
              bool1 = true;
            } else if (!bool) {
              fLastModified = -1L;
              fXercesProperties = null;
            } 
          } else if (bool) {
            bool1 = true;
            fLastModified = SecuritySupport.getLastModified(file);
          } 
          if (bool1) {
            fXercesProperties = new Properties();
            fileInputStream = SecuritySupport.getFileInputStream(file);
            fXercesProperties.load(fileInputStream);
          } 
        } catch (Exception exception) {
          fXercesProperties = null;
          fLastModified = -1L;
        } finally {
          if (fileInputStream != null)
            try {
              fileInputStream.close();
            } catch (IOException iOException) {} 
        } 
      } 
      if (fXercesProperties != null)
        str = fXercesProperties.getProperty(paramString1); 
    } else {
      FileInputStream fileInputStream = null;
      try {
        fileInputStream = SecuritySupport.getFileInputStream(new File(paramString2));
        Properties properties = new Properties();
        properties.load(fileInputStream);
        str = properties.getProperty(paramString1);
      } catch (Exception exception) {
      
      } finally {
        if (fileInputStream != null)
          try {
            fileInputStream.close();
          } catch (IOException iOException) {} 
      } 
    } 
    if (str != null)
      return newInstance(str, classLoader, true); 
    Object object = findJarServiceProvider(paramString1);
    if (object != null)
      return object; 
    if (paramString3 == null)
      throw new ConfigurationError("Provider for " + paramString1 + " cannot be found", null); 
    return newInstance(paramString3, classLoader, true);
  }
  
  private static void debugPrintln(String paramString) {}
  
  static ClassLoader findClassLoader() throws ConfigurationError {
    ClassLoader classLoader1 = SecuritySupport.getContextClassLoader();
    ClassLoader classLoader2 = SecuritySupport.getSystemClassLoader();
    for (ClassLoader classLoader3 = classLoader2;; classLoader3 = SecuritySupport.getParentClassLoader(classLoader3)) {
      if (classLoader1 == classLoader3) {
        ClassLoader classLoader = ObjectFactory.class.getClassLoader();
        for (classLoader3 = classLoader2;; classLoader3 = SecuritySupport.getParentClassLoader(classLoader3)) {
          if (classLoader == classLoader3)
            return classLoader2; 
          if (classLoader3 == null)
            return classLoader; 
        } 
        break;
      } 
      if (classLoader3 == null)
        return classLoader1; 
    } 
  }
  
  static Object newInstance(String paramString, ClassLoader paramClassLoader, boolean paramBoolean) throws ConfigurationError {
    try {
      Class clazz = findProviderClass(paramString, paramClassLoader, paramBoolean);
      return clazz.newInstance();
    } catch (ClassNotFoundException classNotFoundException) {
      throw new ConfigurationError("Provider " + paramString + " not found", classNotFoundException);
    } catch (Exception exception) {
      throw new ConfigurationError("Provider " + paramString + " could not be instantiated: " + exception, exception);
    } 
  }
  
  static Class findProviderClass(String paramString, ClassLoader paramClassLoader, boolean paramBoolean) throws ClassNotFoundException, ConfigurationError {
    Class clazz;
    SecurityManager securityManager = System.getSecurityManager();
    if (securityManager != null) {
      int i = paramString.lastIndexOf(".");
      String str = paramString;
      if (i != -1)
        str = paramString.substring(0, i); 
      securityManager.checkPackageAccess(str);
    } 
    if (paramClassLoader == null) {
      clazz = Class.forName(paramString);
    } else {
      try {
        clazz = paramClassLoader.loadClass(paramString);
      } catch (ClassNotFoundException classNotFoundException) {
        if (paramBoolean) {
          ClassLoader classLoader = ObjectFactory.class.getClassLoader();
          if (classLoader == null) {
            clazz = Class.forName(paramString);
          } else if (paramClassLoader != classLoader) {
            paramClassLoader = classLoader;
            clazz = paramClassLoader.loadClass(paramString);
          } else {
            throw classNotFoundException;
          } 
        } else {
          throw classNotFoundException;
        } 
      } 
    } 
    return clazz;
  }
  
  private static Object findJarServiceProvider(String paramString) throws ConfigurationError {
    BufferedReader bufferedReader;
    String str1 = "META-INF/services/" + paramString;
    InputStream inputStream = null;
    ClassLoader classLoader = findClassLoader();
    inputStream = SecuritySupport.getResourceAsStream(classLoader, str1);
    if (inputStream == null) {
      ClassLoader classLoader1 = ObjectFactory.class.getClassLoader();
      if (classLoader != classLoader1) {
        classLoader = classLoader1;
        inputStream = SecuritySupport.getResourceAsStream(classLoader, str1);
      } 
    } 
    if (inputStream == null)
      return null; 
    try {
      bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 80);
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 80);
    } 
    String str2 = null;
    try {
      str2 = bufferedReader.readLine();
    } catch (IOException iOException) {
      return null;
    } finally {
      try {
        bufferedReader.close();
      } catch (IOException iOException) {}
    } 
    return (str2 != null && !"".equals(str2)) ? newInstance(str2, classLoader, false) : null;
  }
  
  static final class ConfigurationError extends Error {
    static final long serialVersionUID = 937647395548533254L;
    
    private Exception exception;
    
    ConfigurationError(String param1String, Exception param1Exception) {
      super(param1String);
      this.exception = param1Exception;
    }
    
    Exception getException() {
      return this.exception;
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xml\serialize\ObjectFactory.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */